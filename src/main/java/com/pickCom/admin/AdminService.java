package com.pickCom.admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    // 유저 리스트
    List<Map<String, Object>> MemberList(Map<String, Object> map) throws Exception;
    // 유저 강제 탈퇴
    public void MemberDelete(Map<String, Object> map) throws Exception;
    // 유저 정보 업데이트
    public void MemberUpdate(Map<String, Object> map) throws Exception;

    int MemberCount(Map<String, Object> map) throws Exception;
}

