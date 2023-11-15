package com.pickCom.news;

import java.util.List;
import java.util.Map;

public interface NewsService {
    // 배너 뉴스 리스트
    public List<Map<String, Object>> bannerNewsList() throws Exception;

    // 메인 뉴스 리스트
    public List<Map<String, Object>> mainNewsList() throws Exception;

    // 배너 뉴스 리스트
    public List<Map<String, Object>> newsList(Map<String, Object> map) throws Exception;
}
