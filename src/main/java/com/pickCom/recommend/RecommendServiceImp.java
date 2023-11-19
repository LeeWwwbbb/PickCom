package com.pickCom.recommend;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("recommendServiceImp")
public class RecommendServiceImp {
    @Resource(name = "recommendDAO")
    private RecommendDAO recommendDAO;
}
