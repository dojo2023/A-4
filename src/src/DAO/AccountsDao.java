package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class AccountsDao {
	// ログインできるならtrueを返す
		public String isLoginOK(String id,String pw) {
			Connection conn = null;
			boolean loginResult = false;
			String name=null;
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/nyastar", "sa", "");

				// SELECT文を準備する
				String sql = "select * from ACCOUNTS where USER_ID = ? and PASSWORD = ?";
				//全部まとめちまえ
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//SQL文が未完成だったからちゃんと値を入れる
				pStmt.setString(1,id);

				pStmt.setString(2,pw);



				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				while(rs.next()) {
					name=rs.getString("USER_NAME");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return name;
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
				if (accounts.getUser_uuid() != null && !accounts.getUser_uuid().equals("")) {
					pStmt.setString(1, accounts.getUser_uuid());
				}
				else {
					pStmt.setString(1, null);
				}
				if (accounts.getUser_id() != null && !accounts.getUser_id().equals("")) {
					pStmt.setString(2, accounts.getUser_id());
				}
				else {
					pStmt.setString(2, null);
				}
				if (accounts.getUser_name() != null && !accounts.getUser_name().equals("")) {
					pStmt.setString(3, accounts.getUser_name());
				}
				else {
					pStmt.setString(3, null);
				}
				if (accounts.getPassword() != null && !accounts.getPassword().equals("")) {
					pStmt.setString(4, accounts.getPassword());
				}
				else {
					pStmt.setString(4, null);
				}

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
