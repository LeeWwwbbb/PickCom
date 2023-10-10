package com.pickCom.member.my;

import org.springframework.stereotype.Repository;
import com.pickCom.common.AbstractDAO;
import com.pickCom.common.CommandMap;

import java.util.List;
import java.util.Map;

@Repository("myDAO")
public class MyDAO extends AbstractDAO {
    // 구매 목록
    public List<Map<String,Object>> orderList(Map<String,Object> map) throws Exception{
        return (List<Map<String,Object>>) orderList("my.selectOrderList",map);
    }

    // 닉네임 변경
    public void nickNameChange(Map<String, Object> map) throws Exception{
        update("my.nickNameChange", map);
    }

    // 비밀번호 확인
    public String passwdCheck(Map<String, Object> map, String id) throws Exception{
        return (String) selectOne("my.passwdCheck", map);
    }

    // 비밀번호 변경
    public void passwdChange(Map<String, Object> map) throws Exception{
        update("my.passwdChange", map);
    }

    // 회원 탈퇴
    public void memberDelete(Map<String, Object> map) throws Exception{
        update("my.memberDelete", map);
    }

    // 게시글 좋아요 목록
    public List<Map<String, Object>> boardLikeList(CommandMap commandMap) throws Exception{
        return (List<Map<String,Object>>) selectList("my.boardLikeList",commandMap.getMap());
    }

    // 제품 좋아요 목록
    public List<Map<String, Object>> productLikeList(CommandMap commandMap) throws Exception{
        return (List<Map<String,Object>>) selectList("my.productLikeList",commandMap.getMap());
    }

    // 게시글 목록
    public List<Map<String,Object>> boardList(CommandMap commandMap) throws Exception{
        return (List<Map<String,Object>>) selectList("my.boardList",commandMap.getMap());
    }

    // 댓글 목록
    public List<Map<String,Object>> replyList(CommandMap commandMap) throws Exception{
        return (List<Map<String,Object>>) selectList("my.replyList",commandMap.getMap());
    }

    // 리뷰 목록
    public List<Map<String,Object>> reviewList(CommandMap commandMap) throws Exception{
        return (List<Map<String,Object>>) selectList("my.reviewList",commandMap.getMap());
    }
}
