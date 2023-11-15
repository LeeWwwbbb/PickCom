package com.pickCom.news;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("newsServiceImp")
public class NewsServiceImp implements NewsService{
    @Resource(name = "newsDAO")
    private NewsDAO newsDAO;

    // 배너 뉴스 리스트
    public List<Map<String, Object>> bannerNewsList() throws Exception{
        return newsDAO.bannerNewsList();
    }

    // 메인 뉴스 리스트
    public List<Map<String, Object>> mainNewsList() throws Exception{
        return newsDAO.mainNewsList();
    }

    // 배너 뉴스 리스트
    public List<Map<String, Object>> newsList(Map<String, Object> map) throws Exception{
        return newsDAO.newsList(map);
    }
}
