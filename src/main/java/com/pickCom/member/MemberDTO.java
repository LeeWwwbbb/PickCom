package com.pickCom.member;

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

    public int getMember_num() {
        return member_num;
    }

    public void setMember_num(int member_num) {
        this.member_num = member_num;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_nickName() {
        return member_nickName;
    }

    public void setMember_nickName(String member_nickName) {
        this.member_nickName = member_nickName;
    }

    public String getMember_passwd() {
        return member_passwd;
    }

    public void setMember_passwd(String member_passwd) {
        this.member_passwd = member_passwd;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_rank() {
        return member_rank;
    }

    public void setMember_rank(String member_rank) {
        this.member_rank = member_rank;
    }

    public String getMember_regDate() {
        return member_regDate;
    }

    public void setMember_regDate(String member_regDate) {
        this.member_regDate = member_regDate;
    }

    public String getMember_lastDate() {
        return member_lastDate;
    }

    public void setMember_lastDate(String member_lastDate) {
        this.member_lastDate = member_lastDate;
    }

    public boolean isMember_delete() {
        return member_delete;
    }

    public void setMember_delete(boolean member_delete) {
        this.member_delete = member_delete;
    }

    public int getMember_visitCount() {
        return member_visitCount;
    }

    public void setMember_visitCount(int member_visitCount) {
        this.member_visitCount = member_visitCount;
    }
}
