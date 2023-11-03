package com.pickCom.admin;

import com.pickCom.common.dao.AbstractDAO;
import com.pickCom.member.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository("adminDAO")
public class AdminDAO extends AbstractDAO {

    // 유저 강제 탈퇴
    public void MemberDelete(int user) throws Exception{
        System.out.println("user : " + user);
        delete("admin.deleteUser", user);
    }
    // 유저 정보 업데이트
    public void MemberUpdate(Map<String, Object> map) throws Exception {
        System.out.println("map: " + map);
        update("admin.updateUser", map);
    }
    // 유저 리스트 갖고오기
    public List<Map<String, Object>> MemberList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("admin.UserList", map);
    }

    public List<Map<String, Object>> idFindUser(Map<String, Object> id) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("admin.idFindUser", id);
    }
    public List<Map<String, Object>> aliasFindUser(Map<String, Object> alias) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("admin.aliasFindUser", alias);
    }


    /*public List<Map<String,Object>> MemberList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("admin.user_list", map);
    }
    public List<Map<String, Object>> MemberCount(Map<String, Object> map) throws Exception{
        AbstractDAO sqlSession = null;
        return dashBoard("admin.selectCount",map);
    }*/
}
