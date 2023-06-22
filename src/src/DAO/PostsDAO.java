package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Posts;
import model.User;

public class PostsDAO {
	// 新規の投稿を追加する
	public boolean postAdd(Posts post) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "INSERT INTO POSTS VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, post.getId());
			pStmt.setString(2, post.getMsg());
			pStmt.setInt(3, post.getGanbariTime());
			pStmt.setString(4, post.getGoalId());
			pStmt.setString(5, post.getUserUUID());
			pStmt.setTimestamp(6, post.getPostTime());

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

	//全ての投稿を取得する(タイムライン)
	public List<Posts> postShow(String userUuid) {
		Connection conn = null;
		List<Posts> postList = new ArrayList<Posts>(); //Postsのオブジェクトを格納する用のリスト
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/NYASTAR", "sa", "");

			//
			String sql = "SELECT POST_ID,USER_NAME, POST_MESSAGE, GANBARI_TIME, GENRE_TAG, GOAL_NAME, GOAL_TIME, POST_TIME, ACHIEVEMENT_TIME, "
					+ "(SELECT COUNT(*) FROM REACTIONS WHERE POSTS.POST_ID=REACTIONS.POST_ID) AS REACTION_COUNTS, "
					+ "(SELECT COUNT(*) FROM REACTIONS WHERE POSTS.POST_ID=REACTIONS.POST_ID AND REACTIONS.USER_UUID=?) AS REACTION_CHECK "
					+ "FROM POSTS "
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID "
					+ "JOIN GOALS ON POSTS.GOAL_ID = GOALS.GOAL_ID "
					+ "ORDER BY POST_TIME DESC;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userUuid);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// がんばり時間
				double doubleGanbariHours = Math.floor(rs.getInt("GANBARI_TIME") / 60.0);
				int ganbariHours = (int)doubleGanbariHours; // long型からint型に変換
				int ganbariMins = rs.getInt("GANBARI_TIME") % 60; // 残りの分数を計算

				// 目標時間
				double doubleGoalHours = Math.floor(rs.getInt("GOAL_TIME") / 60.0);
				int goalHours = (int)doubleGoalHours; // long型からint型に変換
				int goalMins = rs.getInt("GOAL_TIME") % 60; // 残りの分数を計算

				Posts post = new Posts(
					rs.getString("POST_ID"),
					rs.getString("USER_NAME"),
					rs.getString("POST_MESSAGE"),
					rs.getString("GENRE_TAG"),
					rs.getString("GOAL_NAME"),
					rs.getInt("GANBARI_TIME"),
					ganbariHours,
					ganbariMins,
					rs.getInt("GOAL_TIME"),
					goalHours,
					goalMins,
					rs.getInt("ACHIEVEMENT_TIME"),
					rs.getTimestamp("POST_TIME"),
					rs.getInt("REACTION_COUNTS"),
					rs.getInt("REACTION_CHECK")
					);
					postList.add(post);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			postList = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			postList = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					postList = null;
				}
			}
		}

		return postList;
	}

	//特定のユーザの投稿を取得する(ユーザページ)
	public List<Posts> postShowUser(String id,String userUuid) {
		Connection conn = null;
		List<Posts> postList = new ArrayList<Posts>(); //Postsのオブジェクトを格納する用のリスト

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			//
			String sql = "SELECT POST_ID, USER_NAME, POST_MESSAGE, GANBARI_TIME, GENRE_TAG, GOAL_NAME, POST_TIME, ACHIEVEMENT_TIME, "
					+ "(SELECT COUNT(*) FROM REACTIONS WHERE POSTS.POST_ID=REACTIONS.POST_ID) AS REACTION_COUNTS "
					+ "(SELECT COUNT(*) FROM REACTIONS WHERE POSTS.POST_ID=REACTIONS.POST_ID AND REACTIONS.USER_UUID=?) AS REACTION_CHECK "
					+ "FROM POSTS"
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID "
					+ "JOIN GOALS ON POSTS.GOAL_ID = GOALS.GOAL_ID "
					+ "WHERE POST.USER_UUID=? " //ユーザIDを指定する
					+ "ORDER BY POST_TIME DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userUuid);
			pStmt.setString(2, id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// がんばり時間
				double doubleGanbariHours = Math.floor(rs.getInt("GANBARI_TIME") / 60.0);
				int ganbariHours = (int)doubleGanbariHours; // long型からint型に変換
				int ganbariMins = rs.getInt("GANBARI_TIME") % 60; // 残りの分数を計算

				// 目標時間
				double doubleGoalHours = Math.floor(rs.getInt("GOAL_TIME") / 60.0);
				int goalHours = (int)doubleGoalHours; // long型からint型に変換
				int goalMins = rs.getInt("GOAL_TIME") % 60; // 残りの分数を計算

				Posts post = new Posts(
					rs.getString("POST_ID"),
					rs.getString("USER_NAME"),
					rs.getString("POST_MESSAGE"),
					rs.getString("GENRE_TAG"),
					rs.getString("GOAL_NAME"),
					rs.getInt("GANBARI_TIME"),
					ganbariHours,
					ganbariMins,
					rs.getInt("GOAL_TIME"),
					goalHours,
					goalMins,
					rs.getInt("ACHIEVEMENT_TIME"),
					rs.getTimestamp("POST_TIME"),
					rs.getInt("REACTION_COUNTS"),
					rs.getInt("REACTION_CHECK")
					);
					postList.add(post);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			postList = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			postList = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					postList = null;
				}
			}
		}

		return postList;
	}

	// 投稿情報を更新する
	public boolean update(Posts post) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
			String sql = "UPDATE POSTS SET POST_MESSAGE=?, GANBARI_TIME=?, GOAL_ID=? WHERE POST_ID=?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, post.getMsg());
			pStmt.setInt(2, post.getGanbariTime());
			pStmt.setString(3, post.getGoalId());
			pStmt.setString(4, post.getId());

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

	// 投稿を削除する
	public boolean delete(String id) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "DELETE FROM POSTS WHERE POST_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

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


// 合計活動時間
	public User totaltime(String uuid) {
		Connection conn = null;
		User userTotalTime = new User();

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			//
			String sql = "SELECT  USER_NAME, SUM(GANBARI_TIME) as TOTAL_GANBARI_TIME, "
					+ "FROM POSTS "
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID "
					+ "WHERE POSTS.USER_UUID= ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,uuid);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				userTotalTime.setUser_uuid(uuid);
				userTotalTime.setUser_name(rs.getString("USER_NAME"));
				userTotalTime.setTotalGanbariTime(rs.getInt("GANBARI_TIME"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			userTotalTime = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userTotalTime = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userTotalTime = null;
				}
			}
		}

		return userTotalTime;
	}
	
}

