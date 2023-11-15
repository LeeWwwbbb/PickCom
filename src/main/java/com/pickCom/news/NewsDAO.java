package com.pickCom.news;

import com.pickCom.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("newsDAO")
public class NewsDAO extends AbstractDAO {
    // 배너 뉴스 리스트
    public List<Map<String, Object>> bannerNewsList() throws Exception{
        return selectList("news.bannerNewsList");
    }

    // 메인 뉴스 리스트
    public List<Map<String, Object>> mainNewsList() throws Exception{
        return selectList("news.mainNewsList");
    }

    // 배너 뉴스 리스트
    public List<Map<String, Object>> newsList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("news.newsList", map);
    }
}
