<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
</head>
<body>
<div class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block" style="width: 1000px;">
    <form name="writeFrm" method="post" enctype="multipart/form-data" action="/board/updateBoard.do" onsubmit="return validateForm(this);">
        <c:if test="${not empty map}">
            <input type="hidden" name="board_num" value="${map.board_num}">
            <input type="hidden" name="image_originalName" value="${map.image_originalName}">
            <input type="hidden" name="image_saveName" value="${map.image_saveName}">
            <input type="hidden" name="image_size" value="${map.image_size}">
            <input type="hidden" name="member_num" value="${sessionScope.num}">

            <table class="table mb-4">
                <colgroup>
                    <col width="15%"/>
                    <col width="*"/>
                </colgroup>
                <tr>
                    <td>제목</td>
                    <td><input type="text" value="${map.board_title}" class="form-control" name="board_title"></td>
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
                    <td colspan="2"><textarea name="board_content" class="form-control" style="height: 300px;">${map.board_content}</textarea></td>
                </tr>
                <tr>
                    <td>첨부 파일</td>
                    <td>
                        <input class="form-control" type="file" accept="image/*" name="file" onchange="previewImage(this);"/>
                        <br/>
                        <img id="imagePreview" src="" style="max-width: 100%; max-height: 300px; display: none;"/>
                    </td>
                </tr>
            </table>

            <div class="row mb-4">
                <div class="col-8"></div>
                <div class="col">
                    <button type="reset" class="btn btn-warning w-100" style="text-align: center; font-size: 24px;">RESET</button>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100" style="text-align: center; font-size: 24px;">작성 완료</button>
                </div>
            </div>
        </c:if>
    </form>
</div>
<footer>
    <br><br><br>
</footer>

<script type="text/javascript">
    function validateForm(form) {
        if (form.board_title.value == "") {
            alert("제목을 입력하세요.");
            form.board_title.focus();
            return false;
        }
        if (form.board_content.value == "") {
            alert("내용을 입력하세요.");
            form.board_content.focus();
            return false;
        }
    }

    function previewImage(input) {
        var imagePreview = document.getElementById("imagePreview");
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                imagePreview.style.display = "block";
                imagePreview.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            imagePreview.style.display = "none";
            imagePreview.src = "";
        }
    }
</script>
</body>
</html>
