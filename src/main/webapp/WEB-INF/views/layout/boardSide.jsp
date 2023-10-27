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
</head>
<body>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-light">
    <hr>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a href="/board/notice" class="nav-link link-dark" aria-current="page"
               onclick="changeActiveClass(this)">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                공지사항
            </a>
        </li>
        <li>
            <a href="/board/free" class="nav-link link-dark" onclick="changeActiveClass(this)">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                자유 게시판
            </a>
        </li>
        <li>
            <a href="/board/review" class="nav-link link-dark" onclick="changeActiveClass(this)">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                리뷰 게시판
            </a>
        </li>
        <li>
            <a href="/board/image" class="nav-link link-dark" onclick="changeActiveClass(this)">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                사진 게시판
            </a>
        </li>
        <li>
            <a href="/board/question" class="nav-link link-dark" onclick="changeActiveClass(this)">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                질문 게시판
            </a>
        </li>
    </ul>
    <hr>
</div>

<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>