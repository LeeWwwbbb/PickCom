package com.pickCom.board.board;

import com.pickCom.common.common.CommandMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardService boardService;

    // 게시판
    @RequestMapping(value="/board")
    public ModelAndView boardList(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");


        return mv;
    }

    // 카테고리 게시판
    @RequestMapping(value = "/board/{category}")
    public ModelAndView selectCateList(CommandMap commandMap, @PathVariable String category) throws Exception {
        ModelAndView mv = new ModelAndView("/board/list");
        commandMap.put("cate", category);
        List<Map<String, Object>> list = boardService.selectBoardList(commandMap.getMap());
        mv.addObject("list", list);
        if(!list.isEmpty()){
            mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
        }
        else{
            mv.addObject("TOTAL", 0);
        }

        return mv;
    }

    // 게시글 열기
    @RequestMapping(value = "/board/{category}/{num}")
    public ModelAndView openBoardDetail(CommandMap commandMap, @PathVariable String category, @PathVariable int num) throws Exception{
        ModelAndView mv = new ModelAndView("/board/View");

        Map<String,Object> map = boardService.openBoardDetail(commandMap.getMap());
        mv.addObject("map", map.get("map"));
        mv.addObject("list", map.get("list"));

        return mv;
    }

    // 글 작성 폼
    @RequestMapping(value="/board/write.do")
    public ModelAndView openQnaWrite(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Write");

        return mv;
    }

    // 글 등록
    @RequestMapping(value="/board/insert.do", method = RequestMethod.POST )
    public ModelAndView insertQna(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/qna/openQnaList.do"); // 수정해야함

        boardService.insertBoard(commandMap.getMap(), request);



        return mv;
    }

    // 글 수정 폼
    @RequestMapping(value="/board/openBoardUpdate.do")
    public ModelAndView openQnaUpdate(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Edit");

        Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
        mv.addObject("map", map.get("map"));
        mv.addObject("list", map.get("list"));

        return mv;
    }

    // 글 수정
    @RequestMapping(value="/board/updateBoard.do")
    public ModelAndView updateQna(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/openQnaDetail.do"); // 수정해야함

        boardService.updateBoard(commandMap.getMap(), request);

        mv.addObject("BOARD_NUM", commandMap.get("BOARD_NUM"));
        return mv;
    }

    // 글 삭제
    @RequestMapping(value="/board/deleteBoard.do")
    public ModelAndView deleteQna(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/openQnaList.do"); // 수정해야함

        boardService.deleteBoard(commandMap.getMap());

        return mv;
    }

    // 댓글 추가

    // 댓글 수정

    // 댓글 삭제
}
