package servlet;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.AccountsDao;
import model.User;


/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// アカウント登録ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String uuid = request.getParameter("UUID");
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		String name = request.getParameter("NAME");

		String msg;

		// 登録処理を行う
				AccountsDao rDao = new AccountsDao();

				if (rDao.insert(new User(uuid, id, pw, name))!= null) { // 登録成功
					// request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/simpleBC/MenuServlet"));
					System.out.println("登録が成功しました。");
					response.sendRedirect("/simpleBC/LoginServlet");
				}
				else {
					String errorMsg = "ログインに失敗しました。";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
					dispatcher.forward(request, response);
				}


	}
}
