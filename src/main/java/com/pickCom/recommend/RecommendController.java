package com.pickCom.recommend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

public class RecommendController {
    @Resource(name = "RecommendServiceImp")
    private RecommendService recommendService;

    @RequestMapping(value = "/recommend")
    public ModelAndView recommend() throws Exception{
        ModelAndView mv = new ModelAndView("/recommend/recommend_Usage");
        return mv;
    }
}
