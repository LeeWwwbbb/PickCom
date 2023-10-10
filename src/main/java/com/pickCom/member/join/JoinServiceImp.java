package com.pickCom.member.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("joinService")
public class JoinServiceImp implements JoinService {
    @Autowired
    private JoinDAO joinDAO;

    // 회원가입
    @Override
    public void memberInsert(Map<String, Object> map) throws Exception{
        joinDAO.memberInsert(map);
    };

    // 아이디 중복체크
    @Override
    public int idCheck(String id) throws Exception{
        return joinDAO.idCheck(id);
    };

    // 이메일 중복 체크
    @Override
    public int selectEmailCheck(String email) throws Exception{
        return joinDAO.selectEmailCheck(email);
    }
}
