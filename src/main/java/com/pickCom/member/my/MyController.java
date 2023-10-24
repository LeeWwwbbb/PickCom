package com.pickCom.member.my;

import com.pickCom.common.common.CommandMap;
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

    /*// 마이페이지 메인
    @RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
    public ModelAndView orderList(Map<String, Object> commandMap) throws Exception {

        ModelAndView mv = new ModelAndView("/my/myPage");

        return mv;
    }

    // 수정 전 비밀번호 확인
    @RequestMapping(value = "/my/memberModify.do", method = RequestMethod.GET)
    public ModelAndView pwdCheck() throws Exception {
        ModelAndView mv = new ModelAndView("my/pwdCheck");

        return mv;
    }*/

    //회원 정보 수정 폼 이동
    @RequestMapping(value = "/my/memberModify.do")
    public ModelAndView selectMember(CommandMap commandMap, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        int id = (int) session.getAttribute("num");
        commandMap.put("member_num", id);
        // System.out.println("비빌번호 입력시"+ commandMap.getMap());
        //세션값 가져오기
        /*commandMap.put("MEMBER_ID", id);

        String pw = (String) myService.passwdCheck(commandMap.getMap(), "MEMBER_ID");
        Map<String, Object> MemberInfo;

        if (id.equals(pw)) {

            mv.setViewName("my/memberModify");
            MemberInfo = myService.memberModify(id);
            mv.addObject("MEMBER", MemberInfo);

        } else {
            mv.addObject("alert", "비밀번호가 올바르지 않습니다.");
            mv.setViewName("my/pwdCheck");
        }*/
        Map<String, Object> MemberInfo = myService.memberModify(commandMap.getMap());
        System.out.println(MemberInfo);
        mv.setViewName("my/myPage");
        mv.addObject("MEMBER", MemberInfo.get("map"));
        return mv;
    }

    /*// 회원 정보 수정 처리
    @RequestMapping(value = "/my/memberModifyAction.do", method = RequestMethod.POST)
    public ModelAndView memberModifyAction(CommandMap commandMap, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("my/myOrderList");
        System.out.println("수정클릭" + commandMap.getMap());
        Object MEMBER_NO = "";

        //세션값 가져오기
        HttpSession session = request.getSession();
        MEMBER_NO = (Object) session.getAttribute("SESSION_NO");

        commandMap.remove("MEMBER_NO"); // 기존 회원번호 데이터 삭제
        commandMap.put("MEMBER_NO", MEMBER_NO); // 세션 값으로 적용

        // 이메일, SMS 수신 여부
        String email_agree = (String) commandMap.get("EMAIL_AGREE");
        String sms_agree = (String) commandMap.get("SMS_AGREE");

        // 체크를 하지 않으면 '0' 으로 set 후 넘김
        if (email_agree == null) {
            email_agree = "0";
            commandMap.put("EMAIL_AGREE", email_agree);
        }
        if (sms_agree == null) {
            sms_agree = "0";
            commandMap.put("SMS_AGREE", sms_agree);
        }

        // 이메일
        String email = request.getParameter("MEMBER_EMAIL");

        //System.out.println("MEMBER_EMAIL : " + email);
        commandMap.remove("MEMBER_EMAIL"); // 기존 MEMBER_EMAIL 데이터 삭제
        commandMap.put("MEMBER_EMAIL", email); // 위에 정의한 email로 put
        // 생일
        // birth = 년  + 월  + 일;
        String birth = request.getParameter("MEMBER_BIRTH")
                + request.getParameter("MEMBER_BIRTH2")
                + request.getParameter("MEMBER_BIRTH3");

        //System.out.println("MEMBER_BIRTH : " + birth);

        commandMap.remove("MEMBER_BIRTH"); // 기존 MEMBER_BIRTH 데이터 삭제
        commandMap.put("MEMBER_BIRTH", birth); // 위에 정의한 birth로 put

        //myService.memberModify(commandMap.getMap());

        return mv;
    }*/

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
