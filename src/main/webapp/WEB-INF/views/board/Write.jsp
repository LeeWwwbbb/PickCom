<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous">
</head>
<body>
<div
        class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block"
        style="width: 1000px;">
    <form name="writeFrm" method="post" enctype="multipart/form-data"
          action="/board/insertBoard.do" onsubmit="return validateForm(this);">
        <table class="table mb-4">
            <colgroup>
                <col width="15%"/>
                <col width="*"/>
            </colgroup>
            <tr>
                <td>제목<input type="hidden" name="member_num" value="${ sessionScope.num }"></td>
                <td><input type="text" class="form-control" name="board_title"></td>
            </tr>
            <tr>
                <td>분류</td>
                <td><select name="board_cate" class="form-select">
                    <c:choose>
                        <c:when test="${ sessionScope.rank > 0 }">
                            <c:forEach var="option" items="${['공지', '자유', '리뷰', '사진', '질문']}">
                                <c:set var="selected" value="${option eq category ? 'selected' : ''}"/>
                                <option name="board_cate" value="<c:out value='${
        option == "공지" ? "notice" :
        option == "자유" ? "free" :
        option == "리뷰" ? "review" :
        option == "사진" ? "image" :
        option == "질문" ? "question" :
        ""}'/>" ${selected}>${option}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="option" items="${['자유', '리뷰', '사진', '질문']}">
                                <c:set var="selected" value="${option eq category ? 'selected' : ''}"/>
                                <option name="board_cate" value="<c:out value='${
        option == "자유" ? "free" :
        option == "리뷰" ? "review" :
        option == "사진" ? "image" :
        option == "질문" ? "question" :
        ""}'/>" ${selected} >${option}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><textarea name="board_content" class="form-control"
                                          style="height: 300px;"></textarea></td>
            </tr>
            <tr>
                <td>첨부 파일</td>
                <td><input class="form-control" type="file" multiple="multiple" accept="image/*" name="file"/></td>
            </tr>
        </table>
        <div class="row mb-4">
            <div class="col-8"></div>
            <div class="col ">
                <button type="reset" class="btn btn-warning w-100" style="text-align: center; font-size: 24px;">
                    RESET
                </button>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary w-100" style="text-align: center; font-size: 24px;">작성
                    완료
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    function validateForm(form) { // 필수 항목 입력 확인
        if (form.title.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.content.value == "") {
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
    }
</script>
</html>