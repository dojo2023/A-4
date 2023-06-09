package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountsDao;
import model.User;
import model.PwHashed


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doget(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
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
				String hashedPw = PasswordHashing.hashPassword(pw);

				// ログイン処理を行う
				AccountsDao iDao = new AccountsDao();
				if (iDao.isLoginOK(new Idpw(id, hashedPw))) {	// ログイン成功
					// セッションスコープにIDを格納する
					HttpSession session = request.getSession();
					session.setAttribute("id", id);

					// トップページサーブレットにリダイレクトする
					response.sendRedirect("/NYASTAR/Login");
				}
				else {									// ログイン失敗
					// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
					request.setAttribute("("ログイン失敗！", "IDまたはPWに間違いがあります。", "/NYASTAR/Login"));

					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/result.jsp");
					dispatcher.forward(request, response);
				}
			}

	}


}
