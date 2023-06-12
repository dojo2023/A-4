//投稿情報(post_id,post_message,ganbari_time,goal_id,user_uuid,post_time)を保持するBeans

package model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Posts implements Serializable {
	private String id;
	private String name;
	private String msg;
	private int ganbariTime;
	private String userId;
	private String goalId;
	private Timestamp postTime;


	// 投稿情報の新規登録
	public Posts(String name, String msg, int gTime, String userId) {
		UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成
		String uuidString = uuid.toString();

		this.id = uuidString;
		this.name = name;
		this.msg = msg;
		this.ganbariTime = gTime;
		this.userId = userId;

		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		this.postTime = ts;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public int getGanbariTime() {
		return ganbariTime;
	}


	public void setGanbariTime(int ganbariTime) {
		this.ganbariTime = ganbariTime;
	}


	public String getUserId() {
		return userId;
	}

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}


	public Timestamp getPostTime() {
		return postTime;
	}
}