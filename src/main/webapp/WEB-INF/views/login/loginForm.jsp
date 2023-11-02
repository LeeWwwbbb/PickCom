<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
            crossorigin="anonymous"></script>
    <style>
        a {
            text-decoration-line: none;
        }
    </style>
</head>
<body>
<main>
    <div class="position-absolute top-50 start-50 translate-middle border border-black border-2 p-3">
        <p class="h1">로그인</p>
        <form action="/loginAction.do" method="post" id="frm" onsubmit="return validateForm(this);">
            <table class="table table-borderless">
                <tr>
                    <td>
                        <input type="text" class="form-label p-1 w-100" name="MEMBER_ID" id="MEMBER_ID"
                               placeholder="아이디 입력">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" class="form-label p-1 w-100" name="MEMBER_PASSWD" id="MEMBER_PASSWD"
                               placeholder="비밀번호 입력">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-success w-100" name="memberLogin" id="login">로그인</button>
                    </td>
                </tr>
            </table>
            <span>
                <%--<label>
                  <input type="checkbox" name="auto">자동 로그인
                </label>--%>
            </span>
        </form>
        <div>
            <a href="/findId" class="link-dark m-1">아이디찾기</a>
            <a href="/findPw" class="link-dark m-1">비밀번호찾기</a>
            <a href="/joinTerms" class="link-success m-1">회원가입</a>
        </div>
    </div>
</main>
<script type="text/javascript">
    if ('${message}' != "") {
        alert('${message}');
    }

    function validateForm(form) {
        if (!form.MEMBER_ID.value) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (!form.MEMBER_PASSWD.value) {
            alert("패스워드를 입력하세요.");
            return false;
        }
    }
</script>
</body>
</html>
