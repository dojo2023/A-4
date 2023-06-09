package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
//コメント情報(comment_id,comment_content,user_uuid,post_id,comment_time)を保持するBeans
	public class Comments implements Serializable {
		private String comment_id; //コメントID
		private String comment_content;   //コメント内容
		private String user_uuid; //ユーザUUID
		private String user_name;
		private String post_id;  //投稿ID
		private Timestamp comment_time;  //コメント送信時間

		public Comments(String commentMsg, String user_uuid, String post_id) {

			UUID uuid = UUID.randomUUID(); // 一意のUUIDを生成

			this.comment_id = uuid.toString();
			this.comment_content = commentMsg;
			this.user_uuid = user_uuid;
			this.post_id = post_id;

			// 現在時刻を取得する
			Date now = new Date();
			Timestamp ts = new Timestamp(now.getTime());
			this.comment_time = ts;
//			this.comment_time = ts;
//			SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd");
//			this.comment_time = time.format(ts);
//			System.out.println("設定した時間：" + this.comment_time);

		}

		public Comments() {
		}

		//コメントID
		    public String getComment_id() {
		    	return comment_id;
		    }
		    public void setComment_id(String comment_id){
		        this.comment_id = comment_id;
		    }

		  //コメント内容
		    public String getComment_content() {
		    	return comment_content;
		    }
		    public void setComment_content(String comment_content){
		        this.comment_content = comment_content;
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

		  //コメント送信時間
		    public Timestamp getComment_time() {
		    	return comment_time;
		    }
		    public void setComment_time(Timestamp comment_time){
		        this.comment_time = comment_time;
		    }

		  //ユーザー名
			public String getUser_name() {
				return user_name;
			}
			public void setUser_name(String user_name) {
				this.user_name = user_name;
			}
	}


