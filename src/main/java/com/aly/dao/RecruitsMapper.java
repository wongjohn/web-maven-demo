package com.aly.dao;

import com.aly.domain.Recruits;

import java.util.List;

public interface RecruitsMapper {
    public int deleteByPrimaryKey(Integer recruitId);

    public int insert(Recruits record);

    public int insertSelective(Recruits record);

    public Recruits selectByPrimaryKey(Integer recruitId);

    public int updateByPrimaryKeySelective(Recruits record);

    public int updateByPrimaryKey(Recruits record);

    public List<Recruits> getAllRecruits();
}