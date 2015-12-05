package com.aly.service;

import java.util.List;

import com.aly.domain.Users;
import com.aly.security.PasswordContainer;
import com.aly.security.TokenContainer;

public interface UsersService {
    public int deleteByPrimaryKey(Integer userId);

    public int insert(Users record);

    public int insertSelective(Users record);

    public Users selectByPrimaryKey(Integer userId);

    public int updateByPrimaryKeySelective(Users record);

    public int updateByPrimaryKey(Users record);

    public List<Users> getAllUsers();

    public TokenContainer validateUsersInfo(Users user);

    public void changePasswordFor(Integer userId, PasswordContainer passwords);
}
