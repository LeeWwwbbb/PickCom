package com.pickCom.board.notice;

import java.util.List;
import java.util.Map;

public interface NoticeDAO {
    // 공지 리스트 출력
    public List<Map<String, Object>> selectNoticeList(Map<String, Object> map);

    // 공지 작성
    public void insertNotice(Map<String, Object> map) ;

    // 공지 내용 출력
    public Map<String, Object> selectNoticeDetail(Map<String, Object> map);

    // 공지 수정
    public void updateNotice(Map<String, Object> map);

    // 공지 삭제
    public void deleteNotice(Map<String, Object> map);
}
