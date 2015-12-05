package com.aly.domain;

import com.aly.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 媒体动态。
 */
@XmlRootElement
public class MediaNews {
    /**唯一主键*/
    private Integer newsId;
    /**标题*/
    private String newsTitle;
    /**引用连接*/
    private String referenceLink;
    /**新闻日期*/
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date newsDate;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getReferenceLink() {
        return referenceLink;
    }

    public void setReferenceLink(String referenceLink) {
        this.referenceLink = referenceLink;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }
}
