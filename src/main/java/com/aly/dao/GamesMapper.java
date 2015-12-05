package com.aly.dao;

import com.aly.domain.Games;

import java.util.List;

public interface GamesMapper {
    public int deleteByPrimaryKey(Integer gameId);

    public int insert(Games record);

    public int insertSelective(Games record);

    public Games selectByPrimaryKey(Integer gameId);

    public int updateByPrimaryKeySelective(Games record);

    public int updateByPrimaryKey(Games record);

    public List<Games> getAllGames();
}