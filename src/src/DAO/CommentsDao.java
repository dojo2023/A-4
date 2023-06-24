package DAO;

import java.sql.Connection;//DBMSへの切断や接続
import java.sql.DriverManager;//DBMSへの接続準備
import java.sql.PreparedStatement;//SQLの送信
import java.sql. ResultSet;//DBMSから検索結果を受け取る
import java.sql.SQLException;//DBエラー情報を提供
import java.util.ArrayList;
import java.util.List;

import model.Comments;

public class CommentsDao {
//【1 表示は投稿IDで表示 select】
	// 検索項目を指定し、検索結果のリストを返す
	public List<Comments> select(String post_id) {
		Connection conn = null;
		List<Comments> commentList = new ArrayList<Comments>();

		try {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

		String sql = "SELECT COMMENT_ID, COMMENT_CONTENT, COMMENT_TIME, POST_ID, USER_NAME "
				+ "FROM COMMENTS "
				+ "JOIN ACCOUNTS ON COMMENTS.USER_UUID = ACCOUNTS.USER_UUID "
				+ "WHERE POST_ID = ? "
				+ "ORDER BY COMMENT_TIME DESC";

		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,post_id);
		ResultSet rs = pStmt.executeQuery();

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
	public boolean insert(Comments cmts) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "insert into Comments values (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,cmts.getComment_id());
			pStmt.setString(2,cmts.getComment_content());
			pStmt.setString(3,cmts.getPost_id());
			pStmt.setTimestamp(4,cmts.getComment_time());
			pStmt.setString(5,cmts.getUser_uuid());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
			result = true;
			     System.out.println("コメントをテーブルにデータを追加しました。");
			} else {
				 System.out.println("コメントをテーブルにデータを追加できませんでした。");
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



