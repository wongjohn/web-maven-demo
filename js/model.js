/*Domain*/
function CompanyNew(_paramObj) {
    var paramObj = _paramObj || {};
    /**唯一主键*/
    this.newsId = null;
    /**拼音简述*/
    this.shortcut = paramObj.shortcut || null;
    /**标题*/
    this.newsTitle = paramObj.newsTitle || null;
    /**来源*/
    this.newsSource = paramObj.newsSource || "官网";
    /**作者*/
    this.newsAuthor = paramObj.newsAuthor || "佚名";
    /**正文*/
    this.newsContent = paramObj.newsContent || null;
    /**新闻日期*/
    this.newsDate = paramObj.newsDate || null;
}

function MediaNew(_paramObj) {
    var paramObj = _paramObj || {};
    /**唯一主键*/
    this.newsId = paramObj.newsId || null;
    /**标题*/
    this.newsTitle = paramObj.newsTitle || null;
    /**引用连接*/
    this.referenceLink = paramObj.referenceLink || null;
    /**新闻日期*/
    this.newsDate = paramObj.newsDate || null;
}

function Recruits(_paramObj) {
    var paramObj = _paramObj || {};
    /**唯一主键*/
    this.recruitId = paramObj.recruitId || null;
    /**岗位名称*/
    this.recruitPosition = paramObj.recruitPosition || null;
    /**工作地点*/
    this.workPlace = paramObj.workPlace || null;
    /**工作性质*/
    this.workProperty = paramObj.workProperty || null;
    /**招聘人数*/
    this.recruitNum = paramObj.recruitNum || null;
    /**岗位责任*/
    this.responsibilities = paramObj.responsibilities || null;
    /**任职资格*/
    this.requirements = paramObj.requirements || null;
    /**其他*/
    this.others = paramObj.others || null;
}

function Games(_paramObj) {
    var paramObj = _paramObj || {};
    /**唯一主键*/
    this.gameId = paramObj.gameId || null;
    /**游戏类型*/
    this.gameType = paramObj.gameType || null;
    /**游戏名称*/
    this.gameName = paramObj.gameName || null;
    /**游戏简述*/
    this.additionInfo = paramObj.additionInfo || null;
    /**游戏软文*/
    this.description = paramObj.description || null;
    /**游戏图标*/
    this.iconImageUrl = paramObj.iconImageUrl || null;
    /**游戏官网*/
    this.officialWebsiteUrl = paramObj.officialWebsiteUrl || null;
    /**二维码*/
    this.erweimaImageUrl = paramObj.erweimaImageUrl || null;
}
