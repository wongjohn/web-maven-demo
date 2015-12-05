package com.aly.service.impl;

import com.aly.dao.RecruitsMapper;
import com.aly.domain.Recruits;
import com.aly.service.RecruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recruitsService")
public class RecruitsServiceImpl implements RecruitsService {

    @Override
    public int deleteByPrimaryKey(Integer recruitId) {
        return recruitsMapper.deleteByPrimaryKey(recruitId);
    }

    @Override
    public int insert(Recruits record) {
        return recruitsMapper.insert(record);
    }

    @Override
    public int insertSelective(Recruits record) {
        return recruitsMapper.insertSelective(record);
    }

    @Override
    public Recruits selectByPrimaryKey(Integer recruitId) {
        return recruitsMapper.selectByPrimaryKey(recruitId);
    }

    @Override
    public int updateByPrimaryKeySelective(Recruits record) {
        return recruitsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Recruits record) {
        return recruitsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Recruits> getAllRecruits() {
        return recruitsMapper.getAllRecruits();
    }

    @Override
    public Recruits getRecruitsById(Integer id) {
        return recruitsMapper.selectByPrimaryKey(id);
    }

    private RecruitsMapper recruitsMapper;

    @Autowired
    public void setRecruitsMapper(RecruitsMapper recruitsMapper) {
        this.recruitsMapper = recruitsMapper;
    }
}
