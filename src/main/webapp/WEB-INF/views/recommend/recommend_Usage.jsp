<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<main>
    <div class="container-fluid">
        <div class="row">
            <!-- 왼쪽 선택지 -->
            <div class="col-md-3 bg-primary">
                <h3>선택지</h3>
                <ul class="list-group">
                    <li class="list-group-item">선택지 1</li>
                    <li class="list-group-item">선택지 2</li>
                </ul>
            </div>

            <!-- 중앙 콘텐츠 -->
            <div class="col-md-6">
                <h1>가운데 콘텐츠</h1>
                <!-- 여기에 중앙에 표시할 내용을 추가하세요. -->
            </div>

            <!-- 오른쪽 캐릭터 이미지 -->
            <div class="col-md-3">
                <img src="your-character-image.jpg" alt="캐릭터 이미지" class="img-fluid">
            </div>
        </div>
    </div>
</main>


<!-- Bootstrap JS 및 jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
