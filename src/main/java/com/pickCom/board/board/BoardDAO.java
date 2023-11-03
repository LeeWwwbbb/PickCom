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

    // 리스트 테스트
    public List<Map<String, Object>> boardList(Map<String, Object> map) throws Exception{
        return selectList("board.boardList", map);
    }

    // 글 상세
    public Map<String, Object> openBoardDetail(Map<String, Object> map) throws Exception{
        return (Map<String, Object>) selectOne("board.openBoardDetail", map);
    }

    // 조회수 증가
    public void incrementViewCount(Map<String, Object> map) {
        update("board.updateVisitCount", map);
    }

    // 글 검색
    public List<Map<String, Object>> selectBoardDetail(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectPagingList("admin.selectBoardDetail", map);
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

    // 좋아요 확인
    public int likeCheck(Map<String, Object> map) throws Exception{
        return (int) selectOne("board.likeCheck", map);
    }

    // 좋아요 추가
    public void insertLike(Map<String, Object> map) throws Exception{
        insert("board.insertLike", map);
    }

    // 좋아요 삭제
    public void deleteLike(Map<String, Object> map) throws Exception{
        insert("board.deleteLike", map);
    }

    // 댓글 리스트
    public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception{
        return selectList("board.selectCommentList", map);
    }

    // 댓글 추가
    public void insertComment(Map<String, Object> map) throws Exception{
        insert("board.insertComment", map);
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
