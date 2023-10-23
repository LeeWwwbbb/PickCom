package com.pickCom.member;

import lombok.Data;

public class MemberDTO {
    private int member_num;
    private String member_id;
    private String member_name;
    private String member_nickName;
    private String member_passwd;
    private String member_email;
    private String member_rank;
    private String member_regDate;
    private String member_lastDate;
    private boolean member_delete;
    private int member_visitCount;
}
