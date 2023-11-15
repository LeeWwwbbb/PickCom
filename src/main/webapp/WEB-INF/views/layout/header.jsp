<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        @media (max-width: 1160px) {
            .logo {
                display: none;
            }

            .search {
                display: none;
            }

            .userName {
                display: none;
            }
        }
    </style>
</head>
<body>
<header class="p-1 text-bg-dark vertical-center">
    <div class="row">
        <div class="col-3 text-center d-flex align-items-center justify-content-center logo" style="height: 100%">
            <a href="/">
                <img src="../../../img/header_logo.png" style="max-width: 80%; max-height: 80%" alt="로고">
            </a>
        </div>
        <!-- 검색 폼 -->
        <div class="search col-6">
            <form action="/board/search" method="post" onsubmit="return validateForm()">
                <div class="row p-2">
                    <div class="col-10 d-flex align-items-center">
                        <input type="text" class="form-control w-100" name="keyword" id="keyword"
                               value="<c:if test='${not empty keyword}'>${keyword}</c:if>"/>
                    </div>
                    <div class="col-2 d-flex align-items-center">
                        <button type="submit" class="btn btn-primary w-100">검색</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="userName col-1 text-end p-2 d-flex align-items-center">
            <c:if test="${ sessionScope.num != null }">
                <span class="text-white" style="font-size: 16px;">${ sessionScope.name } 님</span>
            </c:if>
        </div>

        <div class="col p-2 d-flex align-items-center">
            <c:if test="${ sessionScope.num != null }">
                <c:if test="${ sessionScope.rank > 0 }">
                    <button type="button" class="btn btn-outline-light me-2"
                            onclick="location.href='/userList'">Admin
                    </button>
                </c:if>
                <c:if test="${ sessionScope.rank == 0 }">
                    <button type="button" class="btn btn-outline-light me-2"
                            onclick="location.href='/my/memberModify.do'">My Page
                    </button>
                </c:if>
                <button type="button" class="btn btn-outline-light me-2"
                        onclick="location.href='/logout.do' ">Logout
                </button>
            </c:if>
            <c:if test="${ sessionScope.num == null }">
                <button type="button" class="btn btn-outline-light me-2"
                        onclick="location.href='/login'">Login
                </button>
                <button type="button" class="btn btn-warning"
                        onclick="location.href='/joinTerms'">Sign-up
                </button>
            </c:if>
        </div>
    </div>
</header>
</body>
<script>
    function validateForm() {
        var keywordInput = document.getElementById("keyword");
        if (keywordInput.value.trim() === "") {
            // keyword가 비어 있으면 알림 메시지 출력 또는 원하는 동작 수행
            alert("검색어를 입력하세요.");
            return false; // 폼 제출을 막음
        }
        return true; // 폼 제출을 허용
    }
</script>
</html>