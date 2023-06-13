package model;

//目標情報(goal_id,goal_name,genre_tag,goal_time,goal_date,user_uuid)を保持するBeans

	public class Goals {
	    private String goal_id;
	    private String goal_name;
	    private String genre_tag;
	    private String goal_time;
	    private String goal_date;
	    private String user_uuid;

	    // コンストラクタ
	    public Goals(String goal_id, String goal_name, String genre_tag, String goal_time, String goal_date, String user_uuid) {
	        this.goal_id = goal_id;
	        this.goal_name = goal_name;
	        this.genre_tag = genre_tag;
	        this.genre_tag = goal_time;
	        this.genre_tag = goal_date;
	        this.genre_tag = user_uuid;
	    }
	    // ゲッターメソッド
	    public String getGoal_id() {
	        return goal_id;
	    }
	    public String getGoal_name() {
	        return goal_name;
	    }
	    public String getGenre_tag() {
	        return genre_tag;
	    }
	    public String getGoal_time() {
	        return goal_time;
	    }
	    public String getGoal_date() {
	        return goal_date;
	    }
	    public String getUser_uuid() {
	        return user_uuid;
	    }

    // セッターメソッド（必要に応じて追加）
	    public void setGoal_id(String goal_id) {
	        this.goal_id = goal_id;
	    }
	    public void setGoal_name(String goal_name) {
	        this.goal_name = goal_name;
	    }
	    public void setGenre_tag(String genre_tag) {
	        this.genre_tag = genre_tag;
	    }
	    public void setGoal_time(String goal_time) {
	        this.goal_time = goal_time;
	    }
	    public void setGoal_date(String goal_date) {
	        this.goal_date = goal_date;
	    }
	    public void setUser_uuid(String user_uuid) {
	        this.user_uuid = user_uuid;
	    }

	}








