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


		//目標の一覧を表示する
		request.setCharacterEncoding("UTF-8");
		//String goalId = request.getParameter("GOAL_ID");
		String goalName = request.getParameter("GOAL_NAME");
		String genreTag = request.getParameter("GENRE_TAG");
		String goalTime = request.getParameter("GOAL_TIME");
		//String achievementTime = request.getParameter("ACHIEVEMENT_GOAL");
		//String goalDate = request.getParameter("GOAL_DATE");
		String userUuid = request.getParameter("USER_UUID");
		//String exercise = request.getParameter("exercise");
		//String study = request.getParameter("study");
		//String  reading= request.getParameter("reading");
		//String others = request.getParameter("others");


		// 編集または削除を行う
		GoalsDao eDao = new GoalsDao();
		if (request.getParameter("SUBMIT").equals("編集")) {
			if (eDao.update(new Goals(goalName, genreTag, goalTime, userUuid))) {	// 編集成功
				request.setAttribute("result",
				new Result("更新成功！", "レコードを更新しました。", "/NYASTER/GoalServlet"));
			}
			else {												// 編集失敗
				request.setAttribute("result",
				new Result("更新失敗！", "レコードを更新できませんでした。", "/NYASTER/GoalServlet"));
			}
		}
		else {
			if (eDao.delete(userUuid)) {	// 削除成功
				request.setAttribute("result",
				new Result("削除成功！", "レコードを削除しました。", "/NYASTER/GoalServlet"));
			}
			else {						// 削除失敗
				request.setAttribute("result",
				new Result("削除失敗！", "レコードを削除できませんでした。", "/NYASTER/GoalServlet"));
			}
		}


		//JSPに持ってくる
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

			//インスタンス化する(追加)
			GoalsDao gDao = new GoalsDao();
			Goals goal = new Goals(goalName, genreTag, Integer.parseInt(goalTime), userUuid);
			gDao.goalAdd(goal);
			if(gDao.goalAdd(goal)) {
				System.out.println("目標の追加に成功しました");

			}else {
				System.out.println("目標の追加に失敗しました");
			}

			// 結果をリクエストスコープに格納する
			request.setAttribute("goalList", goal);

			// 目標ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUserModal.jsp");
			dispatcher.forward(request, response);


	}

}
