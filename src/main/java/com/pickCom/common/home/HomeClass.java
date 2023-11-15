package com.pickCom.common.home;

import com.pickCom.board.board.BoardService;
import com.pickCom.board.board.BoardServiceImp;
import com.pickCom.news.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class HomeClass {
    @Resource(name = "boardServiceImp")
    private BoardService boardService;

    @Resource(name = "newsServiceImp")
    private NewsService newsService;

    @RequestMapping("/")
    public ModelAndView homeClass() throws Exception {
        ModelAndView mv = new ModelAndView("/index");
        List<Map<String, Object>> bestBoard = boardService.bestBoardList();
        List<Map<String, Object>> mainBoard = boardService.mainBoardList();
        List<Map<String, Object>> bannerNews = newsService.bannerNewsList();
        List<Map<String, Object>> mainNews = newsService.mainNewsList();
        if (!bestBoard.isEmpty()){
            System.out.println(bestBoard.size());
            mv.addObject("bestBoard", bestBoard);
        }
        if(!mainBoard.isEmpty()){
            System.out.println(mainBoard.size());
            mv.addObject("mainBoard", mainBoard);
        }
        if (!bannerNews.isEmpty()){
            System.out.println(bannerNews.size());
            mv.addObject("bannerNews", bannerNews);
        }
        if(!mainNews.isEmpty()){
            System.out.println(mainNews.size());
            mv.addObject("mainNews", mainNews);
        }
        return mv;
    }
}
