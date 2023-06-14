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
			public boolean goalsAdd(Goals goal) {
				Connection conn = null;
				boolean result = false;

				try {
					Class.forName("org.h2.Driver");
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
					String sql = "INSERT INTO GOALS VALUES (?, ?, ?, ?, ?, ?)";

					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, goal.getGoalId());
					pStmt.setString(2, goal.getGoalName());
					pStmt.setString(3, goal.getGenreTag());
					pStmt.setString(4, goal.getGoalTime());
					pStmt.setString(5, goal.getGoalDate());
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

			//特定のユーザの目標を取得する(ユーザページ)
			public List<Goals> postShowUser(String uuid) {
				Connection conn = null;
				List<Goals> goalList = new ArrayList<Goals>(); //Goalsのオブジェクトを格納する用のリスト

				try {
					Class.forName("org.h2.Driver");
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

					//
					String sql = "SELECT GOAL_ID, GOAL_NAME, GENRE_TAG, GOAL_TIME, GOAL_DATE, USER_UUID"
							+ "FROM GOALS"
							+ "JOIN ACCOUNTS ON GOALS.USER_UUID = ACCOUNTS.USER_UUID"
							+ "JOIN GOALS ON POSTS.USER_UUID = GOALS.USER_UUID"
							+ "WHERE GOAL.USER_UUID=?" //ユーザIDを指定する
							+ "ORDER BY GOAL_TIME;";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					ResultSet rs = pStmt.executeQuery();

					while (rs.next()) {
						Goals goal = new Goals(
						rs.getString("GOAL_ID"),
						rs.getString("GOAL_NAME"),
						rs.getString("GENRE_TAG"),
						rs.getString("GOAL_TIME"),
						rs.getString("GOAL_DATE"),
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

			// 目標情報を更新する
			public boolean update(Goals goal) {
				Connection conn = null;
				boolean result = false;

				try {
					Class.forName("org.h2.Driver");
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
					String sql = "UPDATE POSTS SET GOAL_ID=?, GOAL_NAME=?, GENRE_TAG=? GOAL_TIME=？ GOAL_DATE=？  WHERE USER_UUID=？)";

					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, goal.getGoalId());
					pStmt.setString(2, goal.getGoalName());
					pStmt.setString(3, goal.getGenreTag());
					pStmt.setString(4, goal.getGoalTime());
					pStmt.setString(5, goal.getGoalDate());
					pStmt.setString(6, goal.getUserUuid());

					try {
						if (pStmt.executeUpdate() == 1) {
							result = true;
						}
					} catch (Exception e) {
					    e.printStackTrace();
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

			// 目標情報を削除する
			public boolean delete(String uuid) {
				Connection conn = null;
				boolean result = false;

				try {
					Class.forName("org.h2.Driver");
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

					String sql = "DELETE FROM GOALS WHERE USER_UUID=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, uuid);

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
