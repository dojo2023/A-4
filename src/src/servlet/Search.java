package servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountsDao;
import model.User;

/**
 * Servlet implementation class SearchServlet
 */

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
	//	HttpSession session = request.getSession();
	//	if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/NYASTER/Login");
		//	return;
	//	}


		// 検索ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
				dispatcher.forward(request, response);
			}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       //リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		System.out.println (request.getParameter("REGIST"));
		String searchword = request.getParameter("searchword");
		System.out.println(searchword);

		// 検索処理を行う
		AccountsDao acDao = new AccountsDao();
		//Beanを使わずに直接検索条件を作成
		List<User> seList =  acDao.select(searchword);


		// 検索結果をリクエストスコープに格納する
		request.setAttribute("seList", seList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);
	}
}

