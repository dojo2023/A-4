package model;

//目標情報(goal_id,goal_name,genre_tag,goal_time,goal_date,user_uuid)を保持するBeans

	public class Goals {
	    private String title;
	    private String description;
	    private double target;
	    // コンストラクタ
	    public Goals(String title, String description, double target) {
	        this.title = title;
	        this.description = description;
	        this.target = target;
	    }
	    // ゲッターメソッド
	    public String getTitle() {
	        return title;
	    }
	    public String getDescription() {
	        return description;
	    }
	    public double getTarget() {
	        return target;
	    }
	    // セッターメソッド（必要に応じて追加）
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	    public void setTarget(double target) {
	        this.target = target;
	    }
	}








