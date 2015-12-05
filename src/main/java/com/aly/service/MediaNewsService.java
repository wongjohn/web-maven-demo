package com.aly.service;

import com.aly.domain.MediaNews;

import java.util.List;

/**
 * 媒体动态服务。
 */
public interface MediaNewsService {
    public int deleteByPrimaryKey(Integer newsId);

    public int insert(MediaNews record);

    public int insertSelective(MediaNews record);

    public MediaNews selectByPrimaryKey(Integer newsId);

    public int updateByPrimaryKeySelective(MediaNews record);

    public int updateByPrimaryKey(MediaNews record);

    public List<MediaNews> getAllMediaNews();

    public MediaNews getMediaNewsById(Integer id);
}
