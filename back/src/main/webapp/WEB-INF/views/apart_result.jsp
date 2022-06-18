<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>거래정보</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <!-- <link rel='stylesheet' type='text/css' media='screen' href='css/main.css'> -->

    <!--부트스트랩-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
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
            <div class="" id="after_sign" style="display: inline-flex; display: none;">
                <div>
                    <a class="nav-link text-white" href="${root}/user/logout"><small> Logout</small></a>
                </div>
                <div>
                    <a class="nav-link text-white" href="${root}/user/search"><small>회원정보</small></a>
                </div>
            </div>
            </c:if>
        </div>
        <!-- <ul class="navbar-nav navbar-expand justify-content-end" id="before_sign">
            <li class="nav-item ">
                <a class="nav-link text-white" href="member_register.html"><small>Sign Up</small> </a>
            </li>
            <li class="nav-item pl-4">
                <a class = "nav-link text-white" href="#" data-toggle="modal" data-target="#loginModal"> <small>Login</small></a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-expand justify-content-end" style=" display: none;" id="after_sign">
            <li class="nav-item">
                <a class="nav-link text-white" href="#" onclick="logout()"><small> Logout</small></a>
            </li>
            <li class="nav-item pl-4">
                <a class="nav-link text-white" href="member_search.html"><small>회원정보</small></a>
            </li>
        </ul>
         -->
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
                            <button type = "button"><img src="${root}/img/search.png" width = "10px" alt="" ></button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <script src='${root}/js/sojaeji.js'></script>
    <div class ="container bg-dark pb-4 mb-3" style="text-align: center;">
        <br>
        <form >
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
    </div>
    
    <div class="container mt-5" >
        <div class = "row">
            <div class="col-4">
                <script>
                //    document.write(`${JSON.parse(localStorage.getItem("juso")).sido} ${JSON.parse(localStorage.getItem("juso")).gugun} ${JSON.parse(localStorage.getItem("juso")).dong}`);
                </script>
                <h4>${sido} ${gu} ${dong}</h4>
                <h2>거래정보</h2>
                <hr>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <div>
                	<c:forEach items="${ aptdeal }" var="apart" varStatus="vs">
						<h4>${apart.aptName}</h4>거래금액: ${apart.price_deal}만원<br>전용면적: ${apart.exclusivePrivateArea}<br><small>${apart.deal_year}.${apart.deal_month}.${apart.deal_day}</small><hr>
					</c:forEach>
					<c:forEach items="${ housedeal }" var="apart" varStatus="vs">
						<h4>${apart.houseName}</h4>거래금액: ${apart.price_deal}만원<br>전용면적: ${apart.exclusivePrivateArea}<br><small>${apart.deal_year}.${apart.deal_month}.${apart.deal_day}</small><hr>
					</c:forEach>
					<c:forEach items="${ aptrent }" var="apart" varStatus="vs">
						<h4>${apart.aptName}</h4>보증금액: ${apart.price_rent}만원<br>월세금액: ${apart.price_rentMonth}만원<br>전용면적: ${apart.exclusivePrivateArea}<br><small>${apart.rent_year}.${apart.rent_month}.${apart.rent_day}</small><hr>
					</c:forEach>
					<c:forEach items="${ houserent }" var="apart" varStatus="vs">
						<h4>${apart.houseName}</h4>보증금액: ${apart.price_rent}만원<br>월세금액: ${apart.price_rentMonth}만원<br>전용면적: ${apart.exclusivePrivateArea}<br><small>${apart.rent_year}.${apart.rent_month}.${apart.rent_day}</small><hr>
					</c:forEach>
				</div>
				<%-- <div id =d1></div> --%>
              
            </div>
            <div id="map" class="col-8" style="height: 600px;"  >
                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=014f573628962c21d2216e9a1829c586&libraries=services"></script>
                <script>
                    var container = document.getElementById('map');
                    var options = {
                        center: new kakao.maps.LatLng(37.50129420218191, 127.03960466814492),
                        level: 5
                    };
                    var map = new kakao.maps.Map(container, options);
                    var geocoder = new kakao.maps.services.Geocoder();
                  </script>
          		<!-- 마커 영역 -->
          		<c:forEach items="${requestScope.aptdeal}" var="item">
          			<script>
          				console.log("${sido}");
          				console.log("${gu}");
          				console.log("${dong}");
          				console.log("${item.jiNum}");
          				
          				geocoder.addressSearch("${sido} ${gu} ${dong} ${item.jiNum}", function(result, status) {
          					if (status === kakao.maps.services.Status.OK) {
          						var coords = new kakao.maps.LatLng(result[ 0].y, result[  0].x);
          						
          						var marker = new kakao.maps.Marker({
                                    map: map,
                                    position: coords
                               	 });//marker
          						
          						var infowindow = new kakao.maps.InfoWindow({
                                    //content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                                    content: `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`
                                    
                                    //var message = `<div style="padding:5px;">${selOffice}</div>`;
                               	 });//infowindow
          						infowindow.open(map, marker);
          					}//if
          				});//geocoder
          			</script>
          		</c:forEach>
          		
          		<c:forEach items="${requestScope.aptrent}" var="item">
          			<script>
          				console.log("${sido}");
          				console.log("${gu}");
          				console.log("${dong}");
          				console.log("${item.jiNum}");
          				
          				geocoder.addressSearch("${sido} ${gu} ${dong} ${item.jiNum}", function(result, status) {
          					if (status === kakao.maps.services.Status.OK) {
          						var coords = new kakao.maps.LatLng(result[ 0].y, result[  0].x);
          						
          						var marker = new kakao.maps.Marker({
                                    map: map,
                                    position: coords
                               	 });//marker
          						
          						var infowindow = new kakao.maps.InfoWindow({
                                    //content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                                    content: `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`
                                    
                                    //var message = `<div style="padding:5px;">${selOffice}</div>`;
                               	 });//infowindow
          						infowindow.open(map, marker);
          					}//if
          				});//geocoder
          			</script>
          		</c:forEach>
          		
          		<c:forEach items="${requestScope.housedeal}" var="item">
          			<script>
          				console.log("${sido}");
          				console.log("${gu}");
          				console.log("${dong}");
          				console.log("${item.jiNum}");
          				
          				geocoder.addressSearch("${sido} ${gu} ${dong} ${item.jiNum}", function(result, status) {
          					if (status === kakao.maps.services.Status.OK) {
          						var coords = new kakao.maps.LatLng(result[ 0].y, result[  0].x);
          						
          						var marker = new kakao.maps.Marker({
                                    map: map,
                                    position: coords
                               	 });//marker
          						
          						var infowindow = new kakao.maps.InfoWindow({
                                    //content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                                    content: `<div style="width:150px;text-align:center;padding:6px 0;">${item.houseName}</div>`
                                    
                                    //var message = `<div style="padding:5px;">${selOffice}</div>`;
                               	 });//infowindow
          						infowindow.open(map, marker);
          					}//if
          				});//geocoder
          			</script>
          		</c:forEach>
          		
          		<c:forEach items="${requestScope.houserent}" var="item">
          			<script>
          				console.log("${sido}");
          				console.log("${gu}");
          				console.log("${dong}");
          				console.log("${item.jiNum}");
          				
          				geocoder.addressSearch("${sido} ${gu} ${dong} ${item.jiNum}", function(result, status) {
          					if (status === kakao.maps.services.Status.OK) {
          						var coords = new kakao.maps.LatLng(result[ 0].y, result[  0].x);
          						
          						var marker = new kakao.maps.Marker({
                                    map: map,
                                    position: coords
                               	 });//marker
          						
          						var infowindow = new kakao.maps.InfoWindow({
                                    //content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                                    content: `<div style="width:150px;text-align:center;padding:6px 0;">${item.houseName}</div>`
                                    
                                    //var message = `<div style="padding:5px;">${selOffice}</div>`;
                               	 });//infowindow
          						infowindow.open(map, marker);
          						map.setCenter(coords);
          					}//if
          				});//geocoder
          			</script>
          		</c:forEach>
                
                <!--  -->
            </div> <!-- div map-->
        </div> <!-- div row-->
    </div> <!-- div container-->
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
            <!--footer-->
        </div>
        
        <div class="modal" id="loginModal">
            <div class="modal-dialog">
                <div class="modal-content" style="background-color: rgb(61, 61, 61)">
                    <div class="modal-header"></div>
                    <!-- Modal Body -->
                    <div class="modal-body">
                         <form action="${root}/user/login">
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
                    <!-- Modal Body end -->
                    <div class="modal-footer"></div>
                </div>
            </div>
       
        </div>
