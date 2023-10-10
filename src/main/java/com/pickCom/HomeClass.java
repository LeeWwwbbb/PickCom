package com.pickCom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeClass {
    @RequestMapping("/")
    public String homeClass() {
        return "index";
    }
}
