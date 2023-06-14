//投稿情報(post_id,post_message,ganbari_time,goal_id,user_uuid,post_time)を保持するBeans

package model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Posts implements Serializable {

	private String id; //投稿のID（UUID）
	private String userUUID; //投稿したユーザのUUID (DB保存用)
	private String userName; //投稿したユーザの名前 (画面表示用)
	private String msg; //投稿のメッセージ
	private int ganbariTime; //がんばり時間（分）
	private String ganbariTag; //投稿に表示するタグ
	private String goalId; //設定した目標の名前 (DB保存用)
	private String goalName; //設定した目標の名前 (画面表示用)
	private Timestamp postTime; //投稿した日時


	// 投稿情報の新規登録. ユーザ名と目標名は取得してから格納する.
	public Posts(String userUUID, String msg, int gTime, String goalId) {
		UUID uuid = UUID.randomUUID(); // 投稿のUUIDを生成
		String uuidString = uuid.toString();

		this.id = uuidString;
		this.userUUID = userUUID;
		this.msg = msg;
		this.ganbariTime = gTime;
		this.goalId = goalId;

		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		this.postTime = ts;
	}

	// 投稿取得用
	public Posts(String id, String userName, String msg, int gTime, String gTag, String goalName, Timestamp ts) {
		this.id = id;
		this.userName = userName;
		this.msg = msg;
		this.ganbariTime = gTime;
		this.ganbariTag = gTag;
		this.goalName = goalName;
		this.postTime = ts;
	}

//	getter
	public String getId() {return id;}
	public String getUserName() {return userName;}

	public String getMsg() {return msg;}

	public int getGanbariTime() {return ganbariTime;}
	public String getGanbariTag() {return ganbariTag;}

	public String getGoalId() {return goalId;}
	public String getGoalName() {return goalName;}

	public Timestamp getPostTime() {return postTime;}
	public String getUserUUID() {return userUUID;}


// 	setter
	public void setMsg(String msg) {this.msg = msg;}
	public void setGanbariTime(int ganbariTime) {this.ganbariTime = ganbariTime;}
	public void setGanbariTag(String ganbariTag) {this.ganbariTag = ganbariTag;}
	public void setGoal(String goal) {this.goalName = goal;}
	public void setGoalId(String goalId) {this.goalId = goalId;}

}