package model;

//目標情報(goal_id,goal_name,genre_tag,goal_time,goal_date,user_uuid)を保持するBeans

	public class Goals {
	    private String goalId;
	    private String goalName;
	    private String genreTag;
	    private String goalTime;
	    private String goalDate;
	    private String userUuid;

	    // コンストラクタ
	    public Goals(String goalId, String goalName, String genreTag, String goalTime, String goalDate, String userUuid) {
	        this.goalId = goalId;
	        this.goalName = goalName;
	        this.genreTag = genreTag;
	        this.genreTag = goalTime;
	        this.genreTag = goalDate;
	        this.genreTag = userUuid;
	    }
	    // ゲッターメソッド
	    public String getGoalId() {
	        return goalId;
	    }
	    public String getGoalName() {
	        return goalName;
	    }
	    public String getGenreTag() {
	        return genreTag;
	    }
	    public String getGoalTime() {
	        return goalTime;
	    }
	    public String getGoalDate() {
	        return goalDate;
	    }
	    public String getUserUuid() {
	        return userUuid;
	    }

    // セッターメソッド（必要に応じて追加）
	    public void setGoal_id(String goalId) {
	        this.goalId = goalId;
	    }
	    public void setGoal_name(String goalName) {
	        this.goalName = goalName;
	    }
	    public void setGenreTag(String genreTag) {
	        this.genreTag = genreTag;
	    }
	    public void setGoalTime(String goalTime) {
	        this.goalTime = goalTime;
	    }
	    public void setGoalDate(String goalDate) {
	        this.goalDate = goalDate;
	    }
	    public void setUserUuid(String userUuid) {
	        this.userUuid = userUuid;
	    }

	}








