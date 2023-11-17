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
<main>
    <div class="position-absolute top-50 start-50 translate-middle h-50 d-inline-block" style="width: 1000px;">
        <form action="/my/pwCheck.do" method="post" name="pwCheckForm"
              onsubmit="return validateForm(this);">
            <h2>비밀번호 확인</h2>
            <input type="hidden" name="MEMBER_ID" value="${ member_id }">
            <div class="row mb-4">
                <div class="col-2">
                    <label for="pwd1" class="form-label">비밀번호</label>
                </div>
                <div class="col">
                    <input class="form-control"
                           name="MEMBER_PASSWD" id="pwd1" type="password" required>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary" style="width: 300px; float: right;">비밀번호 확인
                </button>
            </div>
        </form>
    </div>
</main>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    if ('${message}' != "") {
        alert('${message}');
    }

    function validateForm(form) {
        if (form.pwd1.value == "") {
            alert("패스워드를 입력하세요.");
            return false;
        }
    }
</script>
</body>
</html>