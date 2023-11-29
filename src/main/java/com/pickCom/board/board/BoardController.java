package com.pickCom.board.board;

import com.pickCom.common.common.CommandMap;
import com.pickCom.utils.BoardPage;
import com.pickCom.utils.TranslateCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

        int pageSize = 15;
        int blockPage = 10;
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

            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, blockPage, page, "/board/" + category);

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

    // 키워드 리스트
    @RequestMapping("/board/search")
    public ModelAndView selectKeywordList(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/board/List");
        System.out.println(map.getMap().toString());
        String kw = map.get("keyword").toString();
        String keyword = "%" + kw + "%";
        map.put("keyword", keyword);
        mv.addObject("keyword", kw);

        // 페이징 관련 설정
        int pageSize = 10;
        int blockPage = 10;
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

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, blockPage, page, "/board/search");

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
    public ModelAndView openBoardDetail(CommandMap commandMap, HttpSession session, @PathVariable String category, @PathVariable int idx, HttpServletRequest req, HttpServletResponse res) throws Exception{
        ModelAndView mv = new ModelAndView("/board/View");
        commandMap.put("board_num", idx);

        Cookie oldCookie = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("boardView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + idx + "]")) {
                boardService.incrementViewCount(commandMap.getMap());
                oldCookie.setValue(oldCookie.getValue() + "_[" + idx + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            boardService.incrementViewCount(commandMap.getMap());
            Cookie newCookie = new Cookie("boardView","[" + idx + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }

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
        String category = (String)commandMap.get("wCate");
        System.out.println(category);
        mv.addObject("category", category);
        return mv;
    }

    // 글 등록
    @RequestMapping(value="/board/insertBoard.do", method = RequestMethod.POST)
    public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        // 이미지 파일이 업로드된 경우에만 이미지를 저장
        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String uploadPath = request.getServletContext().getRealPath("/uploaded-files/") + savedFileName; // 저장 경로

            // 파일 저장
            try {
                file.transferTo(new File(uploadPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // DB에 저장할 파일 정보 설정
            commandMap.put("image_originalName", originalFileName);
            commandMap.put("image_saveName", savedFileName);
            commandMap.put("image_size", file.getSize());
        }

        // 줄바꿈 처리
        String boardContent = (String) commandMap.get("board_content");
        if (boardContent != null) {
            boardContent = boardContent.replaceAll("\\n", "<br>");
            commandMap.put("board_content", boardContent);
        }

        boardService.insertBoard(commandMap.getMap(), request);
        String cate = (String) commandMap.get("board_cate");
        ModelAndView mv = new ModelAndView("redirect:/board/"+cate);

        return mv;
    }

    // 글 수정 폼
    @RequestMapping(value="/board/openBoardUpdate/{idx}")
    public ModelAndView openBoardUpdate(CommandMap commandMap, @PathVariable int idx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/Edit");
        commandMap.put("board_num", idx);

        Map<String,Object> map = boardService.openBoardUpdate(commandMap.getMap());
        mv.addObject("map", map.get("map"));

        return mv;
    }

    // 글 수정
    @RequestMapping(value="/board/updateBoard.do")
    public ModelAndView updateBoard(CommandMap commandMap, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        // 게시물 정보 가져오기
        Map<String, Object> boardInfo = boardService.openBoardDetail(commandMap.getMap());

        // 줄바꿈 처리
        String boardContent = (String) commandMap.get("board_content");
        if (boardContent != null) {
            boardContent = boardContent.replaceAll("\\n", "<br>");
            commandMap.put("board_content", boardContent);
        }

        String cate = (String)commandMap.get("board_cate");
        ModelAndView mv = new ModelAndView("redirect:/board/"+ cate + "/" + commandMap.get("board_num"));

        // 이미지 파일이 업로드된 경우에만 이미지를 저장
        if (!file.isEmpty()) {
            String saveFileName = (String) boardInfo.get("image_saveName");
            if (saveFileName != null) {
                String uploadPath = request.getServletContext().getRealPath("/uploaded-files/") + saveFileName;
                File imageFile = new File(uploadPath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }
            String originalFileName = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String uploadPath = request.getServletContext().getRealPath("/uploaded-files/") + savedFileName; // 저장 경로

            // 파일 저장
            try {
                file.transferTo(new File(uploadPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // DB에 저장할 파일 정보 설정
            commandMap.put("image_originalName", originalFileName);
            commandMap.put("image_saveName", savedFileName);
            commandMap.put("image_size", file.getSize());

            boardService.updateImageBoard(commandMap.getMap(), request);
        } else
            boardService.updateBoard(commandMap.getMap(), request);

        return mv;
    }

    // 글 삭제
    @RequestMapping(value="/board/deleteBoard/{category}/{idx}")
    public ModelAndView deleteBoard(CommandMap commandMap,  @PathVariable String category, @PathVariable int idx, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/board/"+ category);
        commandMap.put("board_num", idx);

        // 게시물 정보 가져오기
        Map<String, Object> boardInfo = boardService.openBoardDetail(commandMap.getMap());

        if (boardInfo != null) {
            // 이미지 파일 삭제
            String savedFileName = (String) boardInfo.get("image_saveName");
            if (savedFileName != null) {
                String uploadPath = request.getServletContext().getRealPath("/uploaded-files/") + savedFileName;
                File imageFile = new File(uploadPath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }
        }

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
