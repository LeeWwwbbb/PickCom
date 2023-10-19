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
</head>
<body>
<div id="wrapper">
    <main id="member">

        <div class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block">
            <nav>
                <h1>로그인</h1>
            </nav>
            <form action="/loginAction.do" method="post" id="frm" onsubmit="return validateForm(this);">
                <table border="0">
                    <tr>
                        <td>아이디
                        <td>
                        <td>
                            <input type="text" name="MEMBER_ID" id="MEMBER_ID" placeholder="아이디 입력">
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호
                        <td>
                        <td>
                            <input type="password" name="MEMBER_PASSWD" id="MEMBER_PASSWD" placeholder="비밀번호 입력">
                        </td>
                    </tr>
                </table>
                <button type="submit" class="btn btn-secondary" name="memberLogin" id="login">로그인</button>
                <span>
            <%--<label>
              <input type="checkbox" name="auto">자동 로그인
            </label>--%>
            <a href="/findId">아이디찾기</a>
            <a href="/findPw">비밀번호찾기</a>
            <a href="/joinTerms">회원가입</a>
          </span>
            </form>
        </div>

    </main>
    <div class="container fixed-bottom">
    </div>
</div>
<script type="text/javascript">
    if('${message}' != "") {
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
