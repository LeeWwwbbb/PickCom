package com.pickCom.member.join;

import java.util.Map;

public interface JoinService {
    // 회원가입
    public void memberInsert(Map<String, Object> map) throws Exception;

    // 아이디 중복 확인
    public int idCheck(String MEMBER_ID) throws Exception;

    // 이메일 중복체크
    public int selectEmailCheck(String email) throws Exception;
}
