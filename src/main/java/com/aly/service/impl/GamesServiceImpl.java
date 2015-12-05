package com.aly.service.impl;

import com.aly.domain.Games;
import com.aly.dao.GamesMapper;
import com.aly.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gamesService")
public class GamesServiceImpl implements GamesService {

    @Override
    public int deleteByPrimaryKey(Integer gameId) {
        return gamesMapper.deleteByPrimaryKey(gameId);
    }

    @Override
    public int insert(Games record) {
        return gamesMapper.insert(record);
    }

    @Override
    public int insertSelective(Games record) {
        return gamesMapper.insertSelective(record);
    }

    @Override
    public Games selectByPrimaryKey(Integer gameId) {
        return gamesMapper.selectByPrimaryKey(gameId);
    }

    @Override
    public int updateByPrimaryKeySelective(Games record) {
        return gamesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Games record) {
        return gamesMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Games> getAllGames() {
        return gamesMapper.getAllGames();
    }

    @Override
    public Games getGamesById(Integer id) {
        return gamesMapper.selectByPrimaryKey(id);
    }

    private GamesMapper gamesMapper;

    @Autowired
    public void setGamesMapper(GamesMapper gamesMapper) {
        this.gamesMapper = gamesMapper;
    }
}
