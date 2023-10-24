package com.pickCom.member.my;

import com.pickCom.utils.TranslateCategory;
import org.springframework.stereotype.Service;
import com.pickCom.common.common.CommandMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("myServiceImp")
public class MyServiceImp implements MyService {
    @Resource(name = "myDAO")
    private MyDAO myDAO;

    // 회원 정보 가져오기
    public Map<String, Object> memberModify(Map<String, Object> map) throws Exception{
        Map<String, Object> resultMap = new HashMap<String,Object>();
        Map<String, Object> tempMap = myDAO.memberModify(map);
        resultMap.put("map", tempMap);
        return resultMap;
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
    public List<Map<String, Object>> likeList(Map<String, Object> commandMap) throws Exception{
        List<Map<String, Object>> list = myDAO.likeList(commandMap);
        for (Map<String, Object> item : list) {
            String boardCate = (String) item.get("board_cate");
            String cate = TranslateCategory.translateCategory(boardCate);
            item.put("cate", cate);
        }
        return list;
    }

    // 게시글 목록
    @Override
    public List<Map<String,Object>> boardList(Map<String, Object> commandMap) throws Exception{
        List<Map<String, Object>> list = myDAO.boardList(commandMap);
        for (Map<String, Object> item : list) {
            String boardCate = (String) item.get("board_cate");
            String cate = TranslateCategory.translateCategory(boardCate);
            item.put("cate", cate);
        }
        return list;
    }

    // 댓글 목록
    @Override
    public List<Map<String,Object>> commentList(Map<String, Object> commandMap) throws Exception {
        List<Map<String, Object>> list = myDAO.commentList(commandMap);
        for (Map<String, Object> item : list) {
            String boardCate = (String) item.get("board_cate");
            String cate = TranslateCategory.translateCategory(boardCate);
            item.put("cate", cate);
        }
        return list;
    }
}
