package com.pickCom.recommend;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("recommendServiceImp")
public class RecommendServiceImp implements RecommendService{
    @Resource(name = "recommendDAO")
    private RecommendDAO recommendDAO;

    @Override
    public void recommend() throws Exception{

    }
}
