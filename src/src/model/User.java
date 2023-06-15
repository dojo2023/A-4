package model;
import java.io.Serializable;
//ユーザ情報（user_uuid,user_id,user_name,password）を保持するBeans
public class User implements Serializable {
	private String user_uuid; //UUID
	private String user_id;   //ユーザID
	private String user_name; //ユーザ名
	private String password;  //パスワード

	public User(String user_uuid,String user_id,String user_name,String password) { //アカウント追加用
		this.user_uuid = user_uuid;
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
	}

	 public User(String user_id,String password) { //ログイン判定用
			this.user_id = user_id;
			this.password = password;
	}

	 public User() {}

    public String getUser_uuid() {return user_uuid;}
    public String getUser_id() {return user_id;}
    public String getUser_name() {return user_name;}
    public String getPassword() {return password;}

    public void setUser_uuid(String user_uuid){this.user_uuid = user_uuid;}
    public void setUser_id(String user_id){this.user_id = user_id;}
    public void setUser_name(String user_name){this.user_name = user_name;}
    public void setPassword(String password){this.password = password;}
}
