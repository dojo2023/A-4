package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Reactions;

public class ReactionsDao {
	public boolean insert(Reactions card) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "insert into REACTIONS values (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
		if (card.getReaction_id() != null && !card.getReaction_id().equals("")) {
			pStmt.setString(1, card.getReaction_id());
		}
		else {
			pStmt.setString(1, null);
		}
		if (card.getGood() != null && !card.getGood()=="") {
			pStmt.setInt(2, card.getGood());
		}
		else {
			pStmt.setInt(2, null);
		}
		if (card.getDepartment() != null && !card.getDepartment().equals("")) {
			pStmt.setString(3, card.getDepartment());
		}
		else {
			pStmt.setString(3, null);
		}
		if (card.getPosition() != null && !card.getPosition().equals("")) {
			pStmt.setString(4, card.getPosition());
		}
		else {
			pStmt.setString(4, null);
		}
		if (card.getName() != null && !card.getName().equals("")) {
			pStmt.setString(5, card.getName());
		}
		else {
			pStmt.setString(5, null);
		}
		// SQL文を実行する
					if (pStmt.executeUpdate() == 1) {
						result = true;
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


