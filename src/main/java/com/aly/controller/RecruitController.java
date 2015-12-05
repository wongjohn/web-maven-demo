package com.aly.controller;

import com.aly.service.RecruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 加入爱乐游。
 */
@Controller
public class RecruitController {
    @RequestMapping("/recruit")
    public String recruit(Model model) {
        model.addAttribute("recruits", recruitsService.getAllRecruits());
        return "recruit";
    }

    private RecruitsService recruitsService;

    @Autowired
    public void setRecruitsService(RecruitsService recruitsService) {
        this.recruitsService = recruitsService;
    }
}
