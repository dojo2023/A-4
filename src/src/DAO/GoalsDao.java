package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Goals;

public class GoalsDao {
	// 新規の目標を追加する
	public boolean goalAdd(Goals goal) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "INSERT INTO GOALS (GOAL_ID, GOAL_NAME, GENRE_TAG, GOAL_TIME, GOAL_DATE, USER_UUID, ACHIEVEMENT_TIME) "
					+ "VALUES (?, ?, ?, ?, ?, ?, 0)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, goal.getGoalId());
			pStmt.setString(2, goal.getGoalName());
			pStmt.setString(3, goal.getGenreTag());
			pStmt.setInt(4, goal.getGoalTime());
			pStmt.setTimestamp(5, goal.getGoalDate());
			pStmt.setString(6, goal.getUserUuid());

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

	//ユーザの目標を取得する
	public List<Goals> goalShowUser(String uuid) {
		Connection conn = null;
		List<Goals> goalList = new ArrayList<Goals>(); //Goalsのオブジェクトを格納する用のリスト

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "SELECT GOAL_ID, GOAL_NAME, GENRE_TAG, GOAL_TIME, ACHIEVEMENT_TIME, GOAL_DATE, USER_UUID "
					+ "FROM GOALS "
					+ "WHERE USER_UUID=? " //ユーザIDを指定する
					+ "ORDER BY GOAL_DATE;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uuid);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Goals goal = new Goals(
				rs.getString("GOAL_ID"),
				rs.getString("GOAL_NAME"),
				rs.getString("GENRE_TAG"),
				rs.getInt("GOAL_TIME"),
				rs.getInt("ACHIEVEMENT_TIME"),
				rs.getTimestamp("GOAL_DATE"),
				rs.getString("USER_UUID")
				);
				goalList.add(goal);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			goalList = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			goalList = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					goalList = null;
				}
			}
		}
		return goalList;
	}


	// がんばり時間を追加
	public boolean addTime(String id, int time) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "UPDATE GOALS "
					+ "SET ACHIEVEMENT_TIME = ACHIEVEMENT_TIME + ? "
					+ "WHERE GOAL_ID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, time);
			pStmt.setString(2, id);
			System.out.println("クエリを実行します");

			try {
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (Exception e) {
				System.out.println("エラー4が発生しました");
			    e.printStackTrace();
			}
		}
		catch (SQLException e) {
			System.out.println("エラー1が発生しました");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("エラー2が発生しました");
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					System.out.println("エラー3が発生しました");
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 目標情報を削除する
	public boolean delete(String goalId) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "DELETE FROM GOALS "
					+ "WHERE GOAL_ID=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, goalId);

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
