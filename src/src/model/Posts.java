//投稿情報(post_id,post_message,ganbari_time,goal_id,user_uuid,post_time)を保持するBeans

package model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Posts implements Serializable {
	private String id; //投稿のID（UUID）
	private String userName; //投稿したユーザの名前
	private String msg; //投稿のメッセージ
	private int ganbariTime; //がんばり時間（分）
	private String ganbariTag;
	private String goal; //設定した目標の名前
	private Timestamp postTime; //投稿した日時


	// 投稿情報の新規登録. ユーザ名と目標名は取得してから格納する.
	public Posts(String name, String userName, String msg, int gTime, String gTag, String goal) {
		UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成
		String uuidString = uuid.toString();

		this.id = uuidString;
		this.userName = userName;
		this.msg = msg;
		this.ganbariTime = gTime;
		this.ganbariTag = gTag;
		this.goal = goal;


		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		this.postTime = ts;
	}