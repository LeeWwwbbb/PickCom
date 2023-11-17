package com.pickCom.member.my;

import com.pickCom.common.common.CommandMap;
import com.pickCom.member.login.LoginService;
import com.pickCom.utils.BoardPage;
import com.pickCom.utils.TranslateCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
    @Resource(name = "myServiceImp")
    private MyService myService;

    @Resource(name = "loginServiceImp")
    private LoginService loginService;

    // 비밀번호 확인
    @RequestMapping(value = "/my/pwCheck.do", method = RequestMethod.POST)
    public ModelAndView pwdCheck(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> chk = loginService.memberLogin(commandMap.getMap());
        System.out.println(commandMap.getMap());
        if(chk == null){
            mv.setViewName("/my/pw_check");
            String id = (String)session.getAttribute("id");
            mv.addObject("member_id", id);
            mv.addObject("message", "비밀번호가 일치하지 않습니다.");
        }else{
            session.setAttribute("pwCheck", true);
            mv = new ModelAndView("redirect:/my/memberModify.do");
        }
        return mv;
    }

    //회원 정보 수정 폼 이동
    @RequestMapping(value = "/my/memberModify.do")
    public ModelAndView selectMember(CommandMap commandMap, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        int num = (int) session.getAttribute("num");
        commandMap.put("member_num", num);
        Map<String, Object> MemberInfo = myService.memberModify(commandMap.getMap());
        if ((boolean) session.getAttribute("pwCheck")) {
            mv.setViewName("my/myPage");
            mv.addObject("MEMBER", MemberInfo.get("map"));
        } else {
            String id = (String)session.getAttribute("id");
            mv.setViewName("/my/pw_check");
            mv.addObject("member_id", id);
        }
        return mv;
    }

    // 비밀번호 변경 폼
    @RequestMapping(value = "/my/pwChange.do")
    public ModelAndView pwChange(HttpSession session, CommandMap commandMap, RedirectAttributes ra) throws Exception {
        ModelAndView mv = new ModelAndView("my/pw_change");
        String id = (String)session.getAttribute("id");
        mv.addObject("member_id", id);

        return mv;
    }

    // 비밀번호 변경
    @RequestMapping(value = "/my/pwChangeAction.do")
    public ModelAndView pwChangeAction(HttpServletRequest request, CommandMap commandMap, RedirectAttributes ra) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false);
        session.invalidate();
        myService.passwdChange(commandMap.getMap());
        mv.addObject("message", "비밀번호 변경 성공");
        mv = new ModelAndView("redirect:/");
        return mv;
    }

    // 회원 탈퇴 폼
    @RequestMapping(value = "/my/memberDelete.do")
    public ModelAndView memberDelete(Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("my/memberDelete");

        return mv;
    }

    // 회원 탈퇴 처리
    @RequestMapping(value = "/my/memberDeleteAction.do")
    @ResponseBody
    public ModelAndView memberDeleteAction(Map<String, Object> commandMap, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        int id = (int) session.getAttribute("num");
        commandMap.put("MEMBER_NUM", id);
        myService.memberDelete(commandMap);
        session.invalidate();
        mv = new ModelAndView("redirect:/");
        return mv;
    }

    // 마이페이지 사이드바
    @RequestMapping(value = "/my/{category}")
    public ModelAndView selectMyList(CommandMap map, HttpSession session, @RequestParam(required = false) String pageNum, @PathVariable String category) throws Exception {
        ModelAndView mv = new ModelAndView();
        int id = (int) session.getAttribute("num");
        map.put("member_num", id);

        System.out.println(category);

        // 페이징 관련 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        // 게시물 리스트를 가져오는 서비스 호출
        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = null;
        switch (category) {
            case "board" :
                mv.setViewName("my/post");
                list = myService.boardList(map.getMap());
                break;
            case "comment":
                mv.setViewName("my/comment");
                list = myService.commentList(map.getMap());
                break;
            case "like":
                mv.setViewName("my/like_post");
                list = myService.likeList(map.getMap());

                break;
        }
        System.out.println(list);

        if (list != null && !list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = BoardPage.pagingStr(totalCount, pageSize, pageCount, page, "/my/" + category);

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("boardList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("boardList", new ArrayList<>());
        }

        return mv;
    }
}
