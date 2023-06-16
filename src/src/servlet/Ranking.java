package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/NYASTER/Login");
			return;
		}
		// 結果をページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
				dispatcher.forward(request, response);
    }


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String tag = request.getParameter("tag");
//			System.out.println(request.getParameterValues(tag).length);
			System.out.println(tag);
//			//if (tag != null) {
//		        for (String button : tag) {
//		// リクエストパラメータを取得する
//		request.setCharacterEncoding("UTF-8");
//		String all = request.getParameter("all");
//		String sport = request.getParameter("sport");
//		String book = request.getParameter("book");
//		String study = request.getParameter("study");
//		String other = request.getParameter("other");
//		
//		System.out.println(all);
//		System.out.println(sport);
//		System.out.println(book);
//		System.out.println(study);
//		System.out.println(other);
//		
//		
//	}
//}
//
//			RankingDao raDao = new RankingDao();
//			List<Rankings> rankingList = raDao.ranking(tag);
//
//			// 検索結果をリクエストスコープに格納する
//			request.setAttribute("rankingList", rankingList);
//
//			// 結果をページにフォワードする
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
//			dispatcher.forward(request, response);
//			
	}
}
