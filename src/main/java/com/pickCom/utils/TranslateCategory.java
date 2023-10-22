package com.pickCom.utils;

public class TranslateCategory {
    public static String translateCategory(String category) {
        String koreanCategory;

        switch (category) {
            case "free":
                koreanCategory = "자유";
                break;
            case "notice":
                koreanCategory = "공지";
                break;
            case "review":
                koreanCategory = "리뷰";
                break;
            case "image":
                koreanCategory = "이미지";
                break;
            case "question":
                koreanCategory = "질문";
                break;
            default:
                koreanCategory = "알 수 없음";
        }

        return koreanCategory;
    }
}
