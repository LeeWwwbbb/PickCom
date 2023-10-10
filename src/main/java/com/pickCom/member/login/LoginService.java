package com.pickCom.member.login;

import java.util.Map;

public interface LoginService {
    // 로그인
    public Map<String, Object> memberLogin(Map<String, Object> map) throws Exception;

    // 로그아웃
    public Map<String, Object> memberLogout(Map<String, Object> map) throws Exception;

    // 아이디 찾기
    public Map<String, Object> findId(Map<String, Object> map) throws Exception;

    // 비밀번호 찾기
    public Map<String, Object> findPw(Map<String, Object> map) throws Exception;

}
