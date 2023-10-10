<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="shortcut icon" type="image/x-icon" href="../../img/favicon.png"/>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
</head>
<body>
<div id="wrapper">
    <form action="/login/login.do" method="post">
        <main id="member">

            <div class="login">
                <nav>
                    <h1>로그인</h1>
                    <p>
                        HOME >
                        <strong>로그인</strong>
                    </p>
                </nav>
                <form action="#">
                    <table border="0">
                        <tr>
                            <td>아이디
                            <td>
                            <td>
                                <input type="text" name="MEMBER_ID" placeholder="아이디 입력">
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호
                            <td>
                            <td>
                                <input type="password" name="MEMBER_PASSWD" placeholder="비밀번호 입력">
                            </td>
                        </tr>
                    </table>
                    <button type="submit" class="btn btn-secondary" name="memberLogin">로그인</button>
                    <span>
            <%--<label>
              <input type="checkbox" name="auto">자동 로그인
            </label>
            <a href="#">아이디찾기</a>
            <a href="#">비밀번호찾기</a>--%>
            <a href="/views/login/join_terms.jsp">회원가입</a>
          </span>
                </form>
            </div>

        </main>
    </form>
    <div class="container fixed-bottom">
    </div>
</div>
</body>
</html>
