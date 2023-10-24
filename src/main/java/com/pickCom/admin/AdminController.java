package com.pickCom.admin;

import com.pickCom.common.common.CommandMap;
import com.pickCom.paging.PageVo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController{
    @Resource(name = "adminServiceImp")
    private AdminService adminService;

    @RequestMapping(value = "/usermanage")
    public ModelAndView admin(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("admin/userManager");

        return mv;
    }

    @RequestMapping(value = "/userList.do")
    public String userList(Model model,
                           @RequestParam(value = "searchField", required = false) String searchField,
                           @RequestParam(value = "searchWord", required = false) String searchWord,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int pageSize) {
        PageVo pageVo = new PageVo(page, pageSize);

        model.addAttribute("pagesize", pageSize);
        Map<String, Object> map = new HashMap<>();

        if (searchWord != null) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        pageSize = Integer.parseInt("YOUR_PAGE_SIZE"); // 페이지 크기 설정
        page = Integer.parseInt("YOUR_BLOCK_SIZE"); // 페이지 블록 크기 설정

        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;
        map.put("start", start);
        map.put("end", end);

        List<Map<String, Object>> userLists = null;

        try {
            userLists = adminService.MemberList(map);
        } catch (Exception e) {
            e.printStackTrace();
/*<<<<<<< HEAD
            // 예외 처리 후 리턴값을 설정할 수 있습니다.
            *//*return "errorPage"; // 혹은 다른 오류 페이지로 리다이렉트*//*
=======
>>>>>>> b132815cdd390c71ea5605a4f4e6819ce779e18a*/
        }

        map.put("pageSize", pageSize);
        map.put("pageNum", page);

        model.addAttribute("userLists", userLists);
        model.addAttribute("map", map);

        return "admin/userManager";
    }

    @SneakyThrows
    @RequestMapping(path="deleteUser")
    public String deleteUser(String userid) {
        int cnt = adminService.MemberDelete(userid);
        if(cnt == 1 ) {
            return "redirect:pagingUser";
        }
        return "redirect:userModify?userid=" + userid;
    }

}