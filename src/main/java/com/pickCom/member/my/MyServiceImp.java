package com.pickCom.member.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pickCom.common.CommandMap;

import java.util.List;
import java.util.Map;

@Service("myService")
public class MyServiceImp implements MyService {
    @Autowired
    private MyDAO myDAO;

    // 구매 목록
    public List<Map<String,Object>> orderList(Map<String,Object> map) throws Exception{
        return myDAO.orderList(map);
    }

    // 닉네임 변경
    public void nickNameChange(Map<String, Object> map) throws Exception{
        myDAO.nickNameChange(map);
    }

    // 비밀번호 확인
    public String passwdCheck(Map<String, Object> map, String id) throws Exception{
        return myDAO.passwdCheck(map, id);
    }

    // 비밀번호 변경
    public void passwdChange(Map<String, Object> map) throws Exception{
        myDAO.passwdChange(map);
    }

    // 회원 탈퇴
    public void memberDelete(Map<String, Object> map) throws Exception{
        myDAO.memberDelete(map);
    }

    // 게시글 좋아요 목록
    public List<Map<String, Object>> boardLikeList(CommandMap commandMap) throws Exception{
        return myDAO.boardLikeList(commandMap);
    }

    // 제품 좋아요 목록
    public List<Map<String, Object>> productLikeList(CommandMap commandMap) throws Exception{
        return myDAO.productLikeList(commandMap);
    }

    // 게시글 목록
    public List<Map<String,Object>> boardList(CommandMap commandMap) throws Exception{
        return myDAO.boardList(commandMap);
    }

    // 댓글 목록
    public List<Map<String,Object>> replyList(CommandMap commandMap) throws Exception{
        return myDAO.replyList(commandMap);
    }

    // 리뷰 목록
    public List<Map<String,Object>> reviewList(CommandMap commandMap) throws Exception{
        return myDAO.reviewList(commandMap);
    }
}
