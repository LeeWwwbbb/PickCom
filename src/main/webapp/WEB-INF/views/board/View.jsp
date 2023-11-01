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
<%@ include file="../layout/category.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-2">
            <%@ include file="../layout/boardSide.jsp" %>
        </div>
        <div class="col-10">
            <div class="container">
                <main>
                    <c:if test="${not empty map}">
                        <a href="/board/${ map.board_cate }"
                           class="nav-link px-10" style="font-size: 24px; font-weight: bold;">${ cate }
                            게시판</a>
                        <table class="table mb-4">
                            <colgroup>
                                <col width="15%"/>
                                <col width="35%"/>
                                <col width="15%"/>
                                <col width="*"/>
                            </colgroup>

                            <!-- 게시글 정보 -->

                            <tr>
                                <td>번호</td>
                                <td>${ map.board_num }</td>
                                <td>작성자</td>
                                <td>${ map.member_name }</td>
                            </tr>
                            <tr>
                                <td>작성일</td>
                                <td>${ map.board_createDate }</td>
                                <td>조회수</td>
                                <td>${ map.board_visitCount }</td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td colspan="3">${ map.board_title }</td>
                            </tr>
                            <tr>
                                <td colspan="4" height="300">${ map.board_content }</td>
                            </tr>
                                <%--<!-- 첨부파일 -->
                                <tr>
                                    <td>첨부파일</td>
                                    <td colspan="3"><c:if test="${ not empty map.board_ofile }">
                                        ${ map.board_ofile }
                                        <a
                                                href="../board/download.do?ofile=${ map.board_ofile }&sfile=${ map.board_sfile }&idx=${ map.board_no }">
                                            [다운로드] </a>
                                    </c:if></td>
                                </tr>--%>
                        </table>

                        <!-- 하단 메뉴(버튼) -->
                        <div class="row mb-4">
                            <div class="col-2">
                                <c:if
                                        test="${ sessionScope.rank > 0 || map.member_nickName == sessionScope.name }">
                                    <button type="button" class="btn btn-danger w-100"
                                            onclick="location.href='/board/deleteBoard/${ map.board_cate }/${ map.board_num }';">
                                        삭제하기
                                    </button>
                                </c:if>
                            </div>
                            <div class="col text-end">
                                <c:if test="${ not empty sessionScope.name }">
                                    <a href="/board/like/${ map.board_cate }}/${ map.board_num }"> <c:if
                                            test="${ like }">
                                        <img src="../../../img/likeTrue.png" alt=""
                                             style="width: 40px; height: 40px"/>
                                    </c:if> <c:if test="${ !like }">
                                        <img src="../../../img/likeFalse.png" alt=""
                                             style="width: 40px; height: 40px"/>
                                    </c:if>
                                    </a>
                                </c:if>
                            </div>
                            <div class="col-2">
                                <c:if
                                        test="${ sessionScope.rank > 0 || map.member_nickName == sessionScope.name }">
                                    <button type="button" class="btn btn-success w-100"
                                            onclick="location.href='/board/openBoardUpdate/${ map.board_num }';">수정
                                    </button>
                                </c:if>
                            </div>
                        </div>

                        <hr class="mb-4">

                        <table class="table">
                            <colgroup>
                                <col width="15%"/>
                                <col width="50%"/>
                                <col width="15%"/>
                                <col width="20%">
                            </colgroup>
                            <c:choose>
                                <c:when test="${ not empty commentList }">
                                    <c:forEach items="${ commentList }" var="row">
                                        <tr align="center">
                                            <td>${ row.member_nickName }</td>
                                            <td align="left">${ row.comment_content }</td>
                                            <td>${ row.comment_updatedDate }</td>
                                                <%--<td><c:if
                                                        test="${ sessionScope.rank != 0 || row.member_nickName == sessionScope.name }">
                                                    <button type="button" class="btn btn-success"
                                                            onclick="location.href='/board/openBoardUpdate/${ map.board_num }';">수정
                                                    </button>
                                                </c:if></td>--%>
                                            <td><c:if
                                                    test="${ sessionScope.rank > 0 || row.member_nickName == sessionScope.name }">
                                                <button type="button" class="btn btn-danger"
                                                        onclick="location.href='/board/deleteComment/${ map.board_cate }/${ map.board_num }/${ row.comment_num }';">
                                                    삭제
                                                </button>
                                            </c:if></td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </table>

                        <c:if test="${ sessionScope.name != null }">
                            <p>${ sessionScope.name }</p>
                            <div class="border border-secondary w-100"
                                 style="padding: 10px; height: 150px;">
                                <form action="/board/insertComment.do" method="post">
                                    <input type="hidden" name="board_num" value="${ map.board_num }"/>
                                    <input type="hidden" name="member_num" value="${ sessionScope.num }"/>
                                    <input type="hidden" name="board_cate" value="${ map.board_cate }"/>
                                    <textarea class="form-control  w-100 p-3" rows="4" cols="50"
                                              style="height: 100px; resize: none;"
                                              name="comment_content"></textarea>
                                    <div class="text-end">
                                        <button type="submit" class="btn btn-outline-primary">등록</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                </main>
            </div>
        </div>
    </div>
</div>
</body>
</html>
