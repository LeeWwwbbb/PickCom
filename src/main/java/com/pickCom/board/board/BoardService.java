package com.pickCom.board.board;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {
    // 리스트 출력
    List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

    // 글 상세
    Map<String, Object> openBoardDetail(Map<String, Object> map) throws Exception;

    // 글 검색
    Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;

    // 글 추가
    void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 글 수정
    void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 글 삭제
    void deleteBoard(Map<String, Object> map) throws Exception;

    // 좋아요 추가

    // 좋아요 삭제

    // 댓글 추가
    void insertComment(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 댓글 수정
    void updateComment(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 댓글 삭제
    void deleteComment(Map<String, Object> map) throws Exception;
}
