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
	private String ganbariTag; //投稿に表示するタグ
	private String goalId; //設定した目標の名前 (DB保存用)
	private String goalName; //設定した目標の名前 (画面表示用)
	private int ganbariTime; //がんばり時間（分）
	private int ganbariTimeHours; //がんばり時間（時間）
	private int ganbariTimeMins; //がんばり時間（残りの分）
	private int goalTime; //設定した目標時間（分）
	private int goalTimeHours; //設定した目標時間（時間）
	private int goalTimeMins; //設定した目標時間（残りの分）
	private int progress;
	private Timestamp postTime; //投稿した日時
	private int reactionCount;


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
	public Posts(String id, String userName, String msg, String gTag, String goalName, int ganbariTime, int ganbariTimeHours, int ganbariTimeMins, int goalTime, int goalTimeHours, int goalTimeMins, int progress, Timestamp ts, int reactionCount) {
		this.id = id;
		this.userName = userName;
		this.msg = msg;
		this.ganbariTag = gTag;
		this.goalName = goalName;
		this.ganbariTime = ganbariTime;
		this.ganbariTimeHours = ganbariTimeHours;
		this.ganbariTimeMins = ganbariTimeMins;
		this.goalTime = goalTime;
		this.goalTimeHours = goalTimeHours;
		this.goalTimeMins = goalTimeMins;
		this.progress = progress;
		this.postTime = ts;
		this.reactionCount = reactionCount;
		
	}

//	getter
	public String getId() {return id;}
	public String getUserName() {return userName;}
	public String getMsg() {return msg;}
	public String getGanbariTag() {return ganbariTag;}
	public String getUserUUID() {return userUUID;}
	public String getGoalId() {return goalId;}
	public String getGoalName() {return goalName;}
	public int getGanbariTime() {return ganbariTime;}
	public int getGanbariTimeHours() {return ganbariTimeHours;}
	public int getGanbariTimeMins() {return ganbariTimeMins;}
	public int getGoalTime() {return goalTime;}
	public int getGoalTimeHours() {return goalTimeHours;}
	public int getGoalTimeMins() {return goalTimeMins;}
	public int getProgress() {return progress;}
	public Timestamp getPostTime() {return postTime;}
	public int getReactionCount() {return reactionCount;}

// 	setter
	public void setMsg(String msg) {this.msg = msg;}
	public void setGanbariTag(String ganbariTag) {this.ganbariTag = ganbariTag;}
	public void setGoal(String goalName) {this.goalName = goalName;}
	public void setGoalId(String goalId) {this.goalId = goalId;}
	public void setGanbariTime(int ganbariTime) {this.ganbariTime = ganbariTime;}
	public void setGoalTimeHours(int goalTimeHours) {this.goalTimeHours = goalTimeHours;}
	public void setGoalTimeMins(int goalTimeMins) {this.goalTimeMins = goalTimeMins;}
	public void setGoal(int goalTime) {this.goalTime = goalTime;}
	public void setGanbariTimeHours(int ganbariTimeHours) {this.ganbariTimeHours = ganbariTimeHours;}
	public void setProgress(int progress) {this.progress = progress;}
	public void setGanbariTimeMins(int ganbariTimeMins) {this.ganbariTimeMins = ganbariTimeMins;}
	public void setReactionCount(int reactionCount) {this.reactionCount = reactionCount;}
}