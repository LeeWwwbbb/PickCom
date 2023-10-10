<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="shortcut icon" type="image/x-icon" href="../../img/favicon.png" />
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
</head>
<body>
<div id="wrapper">
    <main id="member">
        <div class="register">
            <nav>
                <h1>일반 회원가입</h1>
            </nav>
            <form action="/join/join.do" method="post" name="joinForm"
                  onsubmit="return validateForm(this);">

                <section>
                    <table>
                        <caption>필수 정보입력</caption>
                        <tr>
                            <th>
                                <span class="essential">*</span>
                                아이디
                            </th>
                            <td>
                                <input type="text"
                                       pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,14}$" id="userId" name="MEMBER_ID" placeholder="아이디를 입력" required>
                                <span class="msgId">&nbsp;&nbsp;영문, 숫자로 4~12자까지 설정해 주세요.</span>
                            </td>
                            <td>
                                <button type="button" class="btn btn-secondary" id="idCheckBtn">아이디중복확인</button>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="essential">*</span>
                                이름
                            </th>
                            <td>
                                <input type="text" pattern="^[가-힣A-Za-z0-9]{2,8}$" id="userName" name="MEMBER_NAME" placeholder="이름을 입력" required>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="essential">*</span>
                                비밀번호
                            </th>
                            <td>
                                <input type="password" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,16}$"
                                       id="pwd1" name="MEMBER_PASSWD" placeholder="비밀번호를 입력" required>
                                <span class="msgPass">&nbsp;&nbsp;영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span class="essential">*</span>
                                비밀번호확인
                            </th>
                            <td>
                                <input type="password" id="pwd2" name="km_pass" placeholder="비밀번호를 확인" required>
                                <span class="msgPass">&nbsp;&nbsp;비밀번호 재입력</span>
                            </td>
                        </tr>
                    </table>
                </section>
                <section>
                        <table>
                            <caption>기본 정보입력</caption>
                            <tr>
                                <th>
                                    <span class="essential">*</span>
                                    닉네임
                                </th>
                                <td>
                                    <input type="text" id="nickName" name="MEMBER_NICKNAME" placeholder="닉네임을 입력" required>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="essential">*</span>
                                    이메일
                                </th>
                                <td>
                                    <input type="email" id="email" name="MEMBER_EMAIL" placeholder="이메일 입력" required>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-secondary" id="sendBtn" onclick="sendEmailVerificationCode()">인증번호 전송</button>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="essential">*</span>
                                    인증번호
                                </th>
                                <td>
                                    <input type="text" id="authCode" placeholder="인증번호 입력" required>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-secondary" id="codeBtn" onclick="verifyEmailCode()">인증번호 확인</button>
                                </td>
                            </tr>
                        </table>
                </section>
                <div>
                    <input type="submit" class="join" value="회원가입"/>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
<script type="text/javaScript">
    var idChk = false;
    var emailChk = false;
    var numberChk = false;
    function validateForm(form) {
        if (!form.userId.value) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (form.userName.value === "") {
            alert("이름을 입력하세요.");
            return false;
        }
        if (form.pwd1.value === "") {
            alert("비밀번호를 입력하세요.");
            return false;
        }
        if (form.pwd2.value === "") {
            alert("비밀번호 확인을 입력하세요.");
            return false;
        }
        if (form.pwd1.value !== form.pwd2.value) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }
        if (form.email.value === "") {
            alert("이메일을 입력하세요.");
            return false;
        }
        if (!idChk) {
            alert("아이디 중복확인을 체크해주세요");
        }
        if (!emailChk) {
            alert("이메일 인증을 확인하세요");
        }
        if (!numberChk) {
            alert("인증코드를 확인하세요");
        }
        return true;
    }

    $(document).ready(function() {
        // 비밀번호 확인 필드의 값이 변경될 때마다 비밀번호와 일치하는지 확인하고 오류 메시지를 업데이트
        $("#pwd2").on("input", function() {
            var password = $("#pwd1").val();
            var confirmPassword = $(this).val();
            var errorMessage = "비밀번호가 일치하지 않습니다.";

            if (password !== confirmPassword) {
                $("#confirmPasswordError").text(errorMessage);
            } else {
                $("#confirmPasswordError").text("");
            }
        });
    });

    $(document).ready(function() {
        $("#idCheckBtn").click(function() {
            var id = $("#userId").val(); // 아이디 입력 필드의 값을 가져옴

            // 입력된 아이디가 비어있는지 확인
            if (id.trim() === "") {
                alert("아이디를 입력해주세요.");
                return;
            }

            // AJAX를 사용하여 아이디 중복 확인 요청
            $.ajax({
                url: "/join/idCheck.do",
                method: "post",
                data: {
                    userId: id
                },
                success: function(result) {
                    var responseJson = JSON.parse(result);
                    if (responseJson.isDuplicate) {
                        alert("중복된 아이디입니다.");
                    } else {
                        alert("사용 가능한 아이디입니다.");
                        idChk=true;
                    }
                },
                error: function(xhr) {
                    alert("에러코드 = " + xhr.status);
                }
            });
        });
    });

    function sendEmailVerificationCode() {
        var userEmail = $("#email").val().trim();

        // 이메일이 입력되지 않은 경우
        if (userEmail === "" || userEmail === null) {
            alert("이메일 주소를 입력해야 합니다.");
            return;
        }

        $.ajax({
            url: "/join/sendVerificationCode.do",
            method: "post",
            data: {
                email: userEmail
            },
            success: function(result) {
                var responseJson = JSON.parse(result);
                if (responseJson.isDuplicate) {
                    alert("이메일 전송 완료");
                    emailChk=true;
                } else {
                    alert("이메일 전송 실패");
                }
            },
            error: function(xhr) {
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
            url: "/join/verifyCode.do",
            method: "post",
            data: {
                code: enteredCode
            },
            success: function(result) {
                var responseJson = JSON.parse(result);
                if (responseJson.isDuplicate) {
                    alert("인증번호가 일치하지 않습니다");
                } else {
                    alert("인증번호가 일치합니다");
                    numberChk=true;
                }
            },
            error: function(xhr) {
                alert("에러코드 = " + xhr.status);
            }
        });
    }
</script>
</html>