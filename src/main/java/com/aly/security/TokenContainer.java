package com.aly.security;

import com.aly.domain.Users;
import com.aly.exception.AuthenticationException;
import com.nimbusds.jose.JOSEException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户登录验证通过时，返回给客户端的Token数据。
 */
@XmlRootElement
public class TokenContainer {
    /**
     * 返回给客户端的JWT Token信息
     */
    private String token;

    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static final TokenContainer generateTokenFor(final Users user) {
        TokenContainer tokenContainer = new TokenContainer();
        tokenContainer.setUsername(user.getUsername());
        try {
            tokenContainer.setToken(HmacSHA256SignedJWT.buildHmacSha256SignedJWT(user.getUserId().toString(), null));
        } catch (JOSEException e) {
            throw new AuthenticationException(e);
        }
        return tokenContainer;
    }
}
