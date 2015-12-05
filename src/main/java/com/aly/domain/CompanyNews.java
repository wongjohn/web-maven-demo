package com.aly.domain;

import com.aly.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 公司动态。
 */
@XmlRootElement
public class CompanyNews {
    /**唯一主键*/
    private Integer newsId;
    /**拼音简述*/
    private String shortcut;
    /**标题*/
    private String newsTitle;
    /**来源*/
    private String newsSource;
    /**作者*/
    private String newsAuthor;
    /**正文*/
    private String newsContent;
    /**新闻日期*/
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date newsDate;
    /**上一则新闻的Id*/
    private Integer previousId;
    /**上一则新闻的拼音简述*/
    private String previousShortcut;
    /**上一则新闻的标题*/
    private String previousTitle;
    /**下一则新闻的ID*/
    private Integer nextId;
    /**下一则新闻的拼音简述*/
    private String nextShortcut;
    /**下一则新闻的标题*/
    private String nextTitle;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public Integer getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Integer previousId) {
        this.previousId = previousId;
    }

    public String getPreviousShortcut() {
        return previousShortcut;
    }

    public void setPreviousShortcut(String previousShortcut) {
        this.previousShortcut = previousShortcut;
    }

    public String getPreviousTitle() {
        return previousTitle;
    }

    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public String getNextShortcut() {
        return nextShortcut;
    }

    public void setNextShortcut(String nextShortcut) {
        this.nextShortcut = nextShortcut;
    }

    public String getNextTitle() {
        return nextTitle;
    }

    public void setNextTitle(String nextTitle) {
        this.nextTitle = nextTitle;
    }
}
