package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;


public class AccountsDao {
		public User isLoginOK(String id, String pw) {
			Connection conn = null;
			User us= new User();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");
				String sql = "select * from ACCOUNTS where USER_ID = ? and PASSWORD = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1, id);
				pStmt.setString(2, pw);

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
				while(rs.next()) {
					us.setUser_uuid(rs.getString("User_uuid"));
					us.setUser_id(rs.getString("User_id"));
					us.setUser_name(rs.getString("User_name"));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				us = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				us = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						us = null;
					}
				}
			}
			return us;

		}

// ユーザーIDに重複がないかの確認
		public String check(User accounts) {
			Connection conn = null;
			boolean result = false;
			String str = null;
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

				// SQL文を準備する
				String sql = "select * from ACCOUNTS where user_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
//							pStmt.setString(1, accounts.getUser_uuid());
					pStmt.setString(1, accounts.getUser_id());
//							pStmt.setString(3, accounts.getUser_name());
//							pStmt.setString(4, accounts.getPassword());

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


		// 引数accountsで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(User accounts) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

				// SQL文を準備する
				String sql = "insert into ACCOUNTS values (?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setString(1, accounts.getUser_uuid());
					pStmt.setString(2, accounts.getUser_id());
					pStmt.setString(3, accounts.getUser_name());
					pStmt.setString(4, accounts.getPassword());
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



//プロフィール編集
		// 引数で指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(String userUuid,String userId,String userName,String password) {
			Connection conn = null;
			boolean result = false;

			try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyaster", "sa", "");

			// SQL文を準備する
			String sql = "update ACCOUNTS set USER_UUID=?, USER_ID=? , USER_NAME=?, PASSWORD=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

				pStmt.setString(1, userUuid);
				pStmt.setString(2, userId);
				pStmt.setString(3, userName);
				pStmt.setString(4, password);


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



//プロフィール削除
//引数で指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(String userUuid) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "delete from ACCOUNTS where USER_UUID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, userUuid);

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

	//ユーザのUUIDからIDと名前を取得
	public User showUser(String id) {
		Connection conn = null;
		User user = new User();

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "SELECT *"
					+ "FROM ACCOUNTS"
					+ "WHERE USER_UUID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				user.setUser_uuid(rs.getString("USER_UUID"));
				user.setUser_uuid(rs.getString("USER_ID"));
				user.setUser_uuid(rs.getString("USER_NAME"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			user = null;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
			user = null;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					user = null;
				}
			}
		}

		return user;
	}
}

