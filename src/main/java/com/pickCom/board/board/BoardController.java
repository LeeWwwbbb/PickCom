package com.pickCom.board.board;

import com.pickCom.board.BoardDTO;
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
import javax.servlet.http.HttpSession;
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

    // 카테고리 게시판 리스트
    @RequestMapping("/board/{category}")
    public ModelAndView selectCateList(CommandMap map, @RequestParam(required = false) String pageNum, @PathVariable String category) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");
        map.put("cate", category);

        // 페이징 관련 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        // 게시물 리스트를 가져오는 서비스 호출
        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = boardService.selectBoardList(map.getMap());
        System.out.println(boardService.selectBoardList(map.getMap()));

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

    // 키워드 게시판 리스트
    @RequestMapping("/board/search")
    public ModelAndView selectKeywordList(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");
        System.out.println(map.getMap().toString());

        // 페이징 관련 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        // 게시물 리스트를 가져오는 서비스 호출
        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = boardService.selectBoardDetail(map.getMap());
        System.out.println(boardService.selectBoardDetail(map.getMap()));

        if (!list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, pageCount, page, "/board/search");

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("boardList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("boardList", list);
        }
        return mv;
    }

    // 게시글 열기
    @RequestMapping(value = "/board/{category}/{idx}")
    public ModelAndView openBoardDetail(CommandMap commandMap, HttpSession session, @PathVariable String category, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/View");
        commandMap.put("board_num", idx);
        commandMap.put("member_num", session.getAttribute("num"));
        Map<String, Object> map = boardService.openBoardDetail(commandMap.getMap());
        List<Map<String, Object>> list = boardService.selectCommentList(commandMap.getMap());
        boolean like = boardService.likeCheck(commandMap.getMap());
        String cate = TranslateCategory.translateCategory(category);
        mv.addObject("cate", cate);
        mv.addObject("map", map.get("map"));
        mv.addObject("commentList", list);
        mv.addObject("like", like);
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

        boardService.insertBoard(commandMap.getMap(), request);
        String cate =  (String)commandMap.get("board_cate");
        ModelAndView mv = new ModelAndView("redirect:/board/"+cate);

        return mv;
    }

    // 글 수정 폼
    @RequestMapping(value="/board/openBoardUpdate/{idx}")
    public ModelAndView openBoardUpdate(CommandMap commandMap, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Edit");
        commandMap.put("board_num", idx);

        Map<String,Object> map = boardService.openBoardDetail(commandMap.getMap());
        mv.addObject("map", map.get("map"));

        return mv;
    }

    // 글 수정
    @RequestMapping(value="/board/updateBoard.do")
    public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        String cate = (String)commandMap.get("board_cate");
        ModelAndView mv = new ModelAndView("redirect:/board/"+ cate + "/" + commandMap.get("board_num"));

        boardService.updateBoard(commandMap.getMap(), request);
        return mv;
    }

    // 글 삭제
    @RequestMapping(value="/board/deleteBoard/{category}/{idx}")
    public ModelAndView deleteBoard(CommandMap commandMap,  @PathVariable String category, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/"+ category);
        commandMap.put("board_num", idx);

        boardService.deleteBoard(commandMap.getMap());

        return mv;
    }

    // 글 추천
    @RequestMapping(value="/board/like/{category}/{idx}")
    public ModelAndView likeController(CommandMap commandMap, HttpSession session, @PathVariable String category, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/"+ category +"/" + idx);
        commandMap.put("board_num", idx);
        commandMap.put("member_num", session.getAttribute("num"));
        boolean like = boardService.likeCheck(commandMap.getMap());

        if (like)
            boardService.deleteLike(commandMap.getMap());
        else
            boardService.insertLike(commandMap.getMap());


        return mv;
    }

    // 댓글 추가
    @RequestMapping(value="/board/insertComment.do")
    public ModelAndView insertComment(CommandMap commandMap, HttpServletRequest request) throws Exception{
        String cate = (String)commandMap.get("board_cate");
        ModelAndView mv = new ModelAndView("redirect:/board/"+ cate + "/" + commandMap.get("board_num"));

        boardService.insertComment(commandMap.getMap(), request);



        return mv;
    }

    // 댓글 수정
    @RequestMapping(value="/board/updateComment.do")
    public ModelAndView updateComment(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/{category}/{idx}");

        boardService.updateComment(commandMap.getMap(), request);

        mv.addObject("COMMENT_NUM", commandMap.get("board_num"));
        return mv;
    }

    // 댓글 삭제
    @RequestMapping(value="/board/deleteComment/{category}/{idx}/{num}")
    public ModelAndView deleteComment(CommandMap commandMap, @PathVariable String category, @PathVariable int idx, @PathVariable int num) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/"+ category +"/" + idx);

        commandMap.put("comment_num", num);
        boardService.deleteComment(commandMap.getMap());

        return mv;
    }
}
