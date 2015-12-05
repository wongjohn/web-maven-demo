package com.aly.service;

import com.aly.domain.Games;

import java.util.List;

public interface GamesService {

    public int deleteByPrimaryKey(Integer gameId);

    public int insert(Games record);

    public int insertSelective(Games record);

    public Games selectByPrimaryKey(Integer gameId);

    public int updateByPrimaryKeySelective(Games record);

    public int updateByPrimaryKey(Games record);

    public List<Games> getAllGames();

    public Games getGamesById(Integer id);
}
