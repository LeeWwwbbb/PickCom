package com.pickCom.board;

public class BoardDTO {
    private int board_num;
    private String member_id;
    private String title;
    private String board_content;
    private String writingDate;
    private String board_type;
    private String board_classification;
    private boolean board_stat;

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getWritingDate() {
        return writingDate;
    }

    public void setWritingDate(String writingDate) {
        this.writingDate = writingDate;
    }

    public String getBoard_type() {
        return board_type;
    }

    public void setBoard_type(String board_type) {
        this.board_type = board_type;
    }

    public String getBoard_classification() {
        return board_classification;
    }

    public void setBoard_classification(String board_classification) {
        this.board_classification = board_classification;
    }

    public boolean isBoard_stat() {
        return board_stat;
    }

    public void setBoard_stat(boolean board_stat) {
        this.board_stat = board_stat;
    }
}
