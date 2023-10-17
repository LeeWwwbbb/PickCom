package com.pickCom.board.board;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("boardService")
public class BoardServiceImp implements BoardService{
    @Resource(name = "boardDAO")
    private BoardDAO boardDAO;

    // 리스트 출력
    @Override
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
        return boardDAO.selectBoardList(map);
    }

    // 글 검색
    @Override
    public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
        Map<String, Object> resultMap = new HashMap<String,Object>();
        Map<String, Object> tempMap = boardDAO.selectBoardDetail(map);
        resultMap.put("map", tempMap);
        return resultMap;
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
}
