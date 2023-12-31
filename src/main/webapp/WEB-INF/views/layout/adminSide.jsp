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
        @media (max-width: 1160px) {
            .sidebar {
                display: none;
            }
        }
    </style>
</head>
<body>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-light">
    <div class="sidebar bg-light">
        <hr>
        <ul class="nav nav-pills flex-column">
            <li class="nav-item">
                <a href="/userList" class="nav-link link-dark" aria-current="page">
                    <svg class="bi pe-none me-2" width="16" height="16"></svg>
                    유저 정보
                </a>
            </li>
            <li>
                <a href="/Update_Data" class="nav-link link-dark">
                    <svg class="bi pe-none me-2" width="16" height="16"></svg>
                    셀프 업데이트
                </a>
            </li>
        </ul>
        <hr>
    </div>
</div>

<script src="../../js/bundle.min/bootstrap.bundle.min.js"></script>
<script src="../../js/sidebars.js"></script>

</body>
</html>