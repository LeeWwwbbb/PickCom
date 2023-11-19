package com.pickCom.admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    // 유저 리스트
    List<Map<String, Object>> MemberList(Map<String, Object> map) throws Exception;
    // 유저 강제 탈퇴
    public void MemberDelete(int member) throws Exception;
    // 유저 정보 업데이트
    public void MemberUpdate(Map<String, Object> map) throws Exception;

    // id로 검색
    //아이디로 검색
    List<Map<String, Object>> idFindUser(Map<String, Object> map)throws Exception;


    //별명으로 검색
    List<Map<String, Object>> aliasFindUser(Map<String, Object> map)throws Exception;

}

