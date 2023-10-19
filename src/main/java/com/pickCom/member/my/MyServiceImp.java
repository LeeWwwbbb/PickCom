package com.pickCom.member.my;

import org.springframework.stereotype.Service;
import com.pickCom.common.common.CommandMap;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("myService")
public class MyServiceImp implements MyService {
    @Resource(name = "myDAO")
    private MyDAO myDAO;

    // 회원 정보 가져오기
    public Map<String, Object> memberModify(int id) throws Exception{
        return myDAO.memberModify(id);
    }

    // 닉네임 변경
    @Override
    public void nickNameChange(Map<String, Object> map) throws Exception{
        myDAO.nickNameChange(map);
    }

    // 비밀번호 확인
    @Override
    public String passwdCheck(Map<String, Object> map, String id) throws Exception{
        return myDAO.passwdCheck(map, id);
    }

    // 비밀번호 변경
    @Override
    public void passwdChange(Map<String, Object> map) throws Exception{
        myDAO.passwdChange(map);
    }

    // 회원 탈퇴
    @Override
    public void memberDelete(Map<String, Object> map) throws Exception{
        myDAO.memberDelete(map);
    }

    // 게시글 좋아요 목록
    @Override
    public List<Map<String, Object>> selectMyLikeList(CommandMap commandMap) throws Exception{
        return myDAO.boardLikeList(commandMap);
    }

    // 게시글 목록
    @Override
    public List<Map<String,Object>> selectMyList(CommandMap commandMap) throws Exception{
        return myDAO.boardList(commandMap);
    }

    // 댓글 목록
    @Override
    public List<Map<String,Object>> selectMyCommentList(CommandMap commandMap) throws Exception {
        return myDAO.replyList(commandMap);
    }
}
