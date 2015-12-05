package com.aly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商务合作。
 */
@Controller
public class BusinessController {
    @RequestMapping("/business")
    public String business() {
        return "business";
    }

}
