package com.aly.controller;

import com.aly.exception.InvalidUserInfoException;
import com.aly.security.HmacSHA256SignedJWT;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * JWT认证算法测试.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityTest extends AbstractTests{
    private MockMvc mockMvc;

    private static String URI = "/api/{resources}";

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).alwaysDo(print()).build();
    }

    @Test
    public void userLoginValidation() throws Exception {
        String username = "administrator", password = "123456";
        String userInfo = "{\"username\":\"" + username + "\", \"password\": \"" + password + "\"}";
        MvcResult mvcResult = this.mockMvc.perform(post(URI, "/users/validate")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

    }

    @Test
    public void invalidUserShouldBeBadRequest() throws Exception {
        String username = "invalidUserName", password = "invalidPassword";
        String userInfo = "{\"username\":\"" + username + "\", \"password\": \"" + password + "\"}";
        MvcResult mvcResult = this.mockMvc.perform(post(URI, "/users/validate")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userInfo))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(InvalidUserInfoException.INVALID_USER_INFO_MESSAGE))
                .andReturn();
    }

    @Test
    public void testNewHmacSHA256SignedJWT() throws Exception{
        String subject = "john", secret = null;
        String token = HmacSHA256SignedJWT.buildHmacSha256SignedJWT(subject, secret);
        assertNotNull(token);
        assertTrue(HmacSHA256SignedJWT.isValidHmacSha256Signature(token, secret));
    }

    @Test
    public void testInvalidHmacSHA256SignedJWT() throws Exception{
        String subject = "john", secret = null;
        String token = HmacSHA256SignedJWT.buildHmacSha256SignedJWT(subject, secret);
        assertFalse(HmacSHA256SignedJWT.isValidHmacSha256Signature(token + "invalid", secret));
    }

}
