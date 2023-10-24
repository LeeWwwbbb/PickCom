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
import java.net.http.HttpResponse;
import java.util.Random;

@Controller
public class JoinController {
    @Resource(name = "joinServiceImp")
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
        int chk = joinService.selectEmailCheck((String)commandMap.get("MEMBER_EMAIL"));

        if (chk>0){
            mv = new ModelAndView("redirect:/join");
            mv.addObject("message", "이메일이 중복됩니다.");
            return mv;
        }
        joinService.memberInsert(commandMap.getMap());
        mv.addObject("MEMBER_NAME", commandMap.get("MEMBER_NAME"));
        mv.setViewName("login/joinAction");
        return mv;
    }

    //이메일 인증-회원가입
    @RequestMapping(value = "/joinEmail.do", produces = "application/json")
    @ResponseBody
    public boolean sendMailAuth(HttpSession session, @RequestParam String email) {
        Integer ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);

        try{
            new MailSender(email, ran);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 인증번호 확인
    @RequestMapping(value = "/joinCode.do", produces = "application/json")
    @ResponseBody
    public boolean checkMailCode(HttpSession session, @RequestParam String code) {
        String storedCode = (String)session.getAttribute("joinCode");
        return storedCode != null && code != null && storedCode.toString().equals(code);
    }

    //아이디 중복 체크
    @RequestMapping(value="/selectIdCheck.do", method=RequestMethod.POST)
    @ResponseBody
    public boolean selectIdCheck(@RequestParam("userId") String mem_userid) throws Exception{

        int cnt = joinService.idCheck(mem_userid);

        return cnt > 0;
    }

}
