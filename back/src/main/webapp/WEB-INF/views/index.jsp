<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>HAPPY HOUSE</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src='${root}/js/sojaeji.js'></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=014f573628962c21d2216e9a1829c586&libraries=services"></script>
    
    <!--부트스트랩-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<c:if test="${!empty msg}">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	
    <!-- 상단 header-->
    <div class = "container bg-dark ">
        <div class="row">
            <div class="col-10"></div>
            
            <c:if test="${empty loginUser}">
	            <div class="" id="before_sign" style="display: inline-flex;">
	                <div>
	                    <a class="nav-link text-white" href="${root}/user/mvregister"><small>Sign Up</small> </a>
	                </div>									
	                <div>
	                    <a class = "nav-link text-white" href="#" data-toggle="modal" data-target="#loginModal"> <small>Login</small></a>
	                </div>
	            </div>
            </c:if>
            
            <c:if test="${!empty loginUser}">
	            <div class="" id="after_sign" style="display: inline-flex; ">
	                <div>
	                    <a class="nav-link text-white" href="${root}/user/logout"><small> Logout</small></a>
	                </div>										
	                <div>
	                    <a class="nav-link text-white" href="${root}/user/search"><small>회원정보</small></a>
	                </div>									
	            </div>
            </c:if>
            
        </div>
    </div>

    <div class = "navbar navbar-expand "> 
        <div class="container">
            <a class="navbar-brand" href="${root}/user/index">
                <img src="${root}/img/home.png" width="20%" alt="home">
            </a>
            
        
            <div class="justify-content-end">
                <ul class="navbar-nav  ">
                    <li class="nav-item">
                        <a class = "nav-link text-dark" href="#" >공지사항</a>
                    </li>
                    <li class = "nav-item">
                        <a class =  "nav-link text-dark"href="#" >오늘의 뉴스</a>
                    </li>
                    <li class = "nav-item">
                        <a class =  "nav-link text-dark" href="#">주변 탐방</a>
                    </li>
                    <li class = "nav-item">
                        <a class =  "nav-link text-dark"href="#" >관심 지역 설정</a>
                    </li>
                    <li class = "nav-item">
                        <a class =  "nav-link text-dark"href="#">관심 지역 둘러보기</a>
                    </li>
                    <li class = "nav-item">
                            <button type = "button"><img src="${root}/img/search.png" width = "10px" alt="" ></button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container bg-image pt-5 pb-5" style=" background-image: url('${root}/img/home2.png');  background-repeat: no-repeat;
    background-attachment: scroll;
    background-position: center center;
    background-size: cover; text-align: center; height: 200px; color: white;" >
        
        <h3> HAPPY HOUSE</h3>
        <hr color="white" style="
        border: 0;
        height: 1px;
        background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
        margin-left: 15%;
    "width = "70%">
        <h5>행복한 우리 집</h5>
        
    </div>
    <div class ="container bg-dark pb-4 mb-3" style="text-align: center;">
        <br>
        <!-- 해당지역의 아파트, 주택 정보를 검색 -->
        <form action="${root}/house/search"> 
            <select id="sido2" name="sido2" style="background-color: #787878; color: blanchedalmond;">
                <!-- <option value="">도/광역시</option> -->
            </select>
            <select id="gugun2" name="gugun2" style="background-color: #787878; color: blanchedalmond;">
                <!-- <option value="">시/구/군</option> -->
            </select>
            <select id="dong2" name="dong2" style="background-color: #787878; color: blanchedalmond;">
                <!-- <option value="">동</option> -->
            </select>
            <script type="text/javascript">
                new sojaeji('sido2', 'gugun2', 'dong2');
            </script>
        </form>
        <form action="${root}/house/searchname">
        	<input type="text" name="name" style="background-color: #787878; color: blanchedalmond;"/>
        	<button type="submit">검색</button>
        </form> 
    </div>
    <div id = "map" class="container mb-3" style="height: 350px;">
       
		<script src="${root}/js/map.js"></script>
    </div>

    <!--boarder-->
    <div class="container">
        <div class="row">
            <div class="col-sm-3 border border-dark " style=" height: 300px; background-color: rgb(164, 217, 238);">
                <h3>SSAFY 고지서 신청하고 포인트 받으세요!</h3>
                <h4 style="color:purple">#100만원 당첨기회</h4>
                <h4  style="color:purple">#재산세 #주민세</h4>
                <img style="float: right;" src="${root}/img/home3.png" width="120px">
            </div>
            
            <div class="col-sm-4 ml-5">
                <h2>지혜롭게 내집 마련하기</h2>
                <hr width="60%" style="
                border: 0;
                height: 1px;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
                margin-left: 0px;
            ">
                <img src="${root}/img/icon/icon1.png" width="20px" >가용자금 확인 및 대출계획
                <br> <img src="${root}/img/icon/icon2.png" width="20px" >집 종류 및 지역 선택
                <br> <img src="${root}/img/icon/icon3.png" width="20px" >정보 수집 & 시세파악
                <br> <img src="${root}/img/icon/icon4.png" width="20px" >부동산 방문 & 집구경
                <br> <img src="${root}/img/icon/icon5.png" width="20px" >계약 및 잔금 치르기
                <br> <img src="${root}/img/icon/icon6.png" width="20px" >소유권 이전 등기
                <br> <img src="${root}/img/icon/icon7.png" width="20px" >인테리어 공사
                <hr width="60%" style="
                border: 0;
                height: 1px;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
                margin-left: 0px;
            ">
            </div> 
            <div class="col-sm-4 ">
                <h2>오늘의 뉴스</h2>
                <hr width="70%" style="
                border: 0;
                height: 1px;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
                margin-left: 0px;
            ">
                <dl>
                    <dt><h3>서울 아파트. 매매ㆍ전세 다른 양상... 상승.. 아시아경제</h3></dt>
                    <dd style="list-style-type: square;">   
                        <li>
                        <a href="#">서울 입주 2년차 아파트 전셋값 1억 4000만원 올라</a> 한겨레
                        </li>
                        <li>
                            <a href="#">12ㆍ16發 거래한파... 매수자가 사라졌다</a> 아시아경제
                        </li>
                        <li>
                            <a href="#">재건축 대안 뜨는 수직증축 리모델링... 추진 슥..</a> 동아일보
                        </li>
                        <li>
                            <a href="#">고가기준 9억, 서울 아파트 중간값 됐다</a> 동아일보
                        </li>
                    </dd> 
                </dl>
            </div> 
        </div>
        <!--boarder-->
    </div>
    <div class="container mt-2">
    <!--footer-->
        <div class="row">
            <div class="col-1 mt-2">
                <div class="justify-content-start" >
                    <img src="${root}/img/ssafy_icon.png" alt="ssafy_icon" width="120px" >
                </div>
            </div>
            <div class="col-1"></div>
            <div class="col-6">
                <div>
                    <h2 class="font-weight-light">Find Us</h2>
                </div>
                <div>
                    <hr width="500px" style="
                        border: 0;
                        height: 1px;
                        background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
                        margin-left: 0px;
                    ">
                </div>
                <div>
                    <img src="${root}/img/location.jpg" width="20px"> (SSAFY) 서울시 강남구 테헤란로 멀티스퀘어<br>
                    <img src="${root}/img/call.jpg" width="20px"> 1544-9001<br>
                    <img src="${root}/img/email.png" width="20px"> <a href="#">admin@ssafy.com</a>
                </div>
            </div>
            
        </div>

        <div class="text-center p-4 mt-3" style="background-color: rgba(95, 85, 85, 0.05);">
            Copyright by SSAFY. All rights reserved
        </div>

    <!--Modal -->

    <div class="modal" id="loginModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content" style="background-color: rgb(61, 61, 61)">
                <!-- <div class="modal-header"></div> -->
                <!--Modal Body-->
                <div class="modal-body">
                    <form method="post" action="${root}/user/login">
                    		<!-- 2. select를 통해 로그인 -->
                        <div class="form-group">
                            <label for="login-id" class="text-white">아이디</label>
                            <input type="text" class="form-control" id="login-id" name="id"  
                            style="background-color: rgb(94, 96, 97)"/>
                        </div>
                        <div class="form-group">
                            <label for="login-password" class="text-white">비밀번호</label>
                            <input type="password" class="form-control" id="login-password" name="pw"  
                            style="background-color: rgb(94, 96, 97);"/>  
                        </div>
                        <button type="submit" class="btn btn-light">로그인</button>
              
                    </form>
                    	<div class="mt-3"><button type="button" class="btn btn-dark">비밀번호 찾기</button></div>
                </div>
                <!--Modal Body end-->
                <!-- <div class="modal-footer"></div> -->
            </div>
        </div>
    </div>

    <!--Modal -->

</body>
  
</html>

