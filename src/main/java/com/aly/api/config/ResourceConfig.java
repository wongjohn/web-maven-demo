package com.aly.api.config;

import com.aly.exception.AuthenticationException;
import com.aly.security.HmacSHA256SignedJWT;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 资源类共同的配置.
 * 处理包括认证（使用注解@UserID获取用户ID信息）等部分的相关内容。
 */
public class ResourceConfig {
    /**
     * 处理Token认证相关的信息。
     * @param request
     */
    @ModelAttribute
    public void handleAuthorization(HttpServletRequest request) {
        String secret = null;
        Enumeration<String> authorization = request.getHeaders("Authorization");
        if (authorization.hasMoreElements()) {
            try {
                String token = authorization.nextElement();
                token = token.split(" ")[1];
                SignedJWT signedJWT = HmacSHA256SignedJWT.getSignedJWT(token, secret);
                if(HmacSHA256SignedJWT.isValidSignedJWT(signedJWT, secret)) {
                    request.setAttribute("userId", Integer.parseInt(signedJWT.getJWTClaimsSet().getSubject()));
                    return;
                }
            } catch (JOSEException e) {
                throw new AuthenticationException("解析通行证信息时出现异常");
            } catch (Exception e) {
            }
        }
        throw new AuthenticationException();
    }
}
