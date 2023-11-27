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

    <script>
        $(document).ready(function(){
            $(".next-button").click(function(){
                var selectedOption = $("input[name='option']:checked").val();
                var selectedPriceRange = $("input[name='priceRange']:checked").val();

                // 선택한 값을 서버로 전송
                $.ajax({
                    type: "POST",
                    url: "/submit",
                    data: {
                        option: selectedOption,
                        priceRange: selectedPriceRange
                    },
                    success: function(response){
                        console.log(response);
                        // 서버 응답에 따른 처리
                    },
                    error: function(error){
                        console.log(error);
                    }
                });
            });
        });
    </script>

    <style>

        /* 사용자 정의 스타일 */
        .used {
            padding: 15px 10px;
        }

        .used input[type=radio] {
            display: none;
        }

        .used input[type=radio] + label {
            display: inline-block;
            cursor: pointer;
            height: 60px; /* 원하는 높이로 조절 */
            width: 100%; /* 부모 요소에 꽉 차도록 설정 */
            border: 1px solid #333;
            line-height: 60px;
            font-weight: bold;
            font-size: 20px;
            text-align: center;
            background-color: #fff;
            color: #333;
        }

        .used input[type=radio]:checked + label {
            background-color: #333;
            color: #fff;
        }
        .centered-content {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 화면 전체 높이로 설정 */
        }
        .selection-title {
            text-align: center;
        }
        .next-button {
            display: inline-block;
            padding: 15px 10px;
            cursor: pointer;
            height: 60px; /* 원하는 높이로 조절 */
            width: 100%; /* 부모 요소에 꽉 차도록 설정 */
            border: 1px solid #333;
            line-height: 10px;
            font-weight: bold;
            font-size: 20px;
            text-align: center;
            background-color: #fff;
            color: #333;
        }
        .next-button:hover {
            background-color: #333;
            color: #fff;
        }
        .next-button-container {
            position: fixed;
            bottom: 10px;
            left: 10px;
            z-index: 999; /* 다른 요소 위에 위치하도록 설정 */
        }
        .price-range {
            display: flex;
            flex-direction: column; /* Set the flex direction to column for vertical alignment */
            align-items: flex-start; /* Align items to the start of the column */
            margin-bottom: 20px;
        }

        .price-range label {
            cursor: pointer;
            background-color: #fff;
            border: 1px solid #333;
            padding: 10px;
            width: 100%;
            font-size: 20px;
            text-align: center;
            margin-bottom: 5px; /* Add margin between each radio button */
        }

        .price-range input[type=radio] {
            display: none;
        }

        .price-range input[type=radio]:checked + label {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
<main>
    <div class="container-fluid">
        <div class="row centered-content">
            <!-- 왼쪽 선택지 -->
            <div class="col-md-6">
                <div class="row">
                    <div class="col-12">
                        <h3 class="selection-title">선택지</h3>
                    </div>
                    <!-- 선택지 1 -->
                    <div class="col-md-6 col-12">
                        <div class="used">
                            <img src="../../../img/office.png" alt="사무용" class="img-fluid office-image">
                            <input type="radio" name="option" value="S" id="officeOption" onclick="selectOption(this)">
                            <label for="officeOption">사무용</label>
                        </div>
                    </div>
                    <!-- 선택지 2 -->
                    <div class="col-md-6 col-12">
                        <div class="used">
                            <img src="../../../img/gaming.png" alt="게이밍" class="img-fluid gaming-image">
                            <input type="radio" name="option" value="G" id="gamingOption" onclick="selectOption(this)">
                            <label for="gamingOption">게이밍</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="col-12">
                    <div class="price-range">
                        <input type="radio" name="priceRange" value="60" id="price60" onclick="selectPriceRange(this)">
                        <label for="price60">60만원대</label>
                        <input type="radio" name="priceRange" value="70" id="price70" onclick="selectPriceRange(this)">
                        <label for="price70">70만원대</label>
                        <input type="radio" name="priceRange" value="80" id="price80" onclick="selectPriceRange(this)">
                        <label for="price80">80만원대</label>
                        <input type="radio" name="priceRange" value="100" id="price100" onclick="selectPriceRange(this)">
                        <label for="price100">100만원대</label>
                        <input type="radio" name="priceRange" value="130" id="price130" onclick="selectPriceRange(this)">
                        <label for="price130">130만원대</label>
                        <input type="radio" name="priceRange" value="200" id="price200" onclick="selectPriceRange(this)">
                        <label for="price200">200만원대</label>
                    </div>
                </div>
            </div>
            <div class="next-button-container">
                <button class="btn btn-primary next-button">다음</button>
            </div>
        </div>
    </div>
</main>


<!-- Bootstrap JS 및 jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
