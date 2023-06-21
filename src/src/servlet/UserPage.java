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
import DAO.RankingDao;
import model.Goals;
import model.Posts;
import model.Rankings;
import model.User;

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
//		// マイページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
//		dispatcher.forward(request, response);


		String userUuid = (String)session.getAttribute("id");


		AccountsDao aDao = new AccountsDao();
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




		// 合計活動時間
		request.setCharacterEncoding("UTF-8");
		String uuid = request.getParameter("uuid");
//		System.out.println(request.getParameterValues(tag).length);
		System.out.println(uuid);



		PostsDAO ttDao = new PostsDAO();
		User userTotalTime = new User();


		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userTotalTime", userTotalTime);

		// 結果をページにフォワードする
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
		dispatcher.forward(request, response);

	}
}