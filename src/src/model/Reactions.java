package model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
//リアクション情報(riaction_id,good,user_uuid,post_id,reaction_time)を保持するBeans
public class Reactions implements Serializable{
	private String reaction_id; //リアクションID
	private String user_uuid; //ユーザUUID
	private String post_id;  //投稿ID
	private Timestamp reaction_time;  //リアクション送信時間

	public Reactions(String user_uuid,String post_id) {

		UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成
		String uuidString = uuid.toString();

		this.reaction_id = uuidString;
		this.user_uuid = user_uuid;
		this.post_id = post_id;

		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		this.reaction_time = ts;

	}

	public Reactions() {
	}

	//リアクションID
	    public String getReaction_id() {
	    	return reaction_id;
	    }
	    public void setReaction_id(String reaction_id){
	        this.reaction_id = reaction_id;
	    }


	  //ユーザUUID
	    public String getUser_uuid() {
	    	return user_uuid;
	    }
	    public void setUser_uuid(String user_uuid){
	        this.user_uuid = user_uuid;
	    }

	  //投稿ID
	    public String getPost_id() {
	    	return post_id;
	    }
	    public void setPost_id(String post_id){
	        this.post_id = post_id;
	    }

	  //リアクション送信時間
	    public Timestamp getReaction_time() {
	    	return reaction_time;
	    }
	    public void setReaction_time(Timestamp reaction_time){
	        this.reaction_time = reaction_time;
	    }
}
