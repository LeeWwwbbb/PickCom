package com.pickCom.common.home;

import com.pickCom.board.board.BoardService;
import com.pickCom.board.board.BoardServiceImp;
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

    @RequestMapping("/")
    public ModelAndView homeClass() throws Exception {
        ModelAndView mv = new ModelAndView("/index");
        List<Map<String, Object>> bestList = boardService.bestBoardList();
        List<Map<String, Object>> mainList = boardService.mainBoardList();
        if (!bestList.isEmpty()){
            System.out.println(bestList.size());
            mv.addObject("best", bestList);
        }
        if(!mainList.isEmpty()){
            System.out.println(mainList.size());
            mv.addObject("main", mainList);
        }
        return mv;
    }
}
