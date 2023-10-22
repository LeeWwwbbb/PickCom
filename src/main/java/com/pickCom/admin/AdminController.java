package com.pickCom.admin;

import com.pickCom.common.common.CommandMap;
import com.pickCom.member.MemberDTO;
import com.pickCom.utils.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource(name = "adminService")
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
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        Map<String, Object> map = new HashMap<>();

        if (searchWord != null) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        int totalCount = 0;
        try {
            totalCount = adminService.MemberCount(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageSize = Integer.parseInt("YOUR_PAGE_SIZE"); // 페이지 크기 설정
        int blockPage = Integer.parseInt("YOUR_BLOCK_SIZE"); // 페이지 블록 크기 설정

        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
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

        String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/admin/userList");
        map.put("pagingImg", pagingImg);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        model.addAttribute("userLists", userLists);
        model.addAttribute("map", map);

        return "admin/userManager";
    }

}