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
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@ include file="../layout/category.jsp" %>
<div class="container" style="max-width: 75%; margin: 0 auto;">
    <main>
        <c:if test="${not empty map}">
            <br>
            <h2 align="center">${ map.news_title }</h2>
            <p style="text-align: right">${ map.news_writer }</p>
            <hr>
            <div class="newsContent">
                <c:if test="${ not empty map.news_image }">
                    <img src="${ map.news_image }" alt="첨부 이미지"
                         style="width: 100%;"/><br></c:if>
                    ${ map.news_content }
            </div>
        </c:if>
    </main>
</div>
</body>
</html>
