package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

//目標情報(goal_id,goal_name,genre_tag,goal_time,goal_date,user_uuid)を保持するBeans

public class Goals {
    private String goalId; //目標UUID
    private String goalName; //目標名
    private String genreTag; //タグ
    private int progress; // 目標時間（達成済みの時間）
    private int goalTime; // 目標時間
    private Timestamp goalDate; //目標を追加した時間
    private String userUuid; //目標を設定したユーザのUUID

    // コンストラクタ（登録用）
    public Goals(String goalName, String genreTag, int goalTime, String userUuid) {
    	// 投稿のUUIDを生成
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();

		// 現在時刻を取得する
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());

        this.goalId = uuidString;
        this.goalName = goalName;
        this.genreTag = genreTag;
        this.goalTime = goalTime;
        this.userUuid = userUuid;
		this.goalDate = ts;
    }

    // コンストラクタ（取得用）
    public Goals(String goalId, String goalName, String genreTag, int goalTime, int progress, Timestamp goalDate, String userUuid) {
    	this.goalId = goalId;
        this.goalName = goalName;
        this.genreTag = genreTag;
        this.goalTime = goalTime;
        this.progress = progress;
        this.userUuid = userUuid;
		this.goalDate = goalDate;
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
    public int getGoalTime() {
        return goalTime;
    }
    public int progress() {
        return progress;
    }
    public Timestamp getGoalDate() {
        return goalDate;
    }
    public String getUserUuid() {
        return userUuid;
    }

// セッターメソッド（必要に応じて追加）
    public void setGoal_name(String goalName) {
        this.goalName = goalName;
    }
    public void setGenreTag(String genreTag) {
        this.genreTag = genreTag;
    }
    public void setGoalTime(int goalTime) {
        this.goalTime = goalTime;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
}








