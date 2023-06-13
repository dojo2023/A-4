package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountsDao;
import model.PwHashed;
import model.User;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doget(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        // ログインページにフォワードする
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("ID");
				String pw = request.getParameter("PW");
				String hashedPw =  PwHashed.hashPassword(pw);

				// ログイン処理を行う
				AccountsDao iDao = new AccountsDao();
				if (iDao.isLoginOK(new User(id, hashedPw)) != null) {	// ログイン成功
					// セッションスコープにIDを格納する
					HttpSession session = request.getSession();
					session.setAttribute("id", id);

					// トップページサーブレットにリダイレクトする
					response.sendRedirect("/NYASTAR/TopPage");
				}
				else {
					String errorMsg = "ログインに失敗しました。";
					request.setAttribute("errorMsg", "IDまたはパスワードが違います");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}


	}


}
