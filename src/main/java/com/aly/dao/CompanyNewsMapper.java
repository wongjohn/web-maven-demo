package com.aly.dao;

import com.aly.domain.CompanyNews;

import java.util.List;

public interface CompanyNewsMapper {
    public int deleteByPrimaryKey(Integer newsId);

    public int insert(CompanyNews record);

    public int insertSelective(CompanyNews record);

    public CompanyNews selectByPrimaryKey(Integer newsId);

    public int updateByPrimaryKeySelective(CompanyNews record);

    public int updateByPrimaryKey(CompanyNews record);

    public List<CompanyNews> getAllCompanyNews();

    public CompanyNews getCompanyNewsByShortcut(String shortcut);

    public CompanyNews selectByEmptyPreviousId();
}