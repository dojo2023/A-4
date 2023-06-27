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
import DAO.RankingDao;
import model.Rankings;
import model.User;
/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		RankingDao raDao = new RankingDao();
		List<Rankings> rankingList = raDao.ranking("累計");

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("rankingList", rankingList);

		// 結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
		dispatcher.forward(request, response);

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
		String userid = loginUser.getUser_id();
		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("username", username);
		request.setAttribute("userid", userid);

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tag = request.getParameter("tag");

		RankingDao raDao = new RankingDao();
		List<Rankings> rankingList = raDao.ranking(tag);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("rankingList", rankingList);

		// 結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
		dispatcher.forward(request, response);




	}
}
