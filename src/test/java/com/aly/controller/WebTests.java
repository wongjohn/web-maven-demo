package com.aly.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
/**
 * Web页面跳转测试。
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTests extends AbstractTests{
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void indexView() throws Exception {
        mockMvc.perform(get("/")) //home
                .andDo(print())
                .andExpect(model().attributeExists("companyNews")) //验证存储模型数据
                .andExpect(model().attributeExists("mediaNews")) //验证存储模型数据
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void gamesView() throws Exception {
        mockMvc.perform(get("/games")) //games
                .andExpect(model().attributeExists("onlineGames"))//验证存储模型数据
                .andExpect(model().attributeExists("casualGames"))//验证存储模型数据
                .andExpect(status().isOk())
                .andExpect(view().name("games"));
    }

    @Test
    public void introductionView() throws Exception {
        mockMvc.perform(get("/introduction")) //introduction
                .andExpect(status().isOk())
                .andExpect(view().name("introduction"));
    }

    @Test
    public void businessView() throws Exception {
        mockMvc.perform(get("/business")) //home
                .andExpect(status().isOk())
                .andExpect(view().name("business"));
    }

    @Test
    public void recruitView() throws Exception {
        mockMvc.perform(get("/recruit")) //recruit
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recruits")) //验证存储模型数据
                .andExpect(view().name("recruit"));
    }

    @Test
    public void newsView() throws Exception {
        mockMvc.perform(get("/news")) //news
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("companyNews")) //验证存储模型数据
                .andExpect(model().attributeExists("mediaNews")) //验证存储模型数据
                .andExpect(view().name("news"));
        mockMvc.perform(get("/news/ai-le-you-cj-kai-fang-ri-luo-mu")) //news detail
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("companyNew")) //验证存储模型数据
                .andExpect(view().name("news-info/news-detail"));
    }

    @Test
    public void adminIndexView() throws Exception {
        mockMvc.perform(get("/admin/"))//admin index
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/index"));
    }

    @Test
    public void adminDesktopView() throws Exception {
        mockMvc.perform(get("/admin/desktop"))//admin desktop
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/desktop"));
    }

    @Test
    public void presentationIndex() throws Exception {
        mockMvc.perform(get("/presentations/"))//admin index
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("presentations/index"));
    }

    @Test
    public void presentations() throws Exception {
        mockMvc.perform(get("/presentations/architecture"))//admin desktop
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("presentations/architecture"));
    }

}
