package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reactions;

public class ReactionsDao {
	public boolean Reactioninsert(Reactions reaction) {
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "insert into REACTIONS values (?, ?, ?, ?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			pStmt.setString(1, reaction.getReaction_id());	
			pStmt.setInt(2, reaction.getGood());
			pStmt.setString(3, reaction.getPost_id());
			pStmt.setTimestamp(4, reaction.getReaction_time());
			pStmt.setString(5, reaction.getUser_uuid());
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
	public List<Reactions> Reactionselect() {
		Connection conn = null;
		List<Reactions> reactionList = new ArrayList<Reactions>(); //Postsのオブジェクトを格納する用のリスト

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			//
			String sql = "SELECT SUM (GOOD) WHERE POST_ID = ?";
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Reactions reaction = new Reactions(
				
				rs.getString("REACTION_ID"),
				rs.getInt("GOOD"),
				rs.getString("POST_ID")
				);
				reactionList.add(reaction);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			reactionList = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			reactionList = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					reactionList = null;
				}
			}
		}

		return reactionList;
	}
	public boolean Reactionupdate(Reactions reaction) {
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "UPDATE REACTIONS SET GOOD=? REACTIONTIME=? WHERE REACTION_ID=?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			pStmt.setString(1, reaction.getReaction_id());	
			pStmt.setInt(2, reaction.getGood());
			pStmt.setTimestamp(3, reaction.getReaction_time());
			pStmt.setString(4, reaction.getPost_id());
			
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
}
	



