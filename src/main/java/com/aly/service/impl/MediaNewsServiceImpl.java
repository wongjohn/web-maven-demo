package com.aly.service.impl;

import com.aly.dao.MediaNewsMapper;
import com.aly.domain.MediaNews;
import com.aly.service.MediaNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体动态服务。
 */
@Service("mediaNewsService")
public class MediaNewsServiceImpl implements MediaNewsService {
    @Override
    public int deleteByPrimaryKey(Integer newsId) {
        return mediaNewsMapper.deleteByPrimaryKey(newsId);
    }

    @Override
    public int insert(MediaNews record) {
        return mediaNewsMapper.insert(record);
    }

    @Override
    public int insertSelective(MediaNews record) {
        return mediaNewsMapper.insertSelective(record);
    }

    @Override
    public MediaNews selectByPrimaryKey(Integer newsId) {
        return mediaNewsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public int updateByPrimaryKeySelective(MediaNews record) {
        return mediaNewsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MediaNews record) {
        return mediaNewsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MediaNews> getAllMediaNews() {
        return mediaNewsMapper.getAllMediaNews();
    }

    @Override
    public MediaNews getMediaNewsById(Integer id) {
        return mediaNewsMapper.selectByPrimaryKey(id);
    }

    private MediaNewsMapper mediaNewsMapper;

    @Autowired
    public void setMediaNewsMapper(MediaNewsMapper mediaNewsMapper) {
        this.mediaNewsMapper = mediaNewsMapper;
    }
}
