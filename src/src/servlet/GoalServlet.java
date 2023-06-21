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

import DAO.GoalsDao;
import model.Goals;

/**
 * Servlet implementation class GoalServlet
 */
@WebServlet("/GoalServlet")
public class GoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetメソッドはサーブレットを実行してからページを読み込む前にする処理を書く！
		// →　読み込んだ段階で表示したいデータを取得しよう

		// セッションスコープからログイン中のユーザのUUIDを取得する
		HttpSession session = request.getSession();
		String userUuid = (String)session.getAttribute("id");

		// 目標の一覧を表示する
		// ユーザの目標データを取得し、リストをリクエストスコープに格納する。
		GoalsDao gDao = new GoalsDao();
		List<Goals> goalList = gDao.goalShowUser(userUuid);

		// 結果をリクエストスコープに格納する
		request.setAttribute("goalList", goalList);

		// 目標ページにフォワードする. フォワードするときはリクエストスコープに入れたデータも一緒に持っていく！
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUserModal.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログイン中のユーザのUUIDを取得する
		HttpSession session = request.getSession();
		String userUuid = (String)session.getAttribute("id");

		// 【追加処理】
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		// JSPのフォーム内で「name="goal_name"」で指定されている箇所の文字列を取得して, String型のgoalName変数に代入する
		String goalName = request.getParameter("goal_name");

		// JSPのフォーム内で「name="goal_tag"」で指定されている箇所の文字列を取得して, String型のgoalTag変数に代入する
		String goalTag = request.getParameter("goal_tag");

		// 以下同様に、時間・分のデータも取得して変数に代入する。（時間・分は整数なのでint型の変数に代入する）
		int goalHours = Integer.parseInt(request.getParameter("goal_hours"));
		int goalMins = Integer.parseInt(request.getParameter("goal_mins"));

		// データベースへの保存は分で統一したいので、「時間」として取得した変数をふんに変換して足し合わせる
		goalMins = goalMins + (goalHours*60);

		// GoalsDaoクラスをgAddDaoという名前でインスタンス化する(名前は自由に決めてOKだけど後でわかりやすいように！)
		GoalsDao gAddDao = new GoalsDao();
		// Goalsクラス（Beans）をgoalという名前でインスタンス化する. インスタンス化するときは引数としてデータを渡す
		Goals goal = new Goals(goalName, goalTag, goalMins, userUuid/* 引数を書く */);
		// gDao(GoalｓDaoクラス)のgoalAddメソッドに上の行で作った「goal」インスタンス(Beans)を引数として渡して実行する

		// 実行したメソッドの戻り値（成功か失敗か）をboolean型（trueかfalse）の変数goalAddTrueOrFalseに格納する。
		boolean goalAddTrueOrFalse = gAddDao.goalAdd(goal);
		// if文で成功か失敗かを判定して処理を分ける。
		if(goalAddTrueOrFalse == true) { //成功した場合の処理
			System.out.println("目標の追加に成功しました");
		} else { //失敗した場合の処理
			System.out.println("目標の追加に失敗しました");
		}
		// 成功しても失敗してもページをリロードするために同じページリダイレクトする
		response.sendRedirect("/NYASTER/GoalServlet");

		// === 【追加処理】終わり ===

		// 【削除処理】
		// 削除する目標のUUID（POST_ID）を取得する(どの投稿を削除したら良いかDAOに教えなきゃいけないから！)
		// ヒント1：doGetメソッドで表示した目標データが投稿のUUID（POST_ID）を持ってる

		// ヒント2:ページ上でどの目標を削除するか選択して送るにはどうしたらいいか考える

		// GoalsDaoクラスをgDelDaoという名前でインスタンス化する(名前は自由に決めてOKだけど後でわかりやすいように！)
		GoalsDao gDelDao = new GoalsDao();
		List<Goals> goalList = gDelDao.goalShowUser(userUuid);

		// インスタンス化GoalsDaoクラスのインスタンス→ここでは「gDelDao」の中にあるdeleteメソッドを呼び出す。
		gDelDao.delete(uuid);
		// 呼び出すときには引数でUUIDを渡す必要がある→ さっき用意した引数を()内に書いて送ろう！
		Goals goal = new Goals(goalName, goalTag, goalMins, userUuid/* 引数 */);

		// 実行したメソッドの戻り値（成功か失敗か）をboolean型（trueかfalse）の変数goalDelTrueOrFalseに格納する。
		boolean goalDelTrueorFalse = gDelDao.goalDel(goal);
		// if文で成功か失敗かを判定して処理を分ける。
		if(goalDelTrueorFalse == true/* ここに条件式を書く */) {
			System.out.println("目標の削除に成功しました");//成功処理した時の処理
		} else {
			System.out.println("目標の削除に失敗しました");//成功処理した時の処理
		}
		// 成功しても失敗してもページをリロードするために同じページリダイレクトする
		response.sendRedirect("/NYASTER/GoalServlet");
		// === 【削除処理】終わり ===


	}
}
