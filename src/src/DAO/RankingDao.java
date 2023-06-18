package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rankings;

public class RankingDao {
	public List<Rankings> ranking(String tag) {
		Connection conn = null;

		List<Rankings> rankingList = new ArrayList<Rankings>(); //Rankingsのオブジェクトを格納する用のリスト

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "SELECT  USER_NAME, SUM(GANBARI_TIME) AS TOTAL_GANBARI_TIME "
					+ "FROM POSTS "
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID "
					+ "JOIN GOALS ON POSTS.GOAL_ID = GOALS.GOAL_ID ";

			ResultSet rs;

			if (tag.equals("累計")) {
				sql += "GROUP BY POSTS.USER_UUID ORDER BY SUM(GANBARI_TIME) DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				rs = pStmt.executeQuery();
			} else {
				sql += "WHERE GENRE_TAG=? "
						+ "GROUP BY POSTS.USER_UUID ORDER BY SUM(GANBARI_TIME) DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1,tag);
				rs = pStmt.executeQuery();
			}

			while (rs.next()) {
				Rankings ranking = new Rankings(
				rs.getString("USER_NAME"),
				rs.getInt("TOTAL_GANBARI_TIME")
				);
				rankingList.add(ranking);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			rankingList = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			rankingList = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					rankingList = null;
				}
			}
		}
		return rankingList;
	}
}





