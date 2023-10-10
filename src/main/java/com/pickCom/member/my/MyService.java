package com.pickCom.member.my;


import com.pickCom.common.CommandMap;

import java.util.List;
import java.util.Map;

public interface MyService {
    // 구매 목록
    public List<Map<String,Object>> orderList(Map<String,Object> map) throws Exception;

    // 닉네임 변경
    public void nickNameChange(Map<String, Object> map) throws Exception;

    // 비밀번호 확인
    public String passwdCheck(Map<String, Object> map, String id) throws Exception;

    // 비밀번호 변경
    public void passwdChange(Map<String, Object> map) throws Exception;

    // 회원 탈퇴
    public void memberDelete(Map<String, Object> map) throws Exception;

    // 게시글 좋아요 목록
    public List<Map<String, Object>> boardLikeList(CommandMap commandMap) throws Exception;

    // 제품 좋아요 목록
    public List<Map<String, Object>> productLikeList(CommandMap commandMap) throws Exception;

    // 게시글 목록
    public List<Map<String,Object>> boardList(CommandMap commandMap) throws Exception;

    // 댓글 목록
    public List<Map<String,Object>> replyList(CommandMap commandMap) throws Exception;

    // 리뷰 목록
    public List<Map<String,Object>> reviewList(CommandMap commandMap) throws Exception;
}
