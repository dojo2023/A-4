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

/**
 * Servlet implementation class TopPage
 */
@WebServlet("/TopPage")
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//セッションスコープにユーザIDが存在しない場合はログインページにリダイレクトする
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

		String userUuid = (String)session.getAttribute("id");
		request.setAttribute("useruuid", userUuid);

		// IDからユーザ情報を問い合わせる
		AccountsDao aDao = new AccountsDao();
		model.User loginUser = aDao.showUser(userUuid); //ログインユーザの情報を取得
		String username = loginUser.getUser_name();

		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("username", username);
		
		// 投稿データを全件取得し、リストをリクエストスコープに格納する。
		PostsDAO pDao = new PostsDAO();
		List<Posts> postList = pDao.postShow(userUuid);
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
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		// ログインされているユーザのIDを取得
		String userUuid = (String)session.getAttribute("id");

		System.out.println("操作："+(request.getParameter("select")));

		if (request.getParameter("select").equals("投稿")) {
			// 投稿処理を行う
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String msg = request.getParameter("msg"); //メッセージ取得
			String goalId = request.getParameter("goal"); //目標取得
			int mins = Integer.parseInt(request.getParameter("mins"));
			int hours = Integer.parseInt(request.getParameter("hours"));
			mins += (hours*60);
			System.out.println("投稿（時間）：" + mins);
			PostsDAO pDao = new PostsDAO();
			GoalsDao gDao = new GoalsDao();
			if (pDao.postAdd(new Posts(userUuid, msg, mins, goalId))) { // 登録成功
				System.out.println("投稿の登録が成功しました。");
				if (gDao.addTime(goalId, mins)) {
					System.out.println("がんばり時間の追加が成功しました。");
				} else {
					System.out.println("がんばり時間の追加が失敗しました。");
				}
			}
			else { // 登録失敗
				System.out.println("投稿の登録が失敗しました。");
			}
			response.sendRedirect("/NYASTER/TopPage");

		} else if (request.getParameter("select").equals("追加")) {
			// 目標の登録処理を行う
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String goalName = request.getParameter("goal_name");
			String goalTag = request.getParameter("goal_tag");
			int goalHours = Integer.parseInt(request.getParameter("goal_hours"));
			int goalMins = Integer.parseInt(request.getParameter("goal_mins"));
			goalMins = goalMins + (goalHours*60);
			GoalsDao gDao = new GoalsDao();
			if (gDao.goalAdd(new Goals(goalName, goalTag, goalMins, userUuid))) { // 登録成功
				System.out.println("目標の登録が成功しました。");
			}
			else { // 登録失敗
				System.out.println("目標の登録が失敗しました。");
				}
			response.sendRedirect("/NYASTER/TopPage");
			
			
			// リアクションの登録処理を行う
		} else if (request.getParameter("select").equals("ナイス")) {
			
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String postId = request.getParameter("postId");
			String userId = request.getParameter("userId");
			System.out.println(postId);
			
			// 登録処理を行う
			ReactionsDao reDao = new ReactionsDao();
			Reactions p = new Reactions();
			p.setPost_id(postId);
			Reactions u = new Reactions();
			u.setUser_uuid(userId);
			boolean result = reDao.check(u,p);
			request.setAttribute("check", result);
			if (result == false) {
				if (reDao.Reactioninsert(new Reactions(userId,postId))) {

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
		}
	}

}
