//rankingを保持するBeans

package model;
import java.io.Serializable;

public class Rankings implements Serializable {

	private String userName; //投稿したユーザの名前 (画面表示用)
	private int ganbariTime; //がんばり時間（分）
	
	

	// 投稿取得用
	public Rankings( String userName, int gTime) {
		
		this.userName = userName;
		this.ganbariTime = gTime;
		
	}

//	getter
	
	public String getUserName() {return userName;}
	public int getGanbariTime() {return ganbariTime;}
	
	
// 	setter
	
	public void setGanbariTime(int ganbariTime) {this.ganbariTime = ganbariTime;}
	
	
}