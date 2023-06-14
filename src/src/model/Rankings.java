//rankingを保持するBeans

package model;
import java.io.Serializable;

public class Rankings implements Serializable {

	private String userName; //投稿したユーザの名前 (画面表示用)
	private int ganbariTime; //がんばり時間（分）
	private String ganbariTag; //投稿に表示するタグ
	

	

	// 投稿取得用
	public Rankings( String userName, int gTime, String gTag) {
		
		this.userName = userName;
		this.ganbariTime = gTime;
		this.ganbariTag = gTag;
	}

//	getter
	
	public String getUserName() {return userName;}
	public int getGanbariTime() {return ganbariTime;}
	public String getGanbariTag() {return ganbariTag;}

	
// 	setter
	
	public void setGanbariTime(int ganbariTime) {this.ganbariTime = ganbariTime;}
	public void setGanbariTag(String ganbariTag) {this.ganbariTag = ganbariTag;}
	
}