package com.aly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 了解爱乐游。
 */
@Controller
public class IntroductionController {
    @RequestMapping("/introduction")
    public String introduction() {
        return "introduction";
    }
}
