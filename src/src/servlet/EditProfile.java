package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountsDao;


/**
 * Servlet implementation class Comment
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        // ログインページにフォワードする
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
		dispatcher.forward(request, response);
    }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 //リクエストパラメータを取得する
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("USER_ID");
	String userName = request.getParameter("USER_NAME");
	String password = request.getParameter("PASSWORD");


	// 更新または削除を行う
	AccountsDao aDao = new AccountsDao();{

 	if (aDao.update(userId,userName,password)) {	// 更新成功
			System.out.println("更新しました");
				}
		else {												// 更新失敗
			System.out.println("更新できませんでした");
				}
	}

		 if (aDao.delete(userId)) {	// 削除成功
			System.out.println("削除しました");
				}
				else {						// 削除失敗
					System.out.println("削除できませんでした");


	}
	 }
}





















