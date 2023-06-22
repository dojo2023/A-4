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

@WebServlet("/UserPage")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();

		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		String userUuid = (String)session.getAttribute("id"); //ログイン中のユーザUUIDを取得

		request.setCharacterEncoding("UTF-8");
		AccountsDao aDao = new AccountsDao();
		String searchUser = aDao.showUserUuid(request.getParameter("u"));

		// URLのパラメータにユーザのIDがある場合
		if(searchUser != null && searchUser != userUuid) {
			userUuid = searchUser;
		} else {
			System.out.println("ログイン中のアカウントの情報を表示します。");
		}

		model.User loginUser = aDao.showUser(userUuid);  //ログインユーザの情報を取得

		// IDからユーザ情報を問い合わせる
		String username = loginUser.getUser_name();

		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("username", username);

		// 特定ユーザーの投稿データを全件取得し、リストをリクエストスコープに格納する。
		PostsDAO pDao = new PostsDAO();
		List<Posts> postList = pDao.postShowUser(userUuid);
		request.setAttribute("postList", postList);

		// ユーザの目標データを取得し、リストをリクエストスコープに格納する。
		GoalsDao gDao = new GoalsDao();
		List<Goals> goalList = gDao.goalShowUser(userUuid);
		request.setAttribute("goalList", goalList);

		// ユーザの合計活動時間をリクエストスコープに格納する
		Posts tt = pDao.userTotalTime(userUuid);
		request.setAttribute("uttHours", tt.getGanbariTimeHours());
		request.setAttribute("uttMins", tt.getGanbariTimeMins());

		// 結果をページにフォワードする
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
		dispatcher.forward(request, response);

	}
}