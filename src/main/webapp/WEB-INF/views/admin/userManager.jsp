<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<html>
<head>
    <title>PickCom:견적추천 커뮤니티</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        var selectedMemberNo = null;

        $(document).ready(function () {
            $("table").on("click", "td", function () {
                var memberNo = $(this).closest("tr").find("input[type='hidden']").val();
                selectedMemberNo = memberNo;
            });

            $("#userManageForm").submit(function (event) {
                event.preventDefault();
                if (selectedMemberNo !== null) {
                    $(this).append("<input type='hidden' name='num' value='" + selectedMemberNo + "'>");
                    this.submit();
                }
            });

            $(".delete-btn").click(function () {
                var memberNo = $(this).data("member-no");
                setSelectedMemberNo(memberNo);
            });

            $(".update-btn").click(function () {
                var memberNo = $(this).data("member-no");
                var memberNm = $("#userName_" + memberNo).val();

                setSelectedMemberNo(memberNo);
                setSelectedMemberName(memberNm);
                alert(memberNm);
            });
            $(document).ready(function () {
                // 검색 버튼 클릭 시
                $("#searchButton").click(function (event) {
                    // 선택된 검색 유형을 가져옵니다.
                    var selectedSearchType = $("select[name='searchField']").val();

                    if (selectedSearchType === "") {
                        // '검색 유형 선택'이 선택되었을 때 팝업 띄우기
                        alert("검색 유형을 선택하세요."); // 또는 원하는 팝업 띄우기 코드 사용
                        event.preventDefault(); // 폼 제출 방지
                    } else {
                        // 선택된 검색 유형이 유효한 경우 폼을 제출
                        $("form#userManageForm").submit();
                    }
                });
            });
        });

        function setSelectedMemberNo(memberNo) {
            document.getElementById("idx").value = memberNo;
        }

        function setSelectedMemberName(memberNm) {
            document.getElementById("userNm").value = memberNm;
        }
    </script>
</head>
<body>
<main>
    <div class="position-absolute top-50 start-50 translate-middle h-75 d-inline-block" style="width: 1200px;">
        <!-- 사용자 검색 -->
        <form action="/admin/search" method="get">
            <div class="row mb-4" id="keyword">
                <div class="col-2">
                    <select name="searchField" class="form-select">
                        <option value="">검색 유형 선택</option>
                        <option value="i">아이디</option>
                        <option value="e">이메일</option>
                        <option value="a">닉네임</option>
                    </select>
                </div>
                <div class="col-8">
                    <input type="text" class="form-control" name="keyword"/>
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary w-100" id="searchButton">검색</button>
                </div>
            </div>
        </form>
        <!-- 사용자 테이블 -->
        <div>
            <table width="90%" class="table table-hover">
                <thead class="table-light">
                <tr align="center">
                    <th width="12%">아이디</th>
                    <th width="8%">닉네임</th>
                    <th width="12%">이메일</th>
                    <th width="12%">가입일</th>
                    <th width="12%">관리</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${ empty adminList }">
                        <tr>
                            <td colspan="8" align="center">유저가 한명도 없습니다</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${ adminList }" var="row" varStatus="loop">
                            <tr align="center">
                                <td>${ row.member_id }<input type="hidden" value="${ row.member_num }"/></td>
                                <td><input type="text" value="${row.member_name}" class="form-control"
                                           id="userName_${row.member_num}" name="userName"/></td>
                                <td>${ row.member_email }</td>
                                <td>${ row.member_regDate }</td>
                                <td>
                                    <div style="display: flex; justify-content: space-between;">
                                        <button type="button" class="btn btn-danger delete-btn" data-bs-toggle="modal"
                                                data-bs-target="#modal" data-member-no="${ row.member_num }">삭제
                                        </button>
                                        <button type="button" class="btn btn-primary update-btn" data-bs-toggle="modal"
                                                data-bs-target="#modal_upd" data-member-no="${row.member_num}"
                                                data-member-nm="${userName}">업데이트
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <div class="row mb-4">
                <div class="col text-center">${ map.pagingImg }</div>
            </div>
            <div class="row mb-4">
                <div class="col-2"></div>
                <div class="col-8 text-center">
                    <c:if test="${not empty pagingStr}">
                        <div class="paging">
                                ${pagingStr}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <input type="hidden" name="idx" id="idx"/>
        <input type="hidden" name="userNm" id="userNm"/>


    </div>

    <div class="modal" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">사용자 삭제</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>사용자 정보를 삭제하시겠습니까?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" data-bs-dismiss="modal" class="btn btn-primary" id="userDeleteBtn"
                            onclick="location.href='memberDelete/' + document.getElementById('idx').value;">확인
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="modal_upd" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">사용자 업데이트</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal_upd" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>사용자 정보를 업데이트하시겠습니까?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" data-bs-dismiss="modal" class="btn btn-primary" id="userUpdateBtn"
                            onclick="location.href='memberUpdate/' + document.getElementById('idx').value + '/' + document.getElementById('userNm').value">
                        확인
                    </button>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>