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

		// 引数paramで検索項目を指定し、検索結果のリストを返す
		public List<Comments> select(Comments param) {
			Connection conn = null;
			List<Comments> commentList = new ArrayList<Comments>();

			try {
		// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

		// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

		// SQL文を準備する
			String sql = "select * from COMMENTS WHERE COMMENT_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
			if (param.getComment_id() != null) {
				pStmt.setString(1, "%" + param.getComment_id() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}

    	// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
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

		// 結果を返す
			return commentList;
		}

	//【2 追加はコメントID、コメント内容、ユーザUUID、投稿IDで追加 insert into】






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
				String sql = "delete from nyastar where COMMENT_ID=?";
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
