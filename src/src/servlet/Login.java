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
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String hashedPw =  PwHashed.hashPassword(pw);

				// DAOを生成し、User型の戻り値を格納する。
				AccountsDao iDao = new AccountsDao();
				String uuid = iDao.isLoginOK(id, pw);

				if (uuid != null) {	// ログイン成功
					System.out.println(id + "さんがログインに成功しました。");
					// セッションスコープにUUIDを格納する
					HttpSession session = request.getSession();
					session.setAttribute("id", uuid);

					// トップページサーブレットにリダイレクトする
					response.sendRedirect("/NYASTER/TopPage");
				}

				else {
					System.out.println(id + "さんがログインに失敗しました。");
					String errorMsg = "ログインに失敗しました。";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}
	}


}
