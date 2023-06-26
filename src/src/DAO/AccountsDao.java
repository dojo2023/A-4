package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;


public class AccountsDao {
	//ログイン判定
	public String isLoginOK(String id, String pw) {
		Connection conn = null;
		String uuid = null;
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
				uuid = rs.getString("USER_UUID");
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
		return uuid;

	}


	// アカウント追加
	public boolean insert(String uuid, String id, String name, String pw) {
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
				pStmt.setString(1, uuid);
				pStmt.setString(2, id);
				pStmt.setString(3, name);
				pStmt.setString(4, pw);
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
	public boolean update(String userUuid, String userId,String userName,String password) {
		Connection conn = null;
		boolean result = false;

		try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

		// SQL文を準備する
		String sql = "update ACCOUNTS "
				+ "set USER_ID=?, USER_NAME=?, PASSWORD=? "
				+ "WHERE USER_UUID = ?"; //WHERE句でどのユーザを削除するかを指定
		PreparedStatement pStmt = conn.prepareStatement(sql);
		// SQL文を完成させる
		pStmt.setString(1, userId);
		pStmt.setString(2, userName);
		pStmt.setString(3, password);
		pStmt.setString(4, userUuid);

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
	// 引数で指定されたIDのアカウントを削除し、成功したらtrueを返す
	public boolean delete(String userUuid) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			System.out.println("userUuid:" + userUuid);
			// SQL文を準備する
			String sql = "DELETE FROM ACCOUNTS "
					+ "WHERE USER_UUID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
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
		user.setUser_uuid(id);

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "SELECT USER_UUID, USER_ID, USER_NAME "
					+ "FROM ACCOUNTS "
					+ "WHERE USER_UUID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				user.setUser_id(rs.getString("USER_ID"));
				user.setUser_name(rs.getString("USER_NAME"));
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

	//ユーザのIDからUUIDをを取得
	public String showUserUuid(String id) {
		Connection conn = null;
		String uuid = null;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			String sql = "SELECT USER_UUID　"
					+ "FROM ACCOUNTS "
					+ "WHERE USER_ID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				uuid = rs.getString("USER_UUID");
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

		return uuid;
	}


	// ユーザーIDに重複がないかの確認
	public String check(User accounts) {
		Connection conn = null;
		String str = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する
			String sql = "select * from ACCOUNTS where user_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, accounts.getUser_id());

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


	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<User> search(String sq) {
		Connection conn = null;
		//インスタンス化されたUserしかリストに入らない
		List<User> seList = new ArrayList<User>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

			// SQL文を準備する　connとsqlがごっちゃになったものがpSmt
        	String sql = "select *"
        			+ " from ACCOUNTS"
        			+ " WHERE USER_ID LIKE ? OR USER_NAME LIKE ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + sq + "%");
			pStmt.setString(2, "%" + sq + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArryList（JavaBeans）に入れなおしている
			while (rs.next()) {
				User search = new User();
			    search.setUser_id(rs.getString("USER_ID"));
			    search.setUser_name(rs.getString("USER_NAME"))  ;
			    seList.add(search);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			seList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			seList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					seList = null;
				}
			}
		}

		// 結果を返す
		return seList;
	}

}

