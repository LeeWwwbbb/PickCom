package com.pickCom.member.login;

import com.pickCom.member.join.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pickCom.common.common.CommandMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginController {
    @Resource(name = "loginServiceImp")
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public ModelAndView loginForm(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/loginForm");

        return mv;
    }

    // 로그인 이후 메인페이지 이동
    @RequestMapping(value = "/loginAction.do", method = RequestMethod.POST)
    public ModelAndView loginAction(CommandMap commandMap, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Map<String, Object> chk = loginService.memberLogin(commandMap.getMap());
        System.out.println(commandMap.getMap());
        System.out.println(chk);

        if (chk == null) {
            mv.setViewName("/login/loginForm");
            mv.addObject("message", "해당 아이디 혹은 비밀번호가 일치하지 않습니다.");
        } else {
            if ((Integer) chk.get("MEMBER_DELETE")>0) {
                mv.setViewName("/login/loginForm");
                mv.addObject("message", "탈퇴한 회원 입니다.");
            } else {
                if (chk.get("MEMBER_PASSWD").equals(commandMap.get("MEMBER_PASSWD"))) {
                    session.setAttribute("num", chk.get("MEMBER_NUM"));
                    session.setAttribute("id", chk.get("MEMBER_ID"));
                    session.setAttribute("name", chk.get("MEMBER_NICKNAME"));
                    session.setAttribute("rank", chk.get("MEMBER_RANK"));
                    session.setAttribute("loginComplete", true);

                    mv = new ModelAndView("redirect:/");
                    mv.addObject("MEMBER", chk);
                }
            }
        }
        System.out.println(session.getAttribute("num"));

        return mv;
    }

    // 로그아웃
    @RequestMapping(value = "/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false);

        if (session != null) {
            try {
                session.setAttribute("logoutComplete", true);
                session.invalidate();
                mv.addObject("logout", "로그아웃 되었습니다.");
            } catch (IllegalStateException e) {
                mv.addObject("logout", "세션이 이미 종료되었습니다.");
            }
        } else {
            mv.addObject("logout", "로그아웃 되었습니다.");
        }

        mv = new ModelAndView("redirect:/");
        return mv;
    }

    // 아이디 찾기 폼
    @RequestMapping(value = "/findId")
    public ModelAndView findId(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/id_find");
        return mv;
    }

    // 아이디 찾기
    @RequestMapping(value = "/findIdAction")
    public ModelAndView findIdAction(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/findAction");
        Map<String, Object> chk = loginService.findId(commandMap.getMap());
        String id = (String)chk.get("member_id");
        mv.addObject("member_id", id);
        return mv;
    }

    // 비밀번호 초기화 폼
    @RequestMapping(value = "/findPw")
    public ModelAndView findPw(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/pw_find");

        return mv;
    }

    // 비밀번호 초기화 폼으로 이동
    @RequestMapping(value = "/findPwAction")
    public ModelAndView findPwAction(HttpSession session, CommandMap commandMap, RedirectAttributes ra) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> chk = loginService.findId(commandMap.getMap());
        String id = (String)chk.get("member_id");
        if (commandMap.get("member_id").equals(id)){
            mv.setViewName("my/pw_change");
            mv.addObject("member_id", id);
        }else {
            mv.setViewName("login/pw_find");
            mv.addObject("message", "회원 정보가 일치하지 않습니다.");
        }
        return mv;
    }

     /*//이메일 인증
    @RequestMapping(value = "/findIdEmail.do", produces = "application/json")
    @ResponseBody
    public boolean sendMailAuth(HttpSession session, @RequestParam String email) {
        Integer ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String findCode = String.valueOf(ran);
        session.setAttribute("findCode", findCode);

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
        Integer storedCode = (Integer)session.getAttribute("findCode");
        if (storedCode != null && code != null && storedCode.toString().equals(code)) {
            return true;
        }
        return false;
    }*/

}
