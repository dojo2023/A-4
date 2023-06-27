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

			String sql = "SELECT USER_NAME, USER_ID, SUM(GANBARI_TIME) AS TOTAL_GANBARI_TIME "
					+ "FROM POSTS "
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID "
					+ "JOIN GOALS ON POSTS.GOAL_ID = GOALS.GOAL_ID "
					+ "WHERE POST_TIME >= (NOW() - INTERVAL 7 DAY) ";
			ResultSet rs;

			if (tag.equals("累計")) {
				sql += "GROUP BY POSTS.USER_UUID ORDER BY SUM(GANBARI_TIME) DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				rs = pStmt.executeQuery();
			} else {
				sql += "AND GENRE_TAG=? "
						+ "GROUP BY POSTS.USER_UUID ORDER BY SUM(GANBARI_TIME) DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1,tag);
				rs = pStmt.executeQuery();
			}

			while (rs.next()) {

				double doubleGanbariHours = Math.floor(rs.getInt("TOTAL_GANBARI_TIME") / 60.0);
				int ganbariHours = (int)doubleGanbariHours; // long型からint型に変換
				int ganbariMins = rs.getInt("TOTAL_GANBARI_TIME") % 60; // 残りの分数を計算


				Rankings ranking = new Rankings(
				rs.getString("USER_NAME"),
				rs.getString("USER_ID"),
				rs.getInt("TOTAL_GANBARI_TIME"),
				ganbariHours,
				ganbariMins
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





