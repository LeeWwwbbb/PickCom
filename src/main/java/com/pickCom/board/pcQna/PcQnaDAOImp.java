package com.pickCom.board.pcQna;

public class PcQnaDAOImp implements PcQnaDAO{
    /*// 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {
        return 0;
    }

    // 검색 조건에 맞는 게시물 목록을 반환합니다.
    public List<BoardDTO> selectList(Map<String, Object> map) {
        List<BoardDTO> bbs = new ArrayList<BoardDTO>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select * from board";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardDTO dto = new BoardDTO();
//                dto.setNum(rs.getString("num"));
//                dto.setTitle(rs.getString("title"));
//                dto.setContent(rs.getString("content"));
//                dto.setId(rs.getString("id"));
//                dto.setPostdate(rs.getDate("postdate"));
//                dto.setVisitcount(rs.getString("visitcount"));
                bbs.add(dto);
            }
        }catch(Exception e) {
            System.out.println("Exception[selectList]: " + e.getMessage());
        }finally {
            closeConnection(con, ps, rs);
        }
        return bbs;
    }

    // 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
    public List<BoardDTO> selectListPage(Map<String, Object> map) {

        return null;
    }

    // 게시글 데이터를 받아 DB에 추가합니다.
    public int insertWrite(BoardDTO dto) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "insert into board(num,title,content,id,visitcount) ";
            query += "values (seq_board_num.NEXTVAL,?,?,?,0)";
            ps = con.prepareStatement(query);
//            ps.setString(1, dto.getTitle());
//            ps.setString(2, dto.getContent());
//            ps.setString(3, dto.getId());
            result = ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("Exception[insertWrite]: "+e.getMessage());
        }finally {
            closeConnection(con, ps);
        }
        return result;
    }


    // 지정한 게시물을 찾아 내용을 반환합니다.
    public BoardDTO selectView(String num) {
        BoardDTO dto = new BoardDTO();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "select b.*, M.name from member M INNER JOIN board B ";
            query += "on M.id = B.id where num=?";
            ps = con.prepareStatement(query);
            ps.setString(1, num);
            rs = ps.executeQuery();
            if (rs.next()) {
//                dto.setNum(rs.getString("num"));
//                dto.setTitle(rs.getString("title"));
//                dto.setContent(rs.getString("content"));
//                dto.setPostdate(rs.getDate("postdate"));
//                dto.setVisitcount(rs.getString("visitcount"));
//                dto.setId(rs.getString("id"));
//                dto.setName(rs.getString("name"));
            }
        }catch(Exception e) {
            System.out.println("Exception[selectView]: "+e.getMessage());
        }finally {
            closeConnection(con, ps, rs);
        }
        return dto;
    }

    // 지정한 게시물의 조회수를 1 증가시킵니다.
    public void updateVisitCount(String num) {

    }

    // 지정한 게시물을 수정합니다.
    public int updateEdit(BoardDTO dto) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "update board set title=?, content=? where num=?";
            ps = con.prepareStatement(query);
//            ps.setString(1, dto.getTitle());
//            ps.setString(2, dto.getContent());
//            ps.setString(3, dto.getNum());
            result = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Exception[updateEdit]: "+e.getMessage());
        }finally {
            closeConnection(con, ps);
        }
        return result;
    }

    // 지정한 게시물을 삭제합니다.
    public int deletePost(BoardDTO dto) {

        return 0;
    }*/
}
