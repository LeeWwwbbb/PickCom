package com.pickCom.member.my;

import org.springframework.stereotype.Repository;
import com.pickCom.common.dao.AbstractDAO;
import com.pickCom.common.common.CommandMap;

import java.util.List;
import java.util.Map;

@Repository("myDAO")
public class MyDAO extends AbstractDAO {
    // 회원 정보 가져오기
    public Map<String, Object> memberModify(Map<String, Object> map) throws Exception{
        return (Map<String, Object>)selectOne("my.memberModify", map);
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

    // 좋아요 목록
    public List<Map<String, Object>> likeList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("my.likeList", map);
    }

    // 게시글 목록
    public List<Map<String, Object>> boardList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("my.boardList", map);
    }

    // 댓글 목록
    public List<Map<String,Object>> commentList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("my.commentList", map);
    }
}
