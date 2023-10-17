package com.pickCom.board.board;

import com.pickCom.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO {
    // 리스트 출력
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("board.selectBoardList", map);
    }

    // 글 검색
    public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
        return (Map<String, Object>) selectOne("board.selectBoardDetail", map);
    }

    // 글 추가
    public void insertBoard(Map<String, Object> map) throws Exception{
        insert("board.insertBoard", map);
    }

    // 글 수정
    public void updateBoard(Map<String, Object> map) throws Exception{
        update("board.updateBoard", map);
    }

    // 글 삭제
    public void deleteBoard(Map<String, Object> map) throws Exception{
        delete("board.deleteBoard", map);
    }

    // 댓글 추가
    public void insertComment(Map<String, Object> map) throws Exception{
        insert("board.insetComment", map);
    }

    // 댓글 수정
    public void updateComment(Map<String, Object> map) throws Exception{
        update("board.updateComment", map);
    }


    // 댓글 삭제
    public void deleteComment(Map<String, Object> map) throws Exception{
        delete("board.deleteComment", map);
    }
}
