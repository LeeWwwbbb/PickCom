<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.util.Iterator" %>
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
    <style>
        .scrolling-container {
            display: flex;
            flex-wrap: nowrap; /* Prevent wrapping to the next line */
            overflow-x: auto; /* Enable horizontal scrolling */
            white-space: nowrap; /* Prevent line breaks */
        }

        .scrolling-container .col-md-4 {
            flex: 0 0 auto; /* Don't grow or shrink, stay fixed at 33.3333% width */
            margin-right: 15px; /* Add some space between cards */
        }
    </style>
</head>
<body>
<main>
    <div class="container">
        <h1>게이밍</h1>
        <div class="scrolling-container">
            <%
                JSONArray jsonArray = (JSONArray) request.getAttribute("jsonObject");
                if (jsonArray != null) {
                    Iterator<JSONObject> iterator = jsonArray.iterator();
                    int rank = 1;
                    while (iterator.hasNext()) {
                        JSONObject obj = iterator.next();
                        long totalPrice = (long) obj.get("TotalPrice");
                        String formattedTotalPrice = new DecimalFormat("###,###").format(totalPrice);
            %>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= rank++ %>위</h5>
                        <p class="card-text">CPU: <%= obj.get("CPU") %></p>
                        <p class="card-text">MBoard: <%= obj.get("MBoard") %></p>
                        <p class="card-text">RAM: <%= obj.get("RAM") %></p>
                        <p class="card-text">RAMSize: <%= obj.get("RAMSize") %></p>
                        <p class="card-text">VGA: <%= obj.get("VGA") %></p>
                        <p class="card-text">SSD: <%= obj.get("SSD") %></p>
                        <p class="card-text">Power: <%= obj.get("Power") %></p>
                        <p class="card-text">Cooler: <%= obj.get("Cooler") %></p>
                        <p class="card-text">Case: <%= obj.get("Case") %></p>
                        <p class="card-text">TotalPrice: <%= formattedTotalPrice %>원</p>

                        <button class="btn btn-primary copy-button" data-clipboard-target="#json<%= rank %>">클립보드로 복사</button>
                        <textarea id="json<%= rank %>" style="display: none;">
                            CPU: <%= obj.get("CPU") %>
                            Mboard: <%= obj.get("MBoard") %>
                            RAM: <%= obj.get("RAM") %>
                            RAMSize: <%= obj.get("RAMSize") %>
                            VGA: <%= obj.get("VGA") %>
                            SSD: <%= obj.get("SSD") %>
                            Power: <%= obj.get("Power") %>
                            Cooler: <%= obj.get("Cooler") %>
                            Case: <%= obj.get("Case") %>
                            Case_Color: <%= obj.get("Case_Color") %>
                            TotalPrice: <%= formattedTotalPrice %>원
                        </textarea>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</main>
<!-- Bootstrap JS 및 jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Clipboard Copy Script -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var copyButtons = document.querySelectorAll('.copy-button');

        copyButtons.forEach(function (button, index) {
            button.addEventListener('click', function () {
                var textareaId = 'json' + (index + 2);
                var textarea = document.getElementById(textareaId);

                try {
                    navigator.clipboard.writeText(textarea.value);
                    alert('복사되었습니다.');
                } catch (err) {
                    console.error('Clipboard Copy Error:', err);
                }
            });
        });
    });
</script>


</body>
</html>
