package com.aly.dao;

import com.aly.domain.Users;

import java.util.List;

public interface UsersMapper {
    public int deleteByPrimaryKey(Integer userId);

    public int insert(Users record);

    public int insertSelective(Users record);

    public Users selectByPrimaryKey(Integer userId);

    public int updateByPrimaryKeySelective(Users record);

    public int updateByPrimaryKey(Users record);

    public List<Users> getAllUsers();

    public Users getUsersByUsernamePassword(Users user);
}