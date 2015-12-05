package com.aly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理。
 */
@Controller
public class AdminController {
    @RequestMapping("/admin/*")
    public String home() {
        return "admin/index";
    }

    @RequestMapping("/admin/desktop")
    public String desktop() {
        return "admin/desktop";
    }
}
