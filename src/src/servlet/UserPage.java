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
import DAO.ReactionsDao;
import model.Goals;
import model.Posts;
import model.Reactions;


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
		String userid = loginUser.getUser_id();
		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("pageUserName", username);
		request.setAttribute("pageUserId", userid);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		// ログインされているユーザのIDを取得
		String userUuid = (String)session.getAttribute("id");
		System.out.println("操作："+(request.getParameter("select")));

		if (request.getParameter("select").equals("link")) {
			// 遷移するユーザページのユーザUUID
			System.out.println("ユーザUUIDを取得します...");
			AccountsDao aDao = new AccountsDao();
			String pageUserId = request.getParameter("page_user_id");
			String pageUserUuid = aDao.showUserUuid(pageUserId);

			model.User pageUser = aDao.showUser(pageUserUuid);
			String pageUserName = pageUser.getUser_name();
			request.setAttribute("pageUserId", pageUserId);
			request.setAttribute("pageUserName", pageUserName);

			// 特定ユーザーの投稿データを全件取得し、リストをリクエストスコープに格納する。
			PostsDAO pDao = new PostsDAO();
			List<Posts> postList = pDao.postShowUser(pageUserUuid);
			request.setAttribute("postList", postList);

			// ユーザの目標データを取得し、リストをリクエストスコープに格納する。
			GoalsDao gDao = new GoalsDao();
			List<Goals> goalList = gDao.goalShowUser(pageUserUuid);
			request.setAttribute("goalList", goalList);

			// ユーザの合計活動時間をリクエストスコープに格納する
			Posts tt = pDao.userTotalTime(userUuid);
			request.setAttribute("uttHours", tt.getGanbariTimeHours());
			request.setAttribute("uttMins", tt.getGanbariTimeMins());

			// ログインユーザと遷移先のユーザが同一か判定

			if(pageUserUuid.equals(userUuid)) {
				request.setAttribute("mypage", true);
			} else {
				request.setAttribute("mypage", false);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
			dispatcher.forward(request, response);

		} else if (request.getParameter("select").equals("ナイス")) {
			// リクエストパラメータを取得する
//			request.setCharacterEncoding("UTF-8");
			String postId = request.getParameter("postId");
			System.out.println(postId);

			// 登録処理を行う
			ReactionsDao reDao = new ReactionsDao();
			Reactions p = new Reactions();
			p.setPost_id(postId);
			Reactions u = new Reactions();
			u.setUser_uuid(userUuid);
			boolean result = reDao.check(u,p);
			request.setAttribute("check", result);
			if (result == false) {
				if (reDao.Reactioninsert(new Reactions(userUuid,postId))) {

					System.out.println("ナイス＋1");
					response.sendRedirect("/NYASTER/TopPage");
				}else {
					System.out.println("できませんでした");
				}
			}else {
				if(reDao.delete(new Reactions(userUuid,postId))) {

					System.out.println("ナイス－1");
					response.sendRedirect("/NYASTER/TopPage");
				}	else {
					System.out.println("リアクションできん");
				}
			}

		}else if (request.getParameter("select").equals("ログアウト")) {
			session.removeAttribute("id");
			System.out.println("ログアウトしました");
			response.sendRedirect("/NYASTER/Login");
		}

	}
}