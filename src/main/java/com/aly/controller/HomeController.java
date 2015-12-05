package com.aly.controller;

import com.aly.service.CompanyNewsService;
import com.aly.service.MediaNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 爱乐游主页
 */
@Controller
public class HomeController {
    @RequestMapping("/*")
    public String home(Model model) {
        model.addAttribute("companyNews", companyNewsService.getAllCompanyNews());
        model.addAttribute("mediaNews", mediaNewsService.getAllMediaNews());
        return "index";
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
