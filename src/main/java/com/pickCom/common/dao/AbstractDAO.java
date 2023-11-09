package com.pickCom.common.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


public class AbstractDAO {
    protected Log log = LogFactory.getLog(AbstractDAO.class);

    @Autowired
    private SqlSessionTemplate sqlSession;

    protected void printQueryId(String queryId) {

        if(log.isDebugEnabled()) {
            log.debug("\t QueryId \t: " + queryId);
        }

    }

    @SuppressWarnings("unchecked")
    public Object selectPagingList(String queryId, Object params) {
        printQueryId(queryId);

        return sqlSession.selectList(queryId, params);
    }

    // 리스트
    public List<Map<String,Object>> selectList(String queryId) {
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }


    // 리스트
    public List<Map<String,Object>> selectList(String queryId, Object params) {
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
    //수정
    public Object update(String queryId, Object params) {
        printQueryId(queryId);
        return sqlSession.update(queryId,params);
    }
    //수정2
    public Object update(String queryId) {
        printQueryId(queryId);
        return sqlSession.update(queryId);
    }
    //삭제
    public Object delete(String queryId, Object params) {
        printQueryId(queryId);
        return sqlSession.delete(queryId,params);
    }

    /*
     * public Object selectOne(String queryId, Object params) {
     * printQueryId(queryId); return sqlSession.selectOne(queryId,params); }
     */


    // 특정 레코드 한줄 출력
    public Object selectOne(String queryId) {
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }

    // 특정 레코드 한줄 출력
    public Object selectOne(String queryId, Object params) {
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }

    // 레코드 추가
    public Object insert(String queryId, Object params) {
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }

}
