package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Goals
 */
@WebServlet("/Goal")
public class Goal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> goal; // 目標を格納するリスト
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Goal() {
        super();
        goal = new ArrayList<String>(); // 目標を格納するリストを初期化
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
/* 目標リストを表示する
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>目標リスト</h2>");
        response.getWriter().println("<ul>");
        for (String goal : goals) {
        response.getWriter().println("<li>" + goal + "</li>");
        response.getWriter().println("</ul>");
        response.getWriter().println("</body></html>");
        }
*/
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォームから送信された目標をリストに追加する
        String goal = request.getParameter("goal");


        // 目標リストを表示する
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

















