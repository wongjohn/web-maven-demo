package com.aly.controller;

import com.aly.domain.CompanyNews;
import com.aly.domain.Users;
import com.aly.security.TokenContainer;
import com.aly.service.CompanyNewsService;
import com.aly.service.UsersService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
/**
 * REST API测试。
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class APITests extends AbstractTests{
    private MockMvc mockMvc;
    private HttpHeaders httpHeaders;

    private static String URI = "/api/{resources}";

    @BeforeClass
    public static void initDB() {
//        FlywayDBMigration.clean76MysqlDBTables();
        FlywayDBMigration.cleanLocalMysqlDBTables();
    }

    @Autowired
    UsersService usersService;

    @Autowired
    CompanyNewsService companyNewsService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).alwaysDo(print()).build();
        this.httpHeaders = new HttpHeaders();
        Users administrator = new Users("administrator", "123456");
        administrator.setUserId(1);
        TokenContainer token = TokenContainer.generateTokenFor(administrator);
        httpHeaders.add("Authorization", "Bearer " + token.getToken());
    }

    /**公司动态*/
    @Test
    public void getAllCompanyNews() throws Exception {
        //http://goessner.net/articles/JsonPath/
        this.mockMvc.perform(get(URI, "news/company").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getCompanyNewsById() throws Exception {
        Integer companyNewsId = 2;
        this.mockMvc.perform(get(URI, "news/company/" + companyNewsId).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void getCompanyNewsByShortcut() throws Exception {
        String shortcut = "ai-le-you-qian-xia-xing-ji-lie-ren";
        this.mockMvc.perform(get(URI, "news/company/" + shortcut + "/shortcut").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    public void createCompanyNews() throws Exception {
        String sampleCompanyNew = "{" +
                "\"newsId\": null," +
                "\"shortcut\": \"test\"," +
                "\"newsTitle\": \"爱乐游试水发行挺进三甲《星际猎人》惊艳亮相\"," +
                "\"newsSource\": \"官网\"," +
                "\"newsAuthor\": \"佚名\"," +
                "\"newsContent\": \"近日，易观智库公布了《2014上半年中国网络游戏市场监测报告》。\\n\\n近日，易观智库公布了《2014上半年中国网络游戏市场监测报告》。\\n\\n近日，易观智库公布了《2014上半年中国网络游戏市场监测报告》。\"," +
                "\"newsDate\": \"2014-12-23\"" +
                "}";
        Integer id = 10;
        this.mockMvc.perform(post(URI, "news/company")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleCompanyNew).headers(httpHeaders))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nextId").value(9))
                .andExpect(header().string("Location", containsString("/api/news/company/" + id)))
                .andReturn();

        CompanyNews companyNews = companyNewsService.selectByPrimaryKey(9);
        assertThat(companyNews.getPreviousId(), equalTo(10));
    }

    @Test
    public void updateCompanyNews() throws Exception {
        int id = 1;
        String sampleCompanyNew = "{\"newsId\": " + id + ", \"shortcut\": \"new-shortcut\"}";
        this.mockMvc.perform(post(URI, "news/company/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleCompanyNew).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCompanyNews() throws Exception {
        int id = 1;
        this.mockMvc.perform(delete(URI, "news/company/" + id).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    /**媒体动态*/
    @Test
    public void getAllMediaNews() throws Exception {
        this.mockMvc.perform(get(URI, "news/media").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getMediaNewsById() throws Exception {
        Integer mediaNewsId = 2;
        this.mockMvc.perform(get(URI, "news/media/" + mediaNewsId).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void createMediaNews() throws Exception {
        String sampleMediaNew = "{" +
                "\"newsId\": null," +
                "\"newsTitle\": \"《星际猎人》评测：太空与枪战是男人的浪漫\"," +
                "\"referenceLink\": \"http://www.ptbus.com/xjlr/329794/\"," +
                "\"newsDate\": \"2014-10-22\"" +
                "}";
        Integer id = 8;
        this.mockMvc.perform(post(URI, "news/media")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleMediaNew).headers(httpHeaders))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/news/media/" + id)))
                .andReturn();
    }

    @Test
    public void updateMediaNews() throws Exception {
        int id = 1;
        String sampleMediaNew = "{\"newsId\": " + id + "}";
        this.mockMvc.perform(post(URI, "news/media/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleMediaNew).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMediaNews() throws Exception {
        int id = 1;
        this.mockMvc.perform(delete(URI, "news/media/" + id).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    /**旗下游戏*/
    @Test
    public void getAllGames() throws Exception {
        this.mockMvc.perform(get(URI, "games").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getGamesById() throws Exception {
        Integer id = 2;
        this.mockMvc.perform(get(URI, "games/" + id).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void createGames() throws Exception {
        String sampleGame = "{" +
                "\"gameId\": null," +
                "\"gameType\": \"网络游戏\"," +
                "\"gameName\": \"雷霆战机\"," +
                "\"additionInfo\": \"2D/飞行射击\"," +
                "\"description\": \"《雷霆战机》是由北京爱乐游倾力打造，腾讯游戏独家代理的首款星际空战手游。玩家可以根据自己的喜好进行上千种搭配，DIY属于自己的专属战机。如今《雷霆战机》新版本震撼上线，机甲战神的出现必将打破原有的平静，即将掀起新一轮的宇宙大战，在这场战斗中谁能称雄？现在，专属于你的战神正等待着你，一起踏上史诗般的征程！\"," +
                "\"iconImageUrl\": \"images/game/leitingtu.jpg\"," +
                "\"officialWebsiteUrl\": \"http://lt.qq.com\"," +
                "\"erweimaImageUrl\": \"images/game/leitingzhanjierweima.png\"" +
                "}";
        Integer id = 8;
        this.mockMvc.perform(post(URI, "games")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleGame).headers(httpHeaders))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/games/" + id)))
                .andReturn();
    }

    @Test
    public void updateGames() throws Exception {
        int id = 1;
        String sampleGame = "{\"gameId\": " + id + "}";
        this.mockMvc.perform(post(URI, "games/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleGame).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteGames() throws Exception {
        int id = 1;
        this.mockMvc.perform(delete(URI, "games/" + id).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    /**人事招聘*/
    @Test
    public void getAllRecruits() throws Exception {
        this.mockMvc.perform(get(URI, "recruits").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getRecruitsById() throws Exception {
        Integer id = 2;
        this.mockMvc.perform(get(URI, "recruits/" + id).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void createRecruits() throws Exception {
        String sampleRecruit = "{" +
                "\"recruitId\": null," +
                "\"recruitPosition\": \"android/ios游戏开发工程师\"," +
                "\"workPlace\": \"北京\"," +
                "\"workProperty\": \"全职\"," +
                "\"recruitNum\": \"若干\"," +
                "\"responsibilities\": \"1、负责Android/IOS手机平台游戏研发，与策划、美术、测试协同进行游戏制作；\\n\\n2、游戏引擎、开发工具等优化及制作。\"," +
                "\"requirements\": \"1、计算机（或相关专业）本科及以上学历，C++计算机基础知识扎实、理解能力强；\\n\\n2、熟练掌握cocos2d-x者优先,有良好的面向对象设计思想，了解设计模式；\\n\\n3、热爱游戏，并热爱游戏开发工作，拥有手机游戏开发经验者优先；\\n\\n4、具有良好的沟通能力和团队合作精神，工作责任心强；\\n\\n5、优秀的分析问题和解决问题的能力，对解决挑战性问题充满激情。\\n\\n\"," +
                "\"others\":\"\"" +
                "}";
        Integer id = 23;
        this.mockMvc.perform(post(URI, "recruits")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleRecruit).headers(httpHeaders))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/recruits/" + id)))
                .andReturn();
    }

    @Test
    public void updateRecruits() throws Exception {
        int id = 1;
        String sampleRecruit = "{\"recruitId\": " + id + "}";
        this.mockMvc.perform(post(URI, "recruits/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleRecruit).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRecruits() throws Exception {
        int id = 1;
        this.mockMvc.perform(delete(URI, "recruits/" + id).headers(httpHeaders))
                .andExpect(status().isOk());
    }

    @Test
    public void changePassword() throws Exception {
        int id = 1;
        String oldPassword = "123456", //oldPasswordEncoded = "7c4a8d09ca3762af61e59520943dc26494f8941b",
                newPassword = "654321", newPasswordEncoded = "dd5fef9c1c1da1394d6d34b248c51be2ad740840";

        String sampleUser = "{\"oldPassword\":\"" + oldPassword + "\", \"newPassword\":\"" + newPassword + "\"}";
        this.mockMvc.perform(post(URI, "users/password")
                .contentType(MediaType.APPLICATION_JSON).content(sampleUser)
                .headers(httpHeaders))
                .andExpect(status().isOk());

        Users user = usersService.selectByPrimaryKey(id);
        assertThat(user.getPassword(), equalTo(newPasswordEncoded));
    }

}
