package com.pickCom.admin;

import com.pickCom.common.CommandMap;
import com.pickCom.member.MemberDTO;
import com.pickCom.utils.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController{
    @Autowired
    private AdminService adminService;

    @RequestMapping("/userList")
    public String userList(Model model,
                           @RequestParam(value = "searchField", required = false) String searchField,
                           @RequestParam(value = "searchWord", required = false) String searchWord,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        AdminDAO dao = new AdminDAO();
        Map<String, Object> map = new HashMap<>();

        if (searchWord != null) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        int totalCount = dao.selectCount(map);
        int pageSize = Integer.parseInt("YOUR_PAGE_SIZE"); // 페이지 크기 설정
        int blockPage = Integer.parseInt("YOUR_BLOCK_SIZE"); // 페이지 블록 크기 설정

        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
        map.put("start", start);
        map.put("end", end);

        List<MemberDTO> userLists = dao.selectListUser(map);

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