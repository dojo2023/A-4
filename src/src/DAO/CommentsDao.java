package DAO;
import java.sql.Connection;//DBMSへの切断や接続
import java.sql.DriverManager;//DBMSへの接続準備
import java.sql.PreparedStatement;//SQLの送信
import java.sql. ResultSet;//DBMSから検索結果を受け取る
import java.sql.SQLException;//DBエラー情報を提供
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import model.Comments;

public class CommentsDao {

	//【1 表示は投稿IDで表示 select】

		// 検索項目を指定し、検索結果のリストを返す
		public List<Comments> select(String post_id) {
			Connection conn = null;
			List<Comments> commentList = new ArrayList<Comments>();

			try {
		// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

		// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

		// SQL文を準備する
			String sql = "SELECT COMMENT_CONTENT,COMMENT_TIME,USER_NAME "
					+ "FROM COMMENTS "
					+ "JOIN ACCOUNTS ON POST.USER_UUID = ACCOUNTS.USER_UUID "
					+ "WHERE POST_ID = ? "
					+ "ORDER BY COMMENT_TIME DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
				pStmt.setString(1,post_id);


    	// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArryList（JavaBeans）に入れなおしている
			while (rs.next()) {
				Comments com = new Comments();
				com.setComment_id(rs.getString("COMMENT_ID"));
				com.setComment_content(rs.getString("COMMENT_CONTENT"));
				com.setPost_id(rs.getString("POST_ID"));
				com.setComment_time(rs.getTimestamp("COMMENT_TIME"));
				commentList.add(com);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			commentList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			commentList = null;
		}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						commentList = null;
					}
				}
			}

			// 結果を返す
			return commentList;
		}




	//【2 追加はコメントID、コメント内容、UUID,投稿IDで追加 insert into】


	// 引数paramで検索項目を指定し、検索結果のリストを返す
	// 引数commentで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(String comment_content,String user_uuid, String post_id) {
		Connection conn = null;
		boolean result = false;

		UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成
		String uuidString = uuid.toString();

		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());



	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

		// SQL文を準備する
		String sql = "insert into Comments values (?, ?, ?, ?, ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,uuidString);
		pStmt.setString(2,comment_content);
		pStmt.setString(3,post_id);
		pStmt.setTimestamp(4,ts);
		pStmt.setString(5,user_uuid);

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
		result = true;
		     System.out.println("データの挿入/更新が成功しました。");
		} else {
			 System.out.println("データの挿入/更新が失敗しました。");
		}
		}
		catch (SQLException e) {
		     e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
       // データベースを切断
		if (conn != null) {
		try {
			conn.close();
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		}
		}
		// 結果を返す
		return result;

}


	//【3 削除はコメントIDで削除 delete】

		public boolean delete(String comment_id) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

				// SQL文を準備する
				String sql = "delete from comments where COMMENT_ID=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1,comment_id);

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}
}



