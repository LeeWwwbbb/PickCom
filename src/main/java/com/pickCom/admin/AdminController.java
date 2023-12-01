package com.pickCom.admin;

import com.pickCom.common.common.CommandMap;
import com.pickCom.utils.AdminPage;
import com.pickCom.utils.BoardPage;
import lombok.SneakyThrows;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    // 키워드 리스트
    @RequestMapping(value = "/admin/search")
    public ModelAndView Search(CommandMap map, @RequestParam(required = false) String pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("admin/userManager");

        System.out.println(map.getMap().toString());
        String testKeyword = map.get("keyword").toString();
        mv.addObject("adminSearch", testKeyword);
        String keyword = "%" + testKeyword + "%";
        String searchField = map.get("searchField").toString();

        int pageSize = 10; // 한 페이지에 표시할 게시물 수
        int page = (pageNum != null) ? Integer.parseInt(pageNum) : 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        map.put("start", start);
        map.put("end", end);
        map.put("pageSize", pageSize);
        System.out.println("키워드 길이 : " + testKeyword.length());
        List<Map<String, Object>> list = null;
        if (testKeyword.length() > 0) {
            System.out.println("조건문 이상 없음");
            map.put("keyword", keyword);
            if (searchField.equals("i")) {
                list = adminService.idFindUser(map.getMap());
            } else if (searchField.equals("a")) {
                list = adminService.aliasFindUser(map.getMap());
            } else if (searchField.equals("")) {
                String popupScript = "alert('올바르지 않은 검색 유형을 선택했습니다. 올바른 유형을 선택해주세요.');";
                popupScript += "return false;"; // 폼 제출 방지

                mv.addObject("popupScript", popupScript);
            }
        } else {
            String popupScript = "alert('검색어를 입력하세요');";
            popupScript += "return false;"; // 폼 제출 방지

            mv.addObject("popupScript", popupScript);
        }

        if (!list.isEmpty()) {
            int totalCount = Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString());
            int pageCount = (int) Math.ceil((double) totalCount / pageSize);

            // 페이징 문자열 생성
            String pagingStr = AdminPage.pagingStr(totalCount, pageSize, pageCount, page, "/admin/search");

            // 모델에 페이징 문자열과 게시물 리스트를 추가
            mv.addObject("pagingStr", pagingStr);
            mv.addObject("adminList", list);
        } else {
            mv.addObject("pagingStr", "");
            mv.addObject("adminList", list);
        }

        return mv;
    }

    // 데이터 업데이트
    @RequestMapping(value = "/Update_Data")
    public ModelAndView Update_Data() throws Exception {
        /*String[] exePaths = {
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\Case.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\Cooler.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\CPU.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\HDD.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\MBoard.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\Power.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\RAM.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\SSD.exe",
                "C:\\Users\\byung\\Desktop\\JSP Project\\PickCom\\VGA.exe"
        };*/
        String[] exePaths = {
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\Case.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\Cooler.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\CPU.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\HDD.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\MBoard.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\Power.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\RAM.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\SSD.exe",
                "C:\\Users\\pojun\\Documents\\GitHub\\PickCom\\VGA.exe"
        };

        try {
            // 각 exe 파일에 대해 별도의 프로세스를 생성하고 시작
            for (String exePath : exePaths) {
                ProcessBuilder processBuilder = new ProcessBuilder(exePath);
                Process pro = processBuilder.start();

                // 프로세스의 출력을 읽어오기
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                // 에러 출력 읽어오기
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(pro.getErrorStream()))) {
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                int exitCode = pro.waitFor();
                System.out.println("프로세스 종료 코드: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("redirect:/Filtering_Data");
        return mv;
    }
    private static PythonInterpreter intPre;
    // 필터링
    @RequestMapping(value = "/Filtering_Data")
    public ModelAndView Filtering_Data() {
        System.out.println("시작");

        System.setProperty("python.import.site", "false");
        intPre = new PythonInterpreter();
        // 파이썬 스크립트 실행 명령어
        try {
            //intPre.execfile("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_hardware/Filtering_Data.py");
            intPre.execfile("C:/Users/pojun/Documents/GitHub/PickCom/src/main/resources/python/get_hardware/Filtering_Data.py");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("redirect:/SetDBtoJSON");
        return mv;
    }
    // json파일 db에 입력
    @RequestMapping(value = "/SetDBtoJSON")
    public ModelAndView SetDB(){
        intPre = new PythonInterpreter();
        //intPre.execfile("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_hardware/SetDBtoJSON.py");
        intPre.execfile("C:/Users/pojun/Documents/GitHub/PickCom/src/main/resources/python/get_hardware/SetDBtoJSON.py");
        ModelAndView mv = new ModelAndView("redirect:/PickCom");
        return mv;
    }

    // 각 가격별 추천 제품 json화
    @RequestMapping(value = "/PickCom")
    public ModelAndView PickCom(){
        intPre = new PythonInterpreter();
        //intPre.execfile("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_build/PickCom_py.py");
        intPre.execfile("C:/Users/pojun/Documents/GitHub/PickCom/src/main/resources/python/get_build/PickCom_py.py");
        ModelAndView mv = new ModelAndView("/admin/UpdateResult");
        return mv;
    }
}