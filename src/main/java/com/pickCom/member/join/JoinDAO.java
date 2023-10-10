package com.pickCom.member.join;


import org.springframework.stereotype.Repository;
import com.pickCom.common.AbstractDAO;

import java.util.Map;

@Repository("joinDAO")
public class JoinDAO extends AbstractDAO {
    // 회원가입
    public void memberInsert(Map<String, Object> map) throws Exception{
        insert("join.insertMember", map);
    }

    // 아이디 중복 확인
    public int idCheck(String MEMBER_ID) throws Exception{
        return (int)selectOne("join.selectIdCheck", MEMBER_ID);
    }

    // 이메일 중복체크
    public int selectEmailCheck(String email) throws Exception{
        return (int)selectOne("join.selectEmailCheck", email);
    }
}
