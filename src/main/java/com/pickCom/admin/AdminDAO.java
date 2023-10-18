package com.pickCom.admin;

<<<<<<< Updated upstream
public class AdminDAO {
    /*private DBConnPool db;

    // 검색 조건에 맞는 유저리스트 개수를 반환
    public int selectCount(Map<String, Object> map) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;

        String query = "SELECT COUNT(*) FROM member";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            db = new DBConnPool();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("사용자 카운트 중 예외 발생");
            e.printStackTrace();
        } finally {
            db.close();
        }

        return totalCount;
    }

    // 검색 조건에 맞는 유저리스트 목록을 반환
    public List<MemberDTO> selectListUser(Map<String, Object> map) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberDTO> member = new Vector<MemberDTO>();

        String query = "SELECT * FROM ( " +
                "    SELECT Tb.*, @rownum := @rownum + 1 AS rNum FROM ( " +
                "        SELECT * FROM member WHERE member_rank=? ";
        if (map.get("searchWord") != null) {
            query += " AND " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
        }
        query += "    ) Tb, (SELECT @rownum := 0) R " +
                ") AS result " +
                "WHERE rNum BETWEEN ? AND ?";


        try {
            db = new DBConnPool();
            con = db.getConnection();
            System.out.println("query ==> " + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "일반");
            pstmt.setString(2, map.get("start").toString());
            pstmt.setString(3, map.get("end").toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Timestamp regDate = rs.getTimestamp("member_regDate");
                String regDateString = sdf.format(regDate);
                if (rs.getTimestamp("member_statDate") != null) {
                    Timestamp statDate = rs.getTimestamp("member_statDate");
                    String statDateString = sdf.format(statDate);
                    dto.setMember_statDate(statDateString);
                }

                dto.setMember_no(rs.getInt("member_no"));
                dto.setMember_id(rs.getString("member_id"));
                dto.setMember_name(rs.getString("member_name"));
                dto.setMember_password(rs.getString("member_password"));
                dto.setMember_email(rs.getString("member_email"));
                dto.setMember_rank(rs.getString("member_rank"));
                dto.setMember_regDate(regDateString);
                dto.setMember_stat(rs.getBoolean("member_stat"));
                dto.setMember_reason(rs.getString("member_reason"));

                member.add(dto);
            }
        } catch (Exception e) {
            System.out.println("사용자 조회 중 예외 발생");
            e.printStackTrace();
        } finally {
            db.close();
        }
        return member;
    }

    // 사용자 정보 수정
    public int updateUser(int num, String name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "UPDATE member SET member_name=? "
                + "WHERE member_no=?";
        try {
            db = new DBConnPool();
            con = db.getConnection();
            System.out.println("query ==> " + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, num);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception[updateUser] : " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }

    // 사용자 이용 정지
    public int suspendUser(int idx, int day, String reason) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "UPDATE member SET member_stat=?,member_reason=?,"
                + "member_statDate = DATE_ADD(NOW(), INTERVAL ? DAY) "
                + "WHERE member_no=?";
        try {
            db = new DBConnPool();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, reason);
            pstmt.setInt(3, day);
            pstmt.setInt(4, idx);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception[suspendUser] : " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }

    // 이용 정지 해제?

    // 사용자 정보 삭제
    public int deleteUser(int idx) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String query = "DELETE FROM member WHERE member_no=?";
        try {
            db = new DBConnPool();
            con = db.getConnection();
            System.out.println("query ==> " + query);
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idx);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception[deleteUser] : " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }*/
}
=======
import com.pickCom.common.dao.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("adminDAO")
public class AdminDAO extends AbstractDAO {
    // 유저 강제 탈퇴
    public void MemberDelete(Map<String, Object> map) throws Exception{
        delete("admin.deleteUser", map);
    }
    // 유저 정보 업데이트
    public void MemberUpdate(Map<String, Object> map) throws Exception {
        update("admin.updateUser", map);
    }
}
>>>>>>> Stashed changes
