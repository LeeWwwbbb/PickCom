package com.pickCom.recommend;

import org.springframework.stereotype.Repository;

@Repository("recommendDAO")
public class RecommendDAO {
    public void Recommend() throws Exception{
        System.out.println("나가");
    }
}