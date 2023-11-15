package com.pickCom.board.board;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("boardServiceImp")
public class BoardServiceImp implements BoardService{
    @Resource(name = "boardDAO")
    private BoardDAO boardDAO;

    // 베스트 게시물
    @Override
    public List<Map<String, Object>> bestBoardList() throws Exception{
        return boardDAO.bestBoardList();
    }

    // 메인 게시물
    @Override
    public List<Map<String, Object>> mainBoardList() throws Exception{
        return boardDAO.mainBoardList();
    }

    // 리스트 출력
    @Override
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
        return boardDAO.selectBoardList(map);
    }

    // 리스트 테스트
    public List<Map<String, Object>> boardList(Map<String, Object> map) throws Exception{
        return boardDAO.boardList(map);
    }

    // 글 상세
    @Override
    public Map<String, Object> openBoardDetail(Map<String, Object> map) throws Exception{
        Map<String, Object> resultMap = new HashMap<String,Object>();
        Map<String, Object> tempMap = boardDAO.openBoardDetail(map);
        resultMap.put("map", tempMap);
        return resultMap;
    }

    // 게시글 조회수 증가
    public void incrementViewCount(Map<String, Object> map) {
        boardDAO.incrementViewCount(map);
    }

    // 글 검색
    @Override
    public List<Map<String, Object>> selectBoardDetail(Map<String, Object> map) throws Exception{
        return boardDAO.selectBoardDetail(map);
    }

    // 글 추가
    @Override
    public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception{
        boardDAO.insertBoard(map);
    }

    // 글 수정
    @Override
    public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception{
        boardDAO.updateBoard(map);
    }

    // 글 삭제
    @Override
    public void deleteBoard(Map<String, Object> map) throws Exception{
        boardDAO.deleteBoard(map);
    }

    // 좋아요 확인
    @Override
    public boolean likeCheck(Map<String, Object> map) throws Exception{
        int like = boardDAO.likeCheck(map);
        return like > 0;
    }

    // 좋아요 추가
    @Override
    public void insertLike(Map<String, Object> map) throws Exception{
        boardDAO.insertLike(map);
    }

    // 좋아요 삭제
    @Override
    public void deleteLike(Map<String, Object> map) throws Exception{
        boardDAO.deleteLike(map);
    }

    // 댓글 리스트
    @Override
    public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception{
        return boardDAO.selectCommentList(map);
    }

    // 댓글 추가
    @Override
    public void insertComment(Map<String, Object> map, HttpServletRequest request) throws Exception{
        boardDAO.insertComment(map);
    }

    // 댓글 수정
    @Override
    public void updateComment(Map<String, Object> map, HttpServletRequest request) throws Exception{
        boardDAO.updateComment(map);
    }


    // 댓글 삭제
    @Override
    public void deleteComment(Map<String, Object> map) throws Exception{
        boardDAO.deleteComment(map);
    }

    // 이미지 업로드
    @Override
    public void insertImage(Map<String, Object> map) throws Exception{
        boardDAO.insertImage(map);
    }

    // 이미지 수정
    @Override
    public void updateImage(Map<String, Object> map) throws Exception{
        boardDAO.updateImage(map);
    }

    // 이미지 삭제
    @Override
    public void deleteImage(Map<String, Object> map) throws Exception{
        boardDAO.deleteImage(map);
    }
}
