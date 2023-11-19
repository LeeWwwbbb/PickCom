package com.pickCom.admin;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("adminServiceImp")
public class AdminServiceImp implements AdminService {
    @Resource(name = "adminDAO")
    private AdminDAO adminDAO;

    @Override
    public void MemberDelete(int user) throws Exception {
        System.out.println("MemberDelete method called with user: " + user);
        adminDAO.MemberDelete(user);
    }

    @Override
    public void MemberUpdate(Map<String, Object> map) throws Exception {
        adminDAO.MemberUpdate(map);
    }

    @Override
    public List<Map<String, Object>> idFindUser(Map<String, Object> map) throws Exception{
        return adminDAO.idFindUser(map);
    }

    @Override
    public List<Map<String, Object>> aliasFindUser(Map<String, Object> map) throws Exception{
        return adminDAO.aliasFindUser(map);
    }

    @Override
    public List<Map<String, Object>> MemberList(Map<String, Object> map) throws Exception{
        return adminDAO.MemberList(map);
    }
}
