package com.pickCom.board.board;

import com.pickCom.common.common.CommandMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardService boardService;

    // 게시판
    @RequestMapping(value="/board")
    public ModelAndView joinTerms(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");


        return mv;
    }

    // 자유 게시판
    @RequestMapping(value = "/board/{category}")
    public ModelAndView selectCateList(CommandMap commandMap, @PathVariable String category) throws Exception {
        ModelAndView mv = new ModelAndView("/board/list");
        commandMap.put("cate", category);
        List<Map<String, Object>> list = boardService.selectBoardList(commandMap.getMap());
        mv.addObject("list", list);

        return mv;
    }
}
