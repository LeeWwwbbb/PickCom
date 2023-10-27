<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sidebars/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

    <style>
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>
    <link href="../../css/sidebars.css" rel="stylesheet">
</head>
<body>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-light">
    <hr>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a href="/board/notice" class="nav-link link-dark" aria-current="page">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                공지사항
            </a>
        </li>
        <li>
            <a href="/board/free" class="nav-link link-dark">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                자유 게시판
            </a>
        </li>
        <li>
            <a href="/board/review" class="nav-link link-dark">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                리뷰 게시판
            </a>
        </li>
        <li>
            <a href="/board/image" class="nav-link link-dark">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                사진 게시판
            </a>
        </li>
        <li>
            <a href="/board/question" class="nav-link link-dark">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                질문 게시판
            </a>
        </li>
    </ul>
    <hr>
</div>

<script src="../../js/bundle.min/bootstrap.bundle.min.js"></script>
<script src="../../js/sidebars.js"></script>

</body>
</html>