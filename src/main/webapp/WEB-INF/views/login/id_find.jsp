<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게임리뷰게시판</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
</head>
<body>
<main>
    <div class="position-absolute top-50 start-50 translate-middle w-75 p-3 h-75 d-inline-block">
        <h2>아이디 찾기</h2>
        <form action="/findIdAction" method="post" name="idFindForm"
              onsubmit="return validateForm(this);">
            <div class="row">
                <div class="col-2">
                    <label for="email" class="form-label">이메일</label>
                </div>
                <div class="col">
                    <input type="email" id="email" name="MEMBER_EMAIL" class="form-control"/>
                </div>
                <%--<div class="col-2">
                    <select class="form-select" id="address" name="address"
                        aria-label="Default select example">
                        <option selected value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="">직접 입력하기</option>
                    </select>
                </div>--%>
                <div class="col-2">
                    <button type="button" class="btn btn-secondary" id="sendBtn" onclick="sendEmailVerificationCode()">
                        인증번호
                        전송
                    </button>
                </div>
            </div>
            <div class="row">
                <div class="col-2">
                    <label for="email" class="form-label">인증번호</label>
                </div>
                <div class="col-8">
                    <input type="text" class="form-control" id="authCode"/>
                </div>
                <div class="col-2">
                    <button type="button" class="btn btn-secondary" id="codeBtn" onclick="verifyEmailCode()">인증번호
                        확인
                    </button>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">아이디 찾기</button>
            </div>
        </form>
    </div>
</main>
<script>
    var emailChk = false;
    var numberChk = false;
    function validateForm(form) {
        if (form.email.value == "") {
            alert("이메일을 입력하세요.");
            return false;
        }
        if (form.authCode.value == "") {
            alert("인증번호를 입력하세요.");
            return false;
        }
        if (!emailChk) {
            alert("이메일 인증을 확인하세요");
            return false;
        }
        if (!numberChk) {
            alert("인증코드를 확인하세요");
            return false;
        }
        return true;
    }

    function sendEmailVerificationCode() {
        var userEmail = $("#email").val().trim();

        // 이메일이 입력되지 않은 경우
        if (userEmail === "" || userEmail === null) {
            alert("이메일 주소를 입력해야 합니다.");
            return;
        }

        $.ajax({
            url: "/joinEmail.do",
            method: "post",
            data: {
                email: userEmail
            },
            success: function (result) {
                var responseJson = JSON.parse(result);
                if (responseJson) {
                    alert("이메일 전송 완료");
                    emailChk = true;
                } else {
                    alert("이메일 전송 실패");
                }
            },
            error: function (xhr) {
                alert("에러코드 = " + xhr.status);
            }
        });
    }

    function verifyEmailCode() {
        var enteredCode = $("#authCode").val().trim();

        // 입력된 인증번호가 비어있는지 확인
        if (enteredCode === "" || enteredCode === null) {
            alert("인증번호를 입력해주세요.");
            return;
        }

        $.ajax({
            url: "/joinCode.do",
            method: "post",
            data: {
                code: enteredCode
            },
            success: function (result) {
                var responseJson = JSON.parse(result);
                if (!responseJson) {
                    alert("인증번호가 일치하지 않습니다");
                } else {
                    alert("인증번호가 일치합니다");
                    numberChk = true;
                }
            },
            error: function (xhr) {
                alert("에러코드 = " + xhr.status);
            }
        });
    }
</script>
</body>
</html>