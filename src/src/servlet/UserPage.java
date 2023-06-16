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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserPage.jsp");
		dispatcher.forward(request, response);


		// 合計活動時間
		request.setCharacterEncoding("UTF-8");
		String uuid = request.getParameter("uuid");
//		System.out.println(request.getParameterValues(tag).length);
		System.out.println(uuid);



		PostsDAO raDao = new PostsDAO();
		List<Totals> totalList = raDao.totaltime(uuid);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("totalList", totalList);

		// 結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
		dispatcher.forward(request, response);

	}
}