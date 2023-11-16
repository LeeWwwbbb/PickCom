package com.pickCom.news;

import com.pickCom.common.common.CommandMap;
import com.pickCom.utils.BoardPage;
import com.pickCom.utils.TranslateCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    @Resource(name = "newsServiceImp")
    private NewsService newsService;

    @RequestMapping(value = "/news")
    public ModelAndView newsList(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/news/newsList");

        int pageSize = 10;
        int blockPage = 10;
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        // 게시물 리스트를 가져오는 서비스 호출
        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = newsService.newsList(map.getMap());
        System.out.println(newsService.newsList(map.getMap()));

        if (!list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, blockPage, page, "/news");

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("newsList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("newsList", list);
        }

        return mv;
    }

    // 게시글 열기
    @RequestMapping(value = "/news/{idx}")
    public ModelAndView openNewsDetail(CommandMap commandMap, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("/news/newsView");
        commandMap.put("news_num", idx);
        Map<String, Object> map = newsService.openNews(commandMap.getMap());
        mv.addObject("map", map.get("map"));
        System.out.println(mv.getModel());
        return mv;
    }
}
