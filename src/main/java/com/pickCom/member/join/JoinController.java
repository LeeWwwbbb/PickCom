package com.pickCom.member.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.pickCom.common.common.CommandMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class JoinController {
    @Resource(name = "joinService")
    private JoinService joinService;

    // 회원가입 약관
    @RequestMapping(value="/joinTerms")
    public ModelAndView joinTerms(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/join_terms");


        return mv;
    }

    // 회원가입 폼
    @RequestMapping(value="/join")
    public ModelAndView joinForm(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/join_main");


        return mv;
    }

    // 회원가입 처리
    @RequestMapping(value="/joinAction.do", method= RequestMethod.POST)
    public ModelAndView insertMember(CommandMap commandMap, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();

        joinService.memberInsert(commandMap.getMap());

        mv.addObject("MEMBER_NAME", commandMap.get("MEMBER_NAME"));
        mv.setViewName("login/joinAction");
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
