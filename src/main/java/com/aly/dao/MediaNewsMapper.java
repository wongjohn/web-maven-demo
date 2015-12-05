package com.aly.dao;

import com.aly.domain.MediaNews;

import java.util.List;

public interface MediaNewsMapper {
    public int deleteByPrimaryKey(Integer newsId);

    public int insert(MediaNews record);

    public int insertSelective(MediaNews record);

    public MediaNews selectByPrimaryKey(Integer newsId);

    public int updateByPrimaryKeySelective(MediaNews record);

    public int updateByPrimaryKey(MediaNews record);

    public List<MediaNews> getAllMediaNews();
}