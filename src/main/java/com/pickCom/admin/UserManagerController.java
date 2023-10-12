package com.pickCom.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/admin/userManage.do")
public class UserManagerController extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
    	int num = Integer.parseInt(req.getParameter("num"));
    	int day = Integer.parseInt(req.getParameter("statDays"));
        String reason = req.getParameter("reason");

        AdminDAO dao = new AdminDAO();
        MemberDAO member = new MemberDAO();
        MemberDTO dto = member.memberSelect(num);
        String name = dto.getMember_name();
        int result = dao.suspendUser(num, day, reason);

        if (result == 0) {
        	// 중복된 아이디인 경우
            resp.getWriter().write("{\"isDuplicate\": true}");
        } else {
            // 사용 가능한 아이디인 경우
            resp.getWriter().write("{\"isDuplicate\": false}");
        }
    }
}
