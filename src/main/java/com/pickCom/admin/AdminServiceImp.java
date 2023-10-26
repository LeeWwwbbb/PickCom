package com.pickCom.admin;

import com.pickCom.member.MemberDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("adminServiceImp")
public class AdminServiceImp implements AdminService {
    @Resource(name = "adminDAO")
    private AdminDAO adminDAO;

    @Override
    public int MemberDelete(String user) throws Exception {
        adminDAO.MemberDelete(user);
        return 0;
    }

    @Override
    public void MemberUpdate(Map<String, Object> map) throws Exception {
        adminDAO.MemberUpdate(map);
    }

    @Override
    public List<Map<String, Object>> MemberList(Map<String, Object> map) throws Exception{
        return adminDAO.MemberList(map);
    }
}
