package com.pickCom.member.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pickCom.common.common.CommandMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Resource(name = "loginService")
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
            mv.setViewName("login/loginForm");
            mv.addObject("message", "해당 아이디 혹은 비밀번호가 일치하지 않습니다.");
        } else {
            if (chk.get("MEMBER_DELETE").equals(true)) {
                mv.setViewName("login/loginForm");
                mv.addObject("message", "탈퇴한 회원 입니다.");
            } else {
                if (chk.get("MEMBER_PASSWD").equals(commandMap.get("MEMBER_PASSWD"))) {
                    session.setAttribute("num", chk.get("MEMBER_NUM"));
                    session.setAttribute("rank", chk.get("MEMBER_RANK"));

                    mv = new ModelAndView("redirect:/");
                    mv.addObject("MEMBER", chk);
                }
            }
        }

        return mv;
    }

    // 로그아웃
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception {

        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        map.put("URL", url);

        return map;
    }

    // 아이디 찾기 폼
    @RequestMapping(value = "/findId.do")
    public ModelAndView findId(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/findId");

        return mv;
    }

    // 아이디 찾기
    public String selectSearchMyId(HttpSession session, CommandMap commandMap, RedirectAttributes ra) throws Exception {
        /*String email = (String) commandMap.get("MEMBER_EMAIL");
        Map<String, Object> map = loginService.selectFindId(commandMap.getMap());
        if (map == null) {
            ra.addFlashAttribute("resultMsg", "입력된 정보가 일치하지 않습니다.");
            return "redirect:/findId.do";
        }
        String user_name = (String) map.get("MEMBER_NAME");
        String user = (String) map.get("MEMBER_ID");

        String subject = "<STYLE IS YOU>" + user_name + "님, 아이디 찾기 결과 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 아이디는 " + user + " 입니다.");
        joinService.send(subject, sb.toString(), "1teampjt@gmail.com", email, null);
        ra.addFlashAttribute("resultMsg", "아이디가 발송되었습니다. 이메일을 확인해주세요.");
        ra.addFlashAttribute("isResult", "1");*/

        return "redirect:/findId.do";
    }

    // 비밀번호 초기화 폼
    @RequestMapping(value = "/findPw.do")
    public ModelAndView findPw(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("login/findPw");

        return mv;
    }

    // 비밀번호 초기화
    @RequestMapping(value = "/findPwAction.do", method = RequestMethod.POST)
    public String sendMailPassword(HttpSession session, CommandMap commandMap, RedirectAttributes ra) throws Exception {
        /*String email = (String) commandMap.get("MEMBER_EMAIL");
        String user = loginService.selectFindPw(commandMap.getMap());

        if (user == null) {
            ra.addFlashAttribute("resultMsg", "입력된 정보가 일치하지 않습니다.");
            return "redirect:/findPw.do";
        }

        int ran = new Random().nextInt(100000) + 10000;
        String password = String.valueOf(ran);

        commandMap.put("MEMBER_PASSWD", password);
        loginService.updatePw(commandMap.getMap());

        String subject = "<STYLE IS YOU>임시 비밀번호입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 임시 비밀번호는 " + password + " 입니다. 로그인 후 패스워드를 변경해 주세요.");
        joinService.send(subject, sb.toString(), "1teampjt@gmail.com", email, null);
        ra.addFlashAttribute("resultMsg", "비밀번호가 재설정 되었습니다. 이메일을 확인해주세요.");
        ra.addFlashAttribute("isResult", "1");*/

        return "redirect:/findPw.do";
    }
}
