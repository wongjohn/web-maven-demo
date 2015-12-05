package com.aly.api;

import com.aly.api.config.ResourceConfig;
import com.aly.domain.CompanyNews;
import com.aly.security.UserID;
import com.aly.service.CompanyNewsService;
import com.aly.util.HttpHeaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * 公司动态。
 * 进行操作时，需要用户登陆，需要从HTTP请求头中获取用户认证信息，所以需要继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "api/news", headers="Authorization")
public class CompanyNewsResource extends ResourceConfig {
    /**
     * 获取全部公司动态。
     * @return 全部公司动态
     */
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<CompanyNews> getAllCompanyNews(@UserID Integer userId) {
        return companyNewsService.getAllCompanyNews();
    }

    /**
     * 创建新公司动态。
     * @param companyNews 公司动态内容
     * @return 刚刚创建的公司动态
     */
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ResponseStatus(CREATED)

    public CompanyNews createCompanyNews(@UserID Integer userId, @Valid @RequestBody CompanyNews companyNews, HttpServletRequest request,HttpServletResponse response) {
        companyNewsService.insert(companyNews);
        HttpHeaderBuilder.location(request, response, companyNews.getNewsId());
        return companyNews;
    }

    /**
     * 根据公司动态拼音简述，获取公司动态详细信息学。
     * @param shortcut 公司动态拼音简述
     * @return 公司动态详细信息
     */
    @RequestMapping(value = "/company/{shortcut}/shortcut", method = RequestMethod.GET)
    public CompanyNews getCompanyNewsByShortcut(@PathVariable String shortcut) {
        return companyNewsService.getComanyNewsByShortcut(shortcut);
    }

    /**
     * 根据公司动态Id，获取公司动态。
     * @param id 公司动态ID
     * @return 公司动态信息
     */
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public CompanyNews getCompanyNewsById(@PathVariable Integer id) {
        return companyNewsService.getCompanyNewsById(id);
    }

    /**
     * 修改公司动态。
     * @param id 公司动态Id
     * @param companyNews 公司动态内容
     * @return 修改后的公司动态
     */
    @RequestMapping(value = "/company/{id}", method = RequestMethod.POST)
    public CompanyNews updateCompanyNews(@PathVariable Integer id, @Valid @RequestBody CompanyNews companyNews) {
        companyNews.setNewsId(id);
        companyNewsService.updateByPrimaryKeySelective(companyNews);
        return companyNews;
    }

    /**
     * 删除公司动态。
     * @param id 公司动态Id
     */
    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public void deleteCompanyNews(@PathVariable Integer id) {
        companyNewsService.deleteByPrimaryKey(id);
    }

    private CompanyNewsService companyNewsService;

    @Autowired
    public void setCompanyNewsService(CompanyNewsService companyNewsService) {
        this.companyNewsService = companyNewsService;
    }
}
