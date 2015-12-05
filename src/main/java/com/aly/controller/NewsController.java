package com.aly.controller;

import com.aly.service.CompanyNewsService;
import com.aly.service.MediaNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 动态信息。
 */
@Controller
public class NewsController {
	@RequestMapping("/news")
    public String news(Model model) {
        model.addAttribute("companyNews", companyNewsService.getAllCompanyNews());
        model.addAttribute("mediaNews", mediaNewsService.getAllMediaNews());
        return "news";
    }

    @RequestMapping("/news/{newsShortcut}")
    public String newsDetail(@PathVariable String  newsShortcut, Model model) {
        model.addAttribute("companyNew", companyNewsService.getComanyNewsByShortcut(newsShortcut));
        return "news-info/news-detail";
    }

    private CompanyNewsService companyNewsService;
    private MediaNewsService mediaNewsService;

    @Autowired
    public void setCompanyNewsService(CompanyNewsService companyNewsService) {
        this.companyNewsService = companyNewsService;
    }

    @Autowired
    public void setMediaNewsService(MediaNewsService mediaNewsService) {
        this.mediaNewsService = mediaNewsService;
    }
}
