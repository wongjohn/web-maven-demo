package com.aly.api;

import com.aly.api.config.ResourceConfig;
import com.aly.security.PasswordContainer;
import com.aly.security.UserID;
import com.aly.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关操作的资源。
 * 进行操作时，需要用户登陆，需要从HTTP请求头中获取用户认证信息，所以需要继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "api/users")
public class UsersResource extends ResourceConfig {

    /**修改自己的密码*/
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public void changePassword(@UserID Integer userId, @RequestBody PasswordContainer passwords) {
        this.usersService.changePasswordFor(userId, passwords);
    }

    /**修改某个用户的密码*/
    @RequestMapping(value = "/{userId}/password", method = RequestMethod.POST)
    public void changePasswordForUser(@PathVariable("userId") Integer userId, @RequestBody PasswordContainer passwords) {
        this.usersService.changePasswordFor(userId, passwords);
    }


    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
