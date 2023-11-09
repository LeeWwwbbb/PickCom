package com.pickCom.board.board;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {
    // 베스트 게시물
    public List<Map<String, Object>> bestBoardList() throws Exception;

    // 리스트 출력
    List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

    // 리스트 테스트
    public List<Map<String, Object>> boardList(Map<String, Object> map) throws Exception;

    // 글 상세
    Map<String, Object> openBoardDetail(Map<String, Object> map) throws Exception;

    // 조회수 증가
    void incrementViewCount(Map<String, Object> map);

    // 글 검색
    List<Map<String, Object>> selectBoardDetail(Map<String, Object> map) throws Exception;

    // 글 추가
    void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 글 수정
    void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 글 삭제
    void deleteBoard(Map<String, Object> map) throws Exception;

    // 좋아요 확인
    boolean likeCheck(Map<String, Object> map) throws Exception;

    // 좋아요 추가
    void insertLike(Map<String, Object> map) throws Exception;

    // 좋아요 삭제
    void deleteLike(Map<String, Object> map) throws Exception;

    // 댓글 리스트
    public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception;

    // 댓글 추가
    void insertComment(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 댓글 수정
    void updateComment(Map<String, Object> map, HttpServletRequest request) throws Exception;

    // 댓글 삭제
    void deleteComment(Map<String, Object> map) throws Exception;

    // 이미지 업로드
    void insertImage(Map<String, Object> map) throws Exception;

    // 이미지 수정
    void updateImage(Map<String, Object> map) throws Exception;

    // 이미지 삭제
    void deleteImage(Map<String, Object> map) throws Exception;
}
