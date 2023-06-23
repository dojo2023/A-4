//rankingを保持するBeans

package model;
import java.io.Serializable;

public class Rankings implements Serializable {

	private String userName; //投稿したユーザの名前 (画面表示用)
	private int ganbariTime; //がんばり時間（分）
	private int ganbariTimeHours; //がんばり時間（時間）
	private int ganbariTimeMins; //がんばり時間（残りの分）

	// 投稿取得用
	public Rankings( String userName, int gTime, int ganbariTimeHours, int ganbariTimeMins) {
		this.userName = userName;
		this.ganbariTime = gTime;
		this.ganbariTimeHours = ganbariTimeHours;
		this.ganbariTimeMins = ganbariTimeMins;
	}

//	getter
	public String getUserName() {return userName;}
	public int getGanbariTime() {return ganbariTime;}
	public int getGanbariTimeHours() {return ganbariTimeHours;}
	public int getGanbariTimeMins() {return ganbariTimeMins;}

// 	setter
	public void setGanbariTime(int ganbariTime) {this.ganbariTime = ganbariTime;}
	public void setGanbariTimeHours(int ganbariTimeHours) {this.ganbariTimeHours = ganbariTimeHours;}
	public void setGanbariTimeMins(int ganbariTimeMins) {this.ganbariTimeMins = ganbariTimeMins;}


}