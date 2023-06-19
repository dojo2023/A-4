package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentsDao;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	   // 登録ページにフォワードする
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
			dispatcher.forward(request, response);
	    }

      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	  //リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String comment_id = request.getParameter("COMMENT_ID");
		String comment_content = request.getParameter("COMMENT_CONTENT");
		String user_uuid = request.getParameter("USER_UUID");
		String post_id = request.getParameter("POST_ID");

		// 登録処理を行う
		CommentsDao cDao = new CommentsDao();
		if (cDao.insert(comment_id, comment_content,user_uuid,post_id)) {	// 登録成功
			System.out.println("コメントしました");
		}
		else {												// 登録失敗
			System.out.println("コメントできませんでした");
		}


		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
}
}

