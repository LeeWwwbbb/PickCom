package com.pickCom.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class RecommendController {
    @Resource(name = "recommendServiceImp")
    private RecommendService recommendService;

    @RequestMapping(value = "/recommend")
    public ModelAndView recommend() throws Exception{
        ModelAndView mv = new ModelAndView("/recommend/recommend_Usage");
        return mv;
    }
}
