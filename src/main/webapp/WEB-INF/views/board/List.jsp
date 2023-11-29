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
    <style>
        .fixed-text {
            max-width: 200px; /* 원하는 최대 너비로 설정 */
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis; /* 텍스트가 너무 길 경우 생략 부호 (...) 사용 */
        }

        .paging a {
            color: black;
            text-decoration-line: none;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
            crossorigin="anonymous"></script>
</head>
<body>
<main>
    <%@ include file="../layout/category.jsp" %>
    <div class="row">
        <div class="col-2">
            <%@ include file="../layout/boardSide.jsp" %>
        </div>
        <div class="col-9">
            <div class="container">
                <h4 class="mb-4">
                    <c:if test="${not empty cate}">
                        <span class="fixed-text">${ cate } 게시판</span>
                    </c:if>
                </h4>
                <table border="1" width="90%" class="table">
                    <thead class="table-light">
                    <tr align="center">
                        <th width="10%" scope="col">번호</th>
                        <th width="*" scope="col" align="left" style="text-align: left;">제목</th>
                        <th width="15%" scope="col">작성자</th>
                        <th width="15%" scope="col">작성일</th>
                        <th width="8%" scope="col">조회수</th>
                        <th width="8%" scope="col">추천수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${ empty boardList }">
                            <tr>
                                <td colspan="6" align="center">작성한 게시글이 없습니다</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${ boardList }" var="row" varStatus="loop">
                                <tr align="center">
                                    <td>${ row.board_num }<input type="hidden"
                                                                 value="${ row.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}"/>
                                    </td>
                                    <td align="left"><a class="text-dark fixed-text" style="text-decoration-line: none"
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
                    </tbody>
                </table>

                <!-- 하단 메뉴(바로가기, 글쓰기) -->
                <div class="row mb-4">
                    <div class="col-2"></div>
                    <div class="col-8 text-center">
                        <c:if test="${not empty pagingStr}">
                            <div class="paging">
                                    ${pagingStr}
                            </div>
                        </c:if>
                    </div>
                    <div class="col-2">
                        <c:if test="${ not empty sessionScope.name }">
                            <form action="/board/write">
                                <input type="hidden" name="wCate" value="${cate}">
                                <button type="submit" class="btn btn-primary w-100">글쓰기
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <table border="1" width="90%">
                    <tr align="center">
                        <td></td>
                        <td width="100"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
</html>
