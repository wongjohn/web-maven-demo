package com.aly.domain;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 旗下游戏。
 */
@XmlRootElement
public class Games {
	/**唯一主键*/
	private Integer gameId;
	/**游戏类型*/
    private String gameType;
	/**游戏名称*/
	private String gameName;
	/**游戏简述*/
	private String additionInfo;
	/**游戏软文*/
	private String description;
	/**游戏图标图片地址*/
	private String iconImageUrl;
	/**游戏官网地址*/
	private String officialWebsiteUrl;
	/**二维码图片地址*/
	private String erweimaImageUrl;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public void setIconImageUrl(String iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
    }

    public String getOfficialWebsiteUrl() {
        return officialWebsiteUrl;
    }

    public void setOfficialWebsiteUrl(String officialWebsiteUrl) {
        this.officialWebsiteUrl = officialWebsiteUrl;
    }

    public String getErweimaImageUrl() {
        return erweimaImageUrl;
    }

    public void setErweimaImageUrl(String erweimaImageUrl) {
        this.erweimaImageUrl = erweimaImageUrl;
    }
}