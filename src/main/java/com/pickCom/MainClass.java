package com.pickCom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainClass {
    @RequestMapping("/main")
    public String mainClass() {
        return "main";
    }
}
