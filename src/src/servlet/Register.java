package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountsDao;
import model.PwHashed;
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
		UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成
		String uuidString = uuid.toString();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String hashpw = PwHashed.hashPassword(pw);

		System.out.println("UUID" + uuidString + "  ID" +  id + "  NAME" + name + "  PW" + pw);

		// 登録処理を行う
				AccountsDao rDao = new AccountsDao();
				User u = new User();
				u.setUser_id(id);
				String str = rDao.check(u);
				if (str == null) {
					if (rDao.insert(uuidString, id, name, hashpw)) { // 登録成功
						// request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/simpleBC/MenuServlet"));
						System.out.println("登録が成功しました。");
						response.sendRedirect("/NYASTER/Login");

					}
					else {
						String errorMsg = "登録に失敗しました。";
						System.out.println("登録に失敗しました。");
						request.setAttribute("errorMsg", errorMsg);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
						dispatcher.forward(request, response);
					}
				}
				else {
					String errorMsg = "登録に失敗しました。";
					System.out.println("登録に失敗しました。");
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
					dispatcher.forward(request, response);
				}
	}

}
