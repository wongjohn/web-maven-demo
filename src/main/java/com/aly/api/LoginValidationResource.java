package com.aly.api;

import com.aly.domain.Users;
import com.aly.security.TokenContainer;
import com.aly.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登陆相关操作的资源。
 * 由于登陆时不需要认证（而是获取认证信息），所以不必继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "api/users")
public class LoginValidationResource {
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public TokenContainer userLoginValidation(@RequestBody Users user) {
        return usersService.validateUsersInfo(user);
    }

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
