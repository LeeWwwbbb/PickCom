package com.pickCom.common.home;

import com.pickCom.board.board.BoardService;
import com.pickCom.board.board.BoardServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeClass {
    @RequestMapping("/")
    public ModelAndView homeClass() throws Exception {
        ModelAndView mv = new ModelAndView("/index");
        BoardService boardService = new BoardServiceImp();
        List<Map<String, Object>> list = boardService.bestBoardList();
        if (!list.isEmpty()){
            mv.addObject("best", list);
        }
        return mv;
    }
}
