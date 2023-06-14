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

import DAO.PostsDAO;
import model.Posts;

/**
 * Servlet implementation class TopPage
 */
@WebServlet("/TopPage")
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインしていない場合ログインサーブレットにリダイレクト
		HttpSession session = request.getSession();
		if ((String)session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}

//		String id = (String)session.getAttribute("id");
//		User loginUser = new User();

		// IDからユーザ情報を問い合わせる
//		AccountsDao aDao = new AccountsDao();
//		loginUser = uDao.show(new User(id));

		//　取得したユーザ情報からユーザ名を取り出し、リクエストスコープに格納する
//		request.setAttribute("userName", loginUser.getName());

		// 投稿データを全件表示する
		PostsDAO pDao = new PostsDAO();
		List<Posts> postList = pDao.postShow();
		request.setAttribute("postList", postList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
