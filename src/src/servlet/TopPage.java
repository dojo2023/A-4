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
import DAO.PostsDAO;
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

		String id = (String)session.getAttribute("id");

		// IDからユーザ情報を問い合わせる
		AccountsDao aDao = new AccountsDao();
		model.User loginUser = aDao.showUser(id); //ログインユーザの情報を取得

		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
		request.setAttribute("user", loginUser);

		// 投稿データを全件表示する
		PostsDAO pDao = new PostsDAO();
		List<Posts> postList = pDao.postShow();
		request.setAttribute("postList", postList);

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

		// 登録処理を行う
		PostsDAO pDao = new PostsDAO();
		if (pDao.postAdd(new Posts(userUuid, msg, mins, goalId))) { // 登録成功
//			request.setAttribute("result",　new Result("登録成功！", "レコードを登録しました。", "/simpleBC/MenuServlet"));
			System.out.println("登録は成功しました。");
		}
		else { // 登録失敗
//			request.setAttribute("result",　new Result("登録失敗！", "レコードを登録できませんでした。", "/simpleBC/MenuServlet"));
			System.out.println("登録は失敗しました。");
		}

		response.sendRedirect("TopPage");
//		dispatcher.forward(request, response);
	}

}
