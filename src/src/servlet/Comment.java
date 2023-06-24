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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.CommentsDao;
import model.Comments;

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
  		HttpSession session = request.getSession();
  		String userUuid = (String)session.getAttribute("id");

    	//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		//コメントテーブルとのやり取り用DAO
		CommentsDao cDao = new CommentsDao();


		// 操作選択
		String select = request.getParameter("select");
		switch(select) {
			case "view":
				List<Comments> cmtList = cDao.select(request.getParameter("post_id"));
				ObjectMapper mapper = new ObjectMapper();
				try {
		            //JavaオブジェクトからJSONに変換
		            String cmtListJson = mapper.writeValueAsString(cmtList);
		            //JSONの出力
		            response.getWriter().write(cmtListJson);
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "nocache");
				response.setCharacterEncoding("utf-8");
				break;
			case "add":
				//フォームからコメント内容を取得する。
				String commentMsg = request.getParameter("cmt_msg");
				String postId = request.getParameter("post_id");
				Comments cmts = new Comments(commentMsg, userUuid, postId);
				if (cDao.insert(cmts)) { // 登録成功
					System.out.println("コメントしました");
				}
				else { // 登録失敗
					System.out.println("コメントできませんでした");
				}
				response.sendRedirect("/NYASTER/TopPage");
				break;
			case "delete":
				// 削除処理
				break;
			default:
				System.out.println("操作が選択されませんでした。");
		}
    }
}

