package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountsDao;
import DAO.GoalsDao;
import DAO.PostsDAO;
import model.Goals;
import model.Posts;

/**
 * Servlet implementation class TopPage
 */
@WebServlet("/TopPage")
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		String userUuid = (String)session.getAttribute("id");

		// IDからユーザ情報を問い合わせる
		AccountsDao aDao = new AccountsDao();
		model.User loginUser = aDao.showUser(userUuid); //ログインユーザの情報を取得
		String username = loginUser.getUser_name();

		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("username", username);

		// 投稿データを全件取得し、リストをリクエストスコープに格納する。
		PostsDAO pDao = new PostsDAO();
		List<Posts> postList = pDao.postShow();
		request.setAttribute("postList", postList);

		// ユーザの目標データを取得し、リストをリクエストスコープに格納する。
		GoalsDao gDao = new GoalsDao();
		List<Goals> goalList = gDao.goalShowUser(userUuid);
		request.setAttribute("goalList", goalList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
//		String tag = request.getParameter("tag"); //タグ取得
		String msg = request.getParameter("msg"); //メッセージ取得
		String goalId = request.getParameter("goal"); //目標取得
		int mins = Integer.parseInt(request.getParameter("mins"));
		int hours = Integer.parseInt(request.getParameter("hours"));
		mins = hours*60;

		// ログインされているユーザのIDを取得
		String userUuid = (String)session.getAttribute("id");


		if (request.getParameter("select").equals("new_post")) {
			// 投稿処理を行う
			PostsDAO pDao = new PostsDAO();
			if (pDao.postAdd(new Posts(userUuid, msg, mins, goalId))) { // 登録成功
				System.out.println("登録は成功しました。");
			}
			else { // 登録失敗
				System.out.println("登録は失敗しました。");
			}
		} else if (request.getParameter("select").equals("new_goal")) {
			// 目標の登録処理を行う
			String goalName = request.getParameter("name");
			String goalTag = request.getParameter("tag");
			int goalHours = Integer.parseInt(request.getParameter("hours"));
			int goalMins = Integer.parseInt(request.getParameter("mins"));
			goalMins = goalMins + (goalHours*60);
			GoalsDao gDao = new GoalsDao();
			if (gDao.goalAdd(new Goals(goalName, goalTag, goalMins, userUuid))) { // 登録成功
				System.out.println("登録は成功しました。");
			}
			else { // 登録失敗
				System.out.println("登録は失敗しました。");
			}
		}

		response.sendRedirect("TopPage");
//		dispatcher.forward(request, response);
	}

}
