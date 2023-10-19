package com.pickCom.member.my;


import com.pickCom.common.common.CommandMap;

import java.util.List;
import java.util.Map;

public interface MyService {
    // 회원 정보 가져오기
    Map<String, Object> memberModify(int id) throws Exception;

    // 닉네임 변경
    void nickNameChange(Map<String, Object> map) throws Exception;

    // 비밀번호 확인
    String passwdCheck(Map<String, Object> map, String id) throws Exception;

    // 비밀번호 변경
    void passwdChange(Map<String, Object> map) throws Exception;

    // 회원 탈퇴
    void memberDelete(Map<String, Object> map) throws Exception;

    // 게시글 좋아요 목록
    List<Map<String, Object>> selectMyLikeList(CommandMap commandMap) throws Exception;

    // 게시글 목록
    List<Map<String,Object>> selectMyList(CommandMap commandMap) throws Exception;

    // 댓글 목록
    List<Map<String,Object>> selectMyCommentList(CommandMap commandMap) throws Exception;
}
