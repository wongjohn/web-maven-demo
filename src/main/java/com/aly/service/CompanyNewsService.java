package com.aly.service;

import com.aly.domain.CompanyNews;

import java.util.List;

/**
 * 公司动态服务。
 */
public interface CompanyNewsService {
    public int deleteByPrimaryKey(Integer newsId);

    public int insert(CompanyNews record);

    public int insertSelective(CompanyNews record);

    public CompanyNews selectByPrimaryKey(Integer newsId);

    public int updateByPrimaryKeySelective(CompanyNews record);

    public int updateByPrimaryKey(CompanyNews record);

    List<CompanyNews> getAllCompanyNews();

    public CompanyNews getCompanyNewsById(Integer id);

    public CompanyNews getComanyNewsByShortcut(String shortcut);
}
