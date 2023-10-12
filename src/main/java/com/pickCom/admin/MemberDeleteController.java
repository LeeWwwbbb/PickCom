package com.pickCom.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JSFunction;

@WebServlet("/admin/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
    	int num = Integer.parseInt(req.getParameter("idx"));
    	AdminDAO dao = new AdminDAO();
    	int result = dao.deleteUser(num);
    	System.out.println(num + "---");
    	
    	JSFunction.alertLocation(resp, "회원 정보 삭제 완료", "../admin/userList.do");
    }
}
