package com.pickCom.admin;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImp implements AdminService {
    @Resource(name = "adminDAO")
    private AdminDAO adminDAO;

    @Override
    public void MemberDelete(Map<String, Object> map) throws Exception {
        adminDAO.MemberDelete(map);
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
