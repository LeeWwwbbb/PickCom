package com.pickCom.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JSFunction;

@WebServlet("/admin/memberUpdate.do")
public class MemberUpdateController extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
    	int num = Integer.parseInt(req.getParameter("idx"));
    	String userName = (String) req.getParameter("userName");
    	AdminDAO dao = new AdminDAO();
    	int result = dao.updateUser(num, userName);
    	System.out.println(num + "---" + userName);
    	
    	JSFunction.alertLocation(resp, "회원 정보 수정 완료", "../admin/userList.do");
    }
}
