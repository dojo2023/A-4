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

import DAO.RankingDao;
import model.Rankings;

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




		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String all = request.getParameter("all");
		String sport = request.getParameter("sport");
		String book = request.getParameter("book");
		String study = request.getParameter("study");
		String other = request.getParameter("other");
		
		System.out.println(all);
		System.out.println(sport);
		System.out.println(book);
		System.out.println(study);
		System.out.println(other);
		
		// 検索処理を行う
		
		RankingDao raDao = new RankingDao();
		List<Rankings> rankingList = raDao.ranking(all,sport,book,study,other);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("rankingList", rankingList);

		// 結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
		dispatcher.forward(request, response);
	}
}
