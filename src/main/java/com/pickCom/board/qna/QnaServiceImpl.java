package com.pickCom.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("qnaService")
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public List<Map<String, Object>> selectQnaList(Map<String, Object> map) throws Exception {
		return qnaDAO.selectQnaList(map);
	}

	@Override
	public void insertQna(Map<String, Object> map, HttpServletRequest request) throws Exception {
		qnaDAO.insertQna(map);
	}

	@Override
	public Map<String, Object> selectQnaDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = qnaDAO.selectQnaDetail(map);
		tempMap.put("RNUM", map.get("RNUM"));
		resultMap.put("map", tempMap);		
		return resultMap;
	}

	@Override
	public void updateQna(Map<String, Object> map, HttpServletRequest request) throws Exception{
		qnaDAO.updateQna(map);
			}

	@Override
	public void deleteQna(Map<String, Object> map) throws Exception {
		qnaDAO.deleteQna(map);
	}
	
	@Override
	public Map<String, Object> selectQnaPassword(Map<String, Object> map) throws Exception {
		return qnaDAO.selectQnaPassword(map);
	}
	
}
