package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reactions;

public class ReactionsDao {
	public boolean Reactioninsert(Reactions reaction) {
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "INSERT INTO REACTIONS VALUES (?, ?, ?, ?)";
			
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			System.out.println(reaction.getReaction_id());
			System.out.println(reaction.getPost_id());
			System.out.println(reaction.getReaction_time());
			System.out.println(reaction.getUser_uuid());
		
			pStmt.setString(1, reaction.getReaction_id());	
			pStmt.setString(2, reaction.getPost_id());
			pStmt.setTimestamp(3, reaction.getReaction_time());
			pStmt.setString(4, reaction.getUser_uuid());
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
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
		
	}
	
	
	public String check(Reactions u, Reactions p) {
		Connection conn = null;
		boolean result = false;
		String str = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "select * from REACTIONS where post_id = ? and user_uuid = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getPost_id());
			pStmt.setString(2, u.getUser_uuid());

			// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

			// SQL文を実行する
			if (rs.next()) {
				str = "abc";
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
		return str;
	}
		//リアクション削除
	public boolean delete(Reactions reaction) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "delete from REACTIONS where post_id = ? and user_uuid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,reaction.getPost_id());
			pStmt.setString(2,reaction.getUser_uuid());

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
	



