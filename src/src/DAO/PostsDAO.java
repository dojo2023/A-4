package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bc;
import model.Posts;

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

	//全ての投稿を取得
	public List<Posts> postShow() {
		Connection conn = null;
		List<Posts> postList = new ArrayList<Posts>(); //Postsのオブジェクトを格納する用のリスト

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			//
			String sql = "SELECT POST_ID, USER_NAME, POST_MESSAGE, GANPARI_TIME, GENRE_TAG, GOAL_NAME, POST_TIME"
					+ "FROM POSTS"
					+ "JOIN ACCOUNTS ON POSTS.USER_UUID = ACCOUNTS.USER_UUID"
					+ "JOIN GOALS ON POSTS.USER_UUID = GOALS.USER_UUID"
					+ "ORDER BY POST_TIME;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Posts card = new Posts(
				rs.getString("POST_ID"),
				rs.getString("POST_USER_NAME"),
				rs.getString("POST_MESSAGE"),
				rs.getInt("GANPARI_TIME"),
				rs.getString("GENRE_TAG"),
				rs.getString("GOAL_NAME"),
				rs.getString("POST_TIME")
				);
				postList.add(card);
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

	// 名刺データを更新するメソッド
	// 引数postで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Bc post) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");
			String sql = "UPDATE BC SET NAME=?, COMPANY=?, DEPARTMENT=?, POSITION=?, ADDRESS=?, EMAIL=?, PHONE_NUMBER=?, POST_CODE=?, MEMO=? WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, post.getName());
			pStmt.setString(2, post.getCompany());
			pStmt.setString(3, post.getDepartment());
			pStmt.setString(4, post.getPosition());
			pStmt.setString(5, post.getAddress());
			pStmt.setString(6, post.getEmail());
			pStmt.setString(7, post.getPhone_number());
			pStmt.setString(8, post.getPost_code());
			pStmt.setString(9, post.getMemo());

			pStmt.setString(10, post.getId());


			try {
			    // 更新処理の実行
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (Exception e) {
			    e.printStackTrace();
			    // 例外の処理またはエラーメッセージの表示など
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

	//　名刺データを削除するメソッド
	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(String id) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			String sql = "delete from BC where ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

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
