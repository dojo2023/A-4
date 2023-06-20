package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GoalsDao;
import model.Goals;

/**
 * Servlet implementation class GoalServlet
 */
@WebServlet("/GoalServlet")
public class GoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		// 目標ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUserModal.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String userUuid = (String)session.getAttribute("id");
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String goalName = request.getParameter("GOAL_NAME");
			String genreTag = request.getParameter("GENRE_TAG");
			String goalTime = request.getParameter("GOAL_TIME");

			//インスタンス化する
			GoalsDao gDao = new GoalsDao();
			Goals goal = new Goals(goalName, genreTag, Integer.parseInt(goalTime), userUuid);
			gDao.goalAdd(goal);
			if(gDao.goalAdd(goal)) {
				System.out.println("目標の追加に成功しました");

			}else {
				System.out.println("目標の追加に失敗しました");
			}

			// 検索結果をリクエストスコープに格納する
			request.setAttribute("goalList", goal);

			// 目標ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUserModal.jsp");
			dispatcher.forward(request, response);

	}

}
