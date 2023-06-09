がんばり共有アプリのSQL

【ドライバ】
org.h2.Driver

【JDBC URL】
jdbc:h2:file:C:/pleiades/workspace/data/nyastar

※データベース作成のエラーが表示された場合は、
https://sukkiri.jp/technologies/ides/eclipse/h2-createdb.html
を参照してください。

※H2 Consoleは、Webブラウザを閉じても終了しないので、Windowsの
タスクバーの右側にある「＾」をクリックして、表示されたアイコン
から黄色いドラム缶のアイコンの「H2 Database Engine」を右クリッ
クし、表示されたメニューから「Exit」を選択してください。

【ユーザー名】	【パスワード】
sa		（なし）
【アカウントテーブルテーブルを作成するSQL文】
CREATE TABLE ACCOUNTS(
  USER_UUID VARCHAR()PRIMARY KEY,
  USER_ID VARCHAR(16)NOT NULL,
  USER_NAME VARCHAR(16)NOT NULL,
  PASSWORD VARCHAR()NOT NULL
);

【投稿テーブルを作成するSQL文】
CREATE TABLE POSTS(
  POST_ID VARCHAR PRIMARY KEY,
  POST_MESSAGE VARCHAR(50),
  GANBARI_TIME INT NOT NULL,
  GOAL_ID VARCHAR NOT NULL,
  FOREIGN KEY(GOAL_ID) REFERENCES GOALS(GOAL_ID),
  USER_UUID VARCHAR NOT NULL,
  FOREIGN KEY(USER_UUID) REFERENCES ACCOUNTS(USER_UUID),
  POST_TIME TIMESTAMP NOT NULL
);


【目標テーブルを作成するSQL文】
CREATE TABLE GOALS(
  GOAL_ID VARCHAR PRIMARY KEY,
  GOAL_NAME VARCHAR NOT NULL,
  GENRE_TAG VARCHAR NOT NULL,
  GOAL_TIME INT NOT NULL,
  GOAL_DATE TIMESTAMP NOT NULL,
  USER_UUID VARCHAR NOT NULL,
  FOREIGN KEY(USER_UUID) REFERENCES ACCOUNTS(USER_UUID),
  ACHIEVEMENT_TIME INT
);

【コメントテーブルを作成するSQL文】
CREATE TABLE COMMENTS(
  COMMENT_ID VARCHAR PRIMARY KEY,
  COMMENT_CONTENT VARCHAR(20)NOT NULL,
  POST_ID VARCHAR NOT NULL,
  FOREIGN KEY(POST_ID) REFERENCES POSTS(POST_ID),
  COMMENT_TIME TIMESTAMP NOT NULL,
  USER_UUID VARCHAR NOT NULL,
  FOREIGN KEY(USER_UUID) REFERENCES ACCOUNTS(USER_UUID)
);

【リアクションテーブルを作成するSQL文】
CREATE TABLE REACTIONS(
  REACTION_ID VARCHAR PRIMARY KEY,
  POST_ID VARCHAR NOT NULL,
  FOREIGN KEY(POST_ID) REFERENCES POSTS(POST_ID),
  REACTION_TIME TIMESTAMP NOT NULL,
  USER_UUID VARCHAR NOT NULL,
  FOREIGN KEY(USER_UUID) REFERENCES ACCOUNTS(USER_UUID)
);