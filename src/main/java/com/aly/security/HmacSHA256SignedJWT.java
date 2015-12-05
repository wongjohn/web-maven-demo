package com.aly.security;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.nimbusds.jose.*;
import com.nimbusds.jwt.*;
import com.nimbusds.jose.crypto.*;

/**
 * Generating a Signed JWT with Hmac-SHA256.
 * JWT, 即JSON Web Token。
 * 参考《Apress Advanced API Security 第13章 207页》
 */
public class HmacSHA256SignedJWT {
    //默认的官网后台加密口令（私密）
    public static final String DEFAULT_SHARED_SECRET = "北京爱乐游信息技术有限公司（简称：爱乐游5agame）成立于2008年底，位于北京市海淀区上地信息产业基地。自成立以来，专注手机游戏的研发与运营；产品种类多样，主要以休闲类和网游类为主。公司研发的产品《雷霆战机》曾经连续两个月进入全球IOS游戏收入排行榜前十位。凭借其卓越的研发能力和业务运营能力，逐渐发展成为国内最优秀的手游研发和发行商之一。";
    //JWT颁发者，默认为官网
    public static final String ISSUER_HTTP_WWW_5AGAME_COM = "http://www.5agame.com";
    //JWT可以活动的网站
    public static final String AUDIENCE_HTTP_WWW_5AGAME_COM_ADMIN = "http://www.5agame.com/admin";
    //JWT过期时间，默认为5天
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 5;

    public static String buildHmacSha256SignedJWT(String subject, String secret) throws JOSEException{
        if(null == secret || "".equals(secret)) {//如果加密口令为空，则使用默认加密口令
            secret = DEFAULT_SHARED_SECRET;
        }
        // create a claim set.
        JWTClaimsSet jwtClaims = new JWTClaimsSet();
        // set the value of the issuer.
        jwtClaims.setIssuer(ISSUER_HTTP_WWW_5AGAME_COM);
        // set the subject value - JWT belongs to this subject.
        jwtClaims.setSubject(subject);
        // set values for audience restriction.
        List<String> aud = new ArrayList<>();
        aud.add(AUDIENCE_HTTP_WWW_5AGAME_COM_ADMIN);
        jwtClaims.setAudience(aud);
        // expiration time set to 10 minutes.
        jwtClaims.setExpirationTime(new Date(new Date().getTime() + EXPIRATION_TIME));
        Date currentTime = new Date();
        // set the valid from time to current time.
        jwtClaims.setNotBeforeTime(currentTime);
        // set issued time to current time.
        jwtClaims.setIssueTime(currentTime);
        // set a generated UUID as the JWT identifier.
        jwtClaims.setJWTID(UUID.randomUUID().toString());
        // create JWS header with HMAC-SHA256 algorithm.
        JWSHeader jswHeader = new JWSHeader(JWSAlgorithm.HS256);
        // create signer with the provider shared secret.
        JWSSigner signer = new MACSigner(secret);
        // create the signed JWT with the JWS header and the JWT body.
        SignedJWT signedJWT = new SignedJWT(jswHeader, jwtClaims);
        //sign the JWT with HMAC-SHA256.
        signedJWT.sign(signer);
        // serialize into base64-encoded text.
        // print the value of the JWT.
        return signedJWT.serialize();
    }

    /**
     * 判断通行证是否有效。
     * @param secret
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public static boolean isValidHmacSha256Signature(String jwtInText, String secret) throws JOSEException, ParseException {
        // create the signed JWT with the base64-encoded text.
        SignedJWT signedJWT = getSignedJWT(jwtInText, secret);
        // verify the signature of the JWT.
        return isValidSignedJWT(signedJWT, secret);
    }

    /**
     * 判断通行证是否有效。
     * @param secret
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public static SignedJWT getSignedJWT(String jwtInText, String secret) throws JOSEException, ParseException {
        if(null == secret || "".equals(secret)) {//如果加密口令为空，则使用默认加密口令
            secret = DEFAULT_SHARED_SECRET;
        }
        // create verifier with the provider shared secret.
        JWSVerifier verifier = new MACVerifier(secret);
        // create the signed JWT with the base64-encoded text.
        return SignedJWT.parse(jwtInText);
    }

    /**
     * 判断通行证是否有效。
     * @param secret
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public static boolean isValidSignedJWT(SignedJWT signedJWT, String secret) throws JOSEException, ParseException {
        if(null == secret || "".equals(secret)) {//如果加密口令为空，则使用默认加密口令
            secret = DEFAULT_SHARED_SECRET;
        }

        // create verifier with the provider shared secret.
        JWSVerifier verifier = new MACVerifier(secret);
        // verify the signature of the JWT.
        return signedJWT.verify(verifier);
    }


}
