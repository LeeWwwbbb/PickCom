package com.pickCom.admin;

import com.pickCom.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("adminDAO")
public class AdminDAO extends AbstractDAO {
    // 유저 강제 탈퇴
    public void MemberDelete(Map<String, Object> map) throws Exception{
        delete("admin.deleteUser", map);
    }
    // 유저 정보 업데이트
    public void MemberUpdate(Map<String, Object> map) throws Exception {
        update("admin.updateUser", map);
    }
    public void MemberList(Map<String, Object> map) throws Exception{
        selectList("admin.user_list", map);
    }
}
