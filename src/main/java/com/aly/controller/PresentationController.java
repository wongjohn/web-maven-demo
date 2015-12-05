package com.aly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 演示文档.
 */
@Controller
public class PresentationController {
    @RequestMapping("/presentations/*")
    public String home() {
        return "presentations/index";
    }

    @RequestMapping("/presentations/{presentation}")
    public String desktop(@PathVariable String presentation) {
        return "presentations/" + presentation;
    }
}
