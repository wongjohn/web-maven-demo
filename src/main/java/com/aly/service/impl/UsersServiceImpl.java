package com.aly.service.impl;

import com.aly.dao.UsersMapper;
import com.aly.domain.Users;
import com.aly.exception.AuthenticationException;
import com.aly.exception.InvalidUserInfoException;
import com.aly.security.HmacSHA256SignedJWT;
import com.aly.security.PasswordContainer;
import com.aly.security.TokenContainer;
import com.aly.service.UsersService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aly.domain.Users.SHA1;
import static com.aly.security.TokenContainer.generateTokenFor;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return usersMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public Users selectByPrimaryKey(Integer userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

    @Override
    public TokenContainer validateUsersInfo(final Users user) {
        Users _user = (new Users(user.getUsername(), user.getPassword())).encodePassword();
        _user = getUserByUsernamePassword(_user);
        return generateTokenFor(_user);
    }

    private Users getUserByUsernamePassword(Users _user) {
        _user = usersMapper.getUsersByUsernamePassword(_user);
        if(null == _user) {
            throw new InvalidUserInfoException();
        }
        return _user;
    }

    @Override
    public void changePasswordFor(Integer userId, PasswordContainer passwords) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        if(null == user || !user.getPassword().equals(SHA1(passwords.getOldPassword()))) {
            throw new InvalidUserInfoException();
        }
        user.setPassword(passwords.getNewPassword());
        usersMapper.updateByPrimaryKey(user.encodePassword());
    }

    private UsersMapper usersMapper;

    @Autowired
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
