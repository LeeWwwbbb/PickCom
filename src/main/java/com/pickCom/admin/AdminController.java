package com.pickCom.admin;

import com.pickCom.common.common.CommandMap;
import com.pickCom.member.MemberDTO;
import com.pickCom.utils.AdminPage;
import com.pickCom.utils.BoardPage;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Resource(name = "adminServiceImp")
    private AdminService adminService;

    //유저 관리창
    @RequestMapping(value = "/usermanage")
    public ModelAndView admin(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("admin/userManager");
        return mv;
    }

    // 유저 관리창 리스트
    @RequestMapping(value = "/userList")
    public ModelAndView MemberList(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("admin/userManager");

        // 페이징 설정
        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        //유저 리스트를 가져오는 서비스 호출
        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = null;
        try {
            list = adminService.MemberList(map.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(list);
//        mv.addObject("adminList", list);

        if (!list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = AdminPage.pagingStr(totalCount, pageSize, pageCount, page, "/userList/");

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("adminList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("adminList", list);
        }

        return mv;
    }

    /*
    public String userList(Model model,
                           @RequestParam(defaultValue = "") String type,
                           @RequestParam(defaultValue = "") String search,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int pageSize) {
        System.out.println("리스트 가져오기");
        PageVo pageVo = new PageVo(page, pageSize);

        model.addAttribute("pagesize", pageSize);
        Map<String, Object> map = new HashMap<>();

        if(!type.equals("") && !search.equals("")){
            pageVo.setVal(search);
        }

        pageSize = Integer.parseInt("YOUR_PAGE_SIZE"); // 페이지 크기 설정
        page = Integer.parseInt("YOUR_BLOCK_SIZE"); // 페이지 블록 크기 설정

        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;
        map.put("start", start);
        map.put("end", end);

        List<MemberDTO> userLists = null;

        try {
            userLists = adminService.MemberList(map);
        } catch (Exception e) {
            e.printStackTrace();

     */
/*<<<<<<< HEAD
            // 예외 처리 후 리턴값을 설정할 수 있습니다.
            *//*return "errorPage"; // 혹은 다른 오류 페이지로 리다이렉트*//*
=======
>>>>>>> b132815cdd390c71ea5605a4f4e6819ce779e18a*/
    /*
        }

        map.put("pageSize", pageSize);
        map.put("pageNum", page);

        model.addAttribute("userLists", userLists);
        model.addAttribute("map", map);

        return "admin/userManager";
    }
     */
// 유저 삭제
    @SneakyThrows
    @RequestMapping(value = "memberDelete/{idx}")
    public ModelAndView deleteUser(CommandMap map, @PathVariable int idx) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/userList");
        map.put("member_num", idx);
        adminService.MemberDelete(idx);
        System.out.println("idx값 = " + idx);

        return mv;
    }

    //유저 닉네임 업데이트
    @RequestMapping(value = "memberUpdate/{idx}/{userNm}")
    public ModelAndView updateUser(CommandMap map, @PathVariable int idx, @PathVariable String userNm) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/userList");
        System.out.println("username : " + userNm);
        System.out.println("num : " + idx);

        map.put("num", idx);
        map.put("name", userNm);
        adminService.MemberUpdate(map.getMap());


        return mv;
    }

}