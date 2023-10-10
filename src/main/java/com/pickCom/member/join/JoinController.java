package com.pickCom.member.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.pickCom.common.CommandMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class JoinController {
    @Autowired
    private JoinService joinService;

    // 회원가입 폼
    @RequestMapping(value="/joinMain.do")
    public ModelAndView joinForm(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("views/login/join_main");


        return mv;
    }

    // 회원가입 처리
    @RequestMapping(value="/joinAction.do", method= RequestMethod.POST)
    public ModelAndView insertMember(CommandMap commandMap, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();

        /*// 이메일
        String email = request.getParameter("MEMBER_EMAIL") + "@" + request.getParameter("MEMBER_EMAIL2");
        System.out.println("이메일 : "+email);
        // 직접입력일 경우
        if(request.getParameter("MEMBER_EMAIL") == "") {
            email = request.getParameter("MEMBER_EMAIL");
        }
        commandMap.remove("MEMBER_EMAIL");
        commandMap.put("MEMBER_EMAIL", email);

        String birth = request.getParameter("MEMBER_BIRTH")
                + request.getParameter("MEMBER_BIRTH2")
                + request.getParameter("MEMBER_BIRTH3");
        commandMap.remove("MEMBER_BIRTH");
        commandMap.put("MEMBER_BIRTH", birth);*/
        if (!JoinPattern.idChk(request.getParameter("MEMBER_ID"))){

        } else if (!JoinPattern.nameChk(request.getParameter("MEMBER_NICKNAME"))) {

        } else if (!JoinPattern.pwdChk(request.getParameter("MEMBER_PASSWD"))) {

        } else{
            joinService.memberInsert(commandMap.getMap());

            mv.addObject("MEMBER_NAME", commandMap.get("MEMBER_NAME"));
            mv.setViewName("/views/login/joinAction");
        }
        return mv;
    }

    //이메일 인증-회원가입
    @RequestMapping(value = "/join_main.do", produces = "application/json")
    @ResponseBody
    public boolean sendMailAuth(HttpSession session, @RequestParam String user_email) {
        int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);

        try{
            new MailSender(user_email, ran);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    //아이디 중복 체크
    @RequestMapping(value="/selectIdCheck.do", method=RequestMethod.GET)
    @ResponseBody
    public int selectIdCheck(@RequestParam("mem_userid") String mem_userid) throws Exception{

        int cnt = joinService.idCheck(mem_userid);

        return cnt;
    }

}
