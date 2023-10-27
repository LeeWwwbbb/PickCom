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
</head>
<body>
<main>
    <div class="row">
        <div class="col-2">
            <%@ include file="../layout/mySide.jsp" %>
        </div>
        <div class="col-9">
            <div class="container">
                <h2>추천한 글</h2>
                <!-- 목록 테이블 -->
                <table border="1" width="90%" class="table">
                    <thead class="table-light">
                    <tr align="center">
                        <th width="10%" scope="col">분류</th>
                        <th width="*" scope="col">제목</th>
                        <th width="15%" scope="col">작성자</th>
                        <th width="10%" scope="col">작성일</th>
                        <th width="8%" scope="col">조회수</th>
                        <th width="8%" scope="col">추천수</th>
                    </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${ empty boardList }">
                            <tr>
                                <td colspan="6" align="center">추천한 글이 없습니다</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${ boardList }" var="row" varStatus="loop">
                                <tr align="center">
                                    <td>${ row.cate }<input type="hidden"
                                                            value="${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}"/>
                                    </td>
                                    <td align="left"><a
                                            href="/board/${ row.board_cate }/${ row.board_num }">${ row.board_title }</a>
                                    </td>
                                    <td>${ row.member_nickName }</td>
                                    <td>${ row.board_createDate }</td>
                                    <td>${ row.board_visitCount }</td>
                                    <td>${ row.like_count }</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </table>
                <div class="row">
                    <div class="col text-center"><c:if test="${not empty pagingStr}">
                        <div class="paging">
                                ${pagingStr}
                        </div>
                    </c:if></div>
                </div>
            </div>
        </div>
    </div>
</main>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>