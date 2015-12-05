-- create table <表名> ( <字段名1> <类型1> [,..<字段名n> <类型n>]);
-- 官网内容管理可用账户
CREATE TABLE USERS (
  USER_ID INT (11) NOT NULL AUTO_INCREMENT  COMMENT '唯一主键',
  USERNAME VARCHAR (20) NOT NULL            COMMENT '用户名',
  PASSWORD VARCHAR (40) NOT NULL            COMMENT '密码',
  PRIMARY KEY (USER_ID)
);

-- 旗下游戏
CREATE TABLE GAMES (
  GAME_ID INT (11) NOT NULL AUTO_INCREMENT  COMMENT '唯一主键',
  GAME_TYPE VARCHAR (20)                    COMMENT '游戏类型',
  GAME_NAME VARCHAR (50)                    COMMENT '游戏名称',
  ADDITION_INFO VARCHAR (50)                COMMENT '游戏简述',
  DESCRIPTION VARCHAR (2000)                COMMENT '游戏软文',
  ICON_IMAGE_URL VARCHAR (100)              COMMENT '游戏图标图片地址',
  OFFICIAL_WEBSITE_URL VARCHAR (100)        COMMENT '游戏官网地址',
  ERWEIMA_IMAGE_URL VARCHAR (100)           COMMENT '二维码图片地址',
  PRIMARY KEY (GAME_ID)
);

-- 公司动态
CREATE TABLE COMPANY_NEWS (
  NEWS_ID INT (11) NOT NULL AUTO_INCREMENT  COMMENT '唯一主键',
  SHORTCUT VARCHAR (200) NOT NULL           COMMENT '拼音简述',
  NEWS_TITLE VARCHAR (50)                   COMMENT '标题',
  NEWS_SOURCE VARCHAR (20)                  COMMENT '来源',
  NEWS_AUTHOR VARCHAR (20)                  COMMENT '作者',
  NEWS_CONTENT MEDIUMTEXT                   COMMENT '正文',
  NEWS_DATE DATE                            COMMENT '新闻日期',
  PREVIOUS_ID INT (11)                      COMMENT '上一则新闻的Id',
  PREVIOUS_SHORTCUT VARCHAR (200)           COMMENT '上一则新闻的拼音简述',
  PREVIOUS_TITLE VARCHAR (50)               COMMENT '上一则新闻的标题',
  NEXT_ID INT (11)                          COMMENT '下一则新闻的ID',
  NEXT_SHORTCUT VARCHAR (200)               COMMENT '下一则新闻的拼音简述',
  NEXT_TITLE VARCHAR (50)                   COMMENT '下一则新闻的标题',
  PRIMARY KEY (NEWS_ID)
);

-- 媒体动态
CREATE TABLE MEDIA_NEWS (
  NEWS_ID INT (11) NOT NULL AUTO_INCREMENT  COMMENT '唯一主键',
  NEWS_TITLE VARCHAR (50)                   COMMENT '标题',
  REFERENCE_LINK VARCHAR (100)              COMMENT '引用连接',
  NEWS_DATE DATE                            COMMENT '新闻日期',
  PRIMARY KEY (NEWS_ID)
);

-- 人事招聘
CREATE TABLE RECRUITS (
  RECRUIT_ID INT (11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  RECRUIT_POSITION VARCHAR (50)               COMMENT '岗位名称',
  WORK_PLACE VARCHAR (50)                     COMMENT '工作地点',
  WORK_PROPERTY VARCHAR (50)                  COMMENT '工作性质',
  RECRUIT_NUM VARCHAR (50)                    COMMENT '招聘人数，可以使用汉字，比如若干',
  RESPONSIBILITIES VARCHAR (2000)             COMMENT '岗位责任',
  REQUIREMENTS VARCHAR (2000)                 COMMENT '任职资格',
  OTHERS VARCHAR (2000)                       COMMENT '其他',
  PRIMARY KEY (RECRUIT_ID)
);

