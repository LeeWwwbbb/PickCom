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
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    @Resource(name = "newsServiceImp")
    private NewsService newsService;

    @RequestMapping(value = "/news")
    public ModelAndView newsList(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/news/List");

        // 페이징 관련 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
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
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, pageCount, page, "/news");

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("newsList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("newsList", list);
        }

        return mv;
    }
}
