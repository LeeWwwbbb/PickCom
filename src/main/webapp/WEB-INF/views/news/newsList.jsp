<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
            crossorigin="anonymous"></script>
    <style>
        .paging a {
            color: black;
            text-decoration-line: none;
        }

        p {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        h4 {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<main>
    <%@ include file="../layout/category.jsp" %>
    <div class="container" style="max-width: 60%; margin: 0 auto;">
        <c:choose>
            <c:when test="${ empty newsList }">
            </c:when>
            <c:otherwise>
                <c:forEach items="${ newsList }" var="row" varStatus="loop">
                    <div class="row" style="height: 200px">
                        <div class="col-3" style="width: 275px;">
                            <a class="d-block" href="/news/${ row.news_num }">
                                <img src="${ row.news_image }" alt="첨부 이미지" style="width: 100%; height: 150px"/>
                            </a>
                        </div>
                        <div class="text-dark col-9" style="width: calc(100% - 275px);">
                            <a class="text-dark d-block text-decoration-none" href="/news/${ row.news_num }">
                                <h4>${ row.news_title }</h4>
                                <p>${ row.news_writer }</p>
                                <p>${ row.news_date }</p>
                                <input type="hidden"
                                       value="${ row.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}"/>
                            </a>
                        </div>
                        <hr style="width: 95%">
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <!-- 하단 메뉴(바로가기, 글쓰기) -->
        <div class="text-center">
            <c:if test="${not empty pagingStr}">
                <div class="paging">
                        ${pagingStr}
                </div>
            </c:if>
        </div>
    </div>
    <footer>
        <br><br><br>
    </footer>
</main>
</body>
</html>
