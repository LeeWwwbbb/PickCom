package com.pickCom.board.board;

import com.pickCom.common.common.CommandMap;
import com.pickCom.utils.BoardPage;
import com.pickCom.utils.TranslateCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Resource(name = "boardServiceImp")
    private BoardService boardService;

    // 게시판
    @RequestMapping(value="/boardList")
    public ModelAndView boardList(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");
        List<Map<String, Object>> list = boardService.boardList(commandMap.getMap());
        System.out.println(list);

        return mv;
    }

    @RequestMapping("/board/{category}")
    public ModelAndView selectCateList(CommandMap commandMap, @RequestParam(required = false) String pageNum, @PathVariable String category) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");
        commandMap.put("cate", category);

        // 페이징 관련 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        // 게시물 리스트를 가져오는 서비스 호출
        commandMap.put("start", start);
        commandMap.put("end", end);
        commandMap.put("pageSize", pageSize);
        List<Map<String, Object>> list = boardService.selectBoardList(commandMap.getMap());
        System.out.println(boardService.selectBoardList(commandMap.getMap()));

        if (!list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, pageCount, page, "/board/" + category);

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("boardList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("boardList", list);
        }

        String cate = TranslateCategory.translateCategory(category);
        mv.addObject("cate", cate);

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
    @RequestMapping(value="/board/write")
    public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Write");

        return mv;
    }

    // 글 등록
    @RequestMapping(value="/board/insertBoard.do", method = RequestMethod.POST )
    public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{num}");

        boardService.insertBoard(commandMap.getMap(), request);



        return mv;
    }

    // 글 수정 폼
    @RequestMapping(value="/board/openBoardUpdate.do")
    public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Edit");

        Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
        mv.addObject("map", map.get("map"));
        mv.addObject("list", map.get("list"));

        return mv;
    }

    // 글 수정
    @RequestMapping(value="/board/updateBoard.do")
    public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{num}");

        boardService.updateBoard(commandMap.getMap(), request);

        mv.addObject("BOARD_NUM", commandMap.get("board_num"));
        return mv;
    }

    // 글 삭제
    @RequestMapping(value="/board/deleteBoard.do")
    public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}");

        boardService.deleteBoard(commandMap.getMap());

        return mv;
    }

    // 댓글 추가
    @RequestMapping(value="/board/insertComment.do", method = RequestMethod.POST )
    public ModelAndView insertComment(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{num}");

        boardService.insertComment(commandMap.getMap(), request);



        return mv;
    }

    // 댓글 수정
    @RequestMapping(value="/board/updateComment.do")
    public ModelAndView updateComment(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{num}"); // 수정해야함

        boardService.updateComment(commandMap.getMap(), request);

        mv.addObject("COMMENT_NUM", commandMap.get("board_num"));
        return mv;
    }

    // 댓글 삭제
    @RequestMapping(value="/board/deleteComment.do")
    public ModelAndView deleteComment(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{num}"); // 수정해야함

        boardService.deleteComment(commandMap.getMap());

        return mv;
    }
}
