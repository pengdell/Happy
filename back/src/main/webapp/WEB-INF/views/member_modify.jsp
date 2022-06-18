<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>회원정보 수정</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${root}/css/main.css'>
    <!-- <script src='js/main.js'></script> -->
<%--     <script src='${root}/js/modify.js'></script> --%>
</head>
<body>

	<c:if test="${empty loginUser}">
		<script>
			alert("로그인해주세요");
			location.href=${root}+"/user/index";
		</script>
	</c:if>

    <!-- 상단 header-->
    <nav class = "navbar_sign_information">
        <div id = "after_sign" >
            <div>
                <a class="logout" href="${root}/user/logout">Logout</a>
            </div>				
            <div>
                <a class="member_search" href="${root}/user/search">회원정보</a>
            </div>
        </div>
        
    </nav>
    <nav class = "nav_header">
        <a href="${root}/user/index"><img class = "left_img" src="${root}/img/home.png" width ="120px" alt="" ></a>
        <div class = "right_header">
            <a class = "notice" >공지사항</a>
            <a class = "today_news" >오늘의 뉴스</a>
            <button type = "button"><img src="${root}/img/search.png" width = "10px" alt="" ></button>

        </div>
    </nav>
    
    <div class="Home_register">
      <hr style="opacity: 0.3">
      <a href="${root}/user/index"><img src="${root}/img/icon/icon2.png" width="20px"> Home</a>
      <img src="${root}/img/arrow.png" width="20px">회원 정보 수정
    </div>
      
    <div class="register_main">
      <div class="register_content">
        <h2>회원 정보 수정</h2>
        <div class="register_form">
          <div class="form_left">
            <div>아이디 <span>*</span></div>
            <div>비밀번호 <span>*</span></div>
            <div> 이름 <span>*</span></div>
            <div> 주소 <span>*</span></div>
            <div>전화번호 <span>*</span></div>
          </div>

          <div class="form_right">
            <div>
           	 	<script>        
           		  //   document.write(JSON.parse(localStorage.getItem('login_user')).id);
           	 	</script>
            	${loginUser.id}
            </div>									
            <form method="post" action="${root}/user/modify">
    			<div><input type="hidden" id="id" value="${loginUser.id}" name="id"></div>
	            <div><input type="password" id="modify-password" placeholder="  영문 숫자 포함 6자리 이상" name="pw"></div>
	            <div><input type="text" id="modify-name" placeholder="  User Name" name="name"></div>
	            <div><input type="text" id="modify-address" placeholder="  address" name="address"></div>
	            <div><input type="text" id="modify-phone" placeholder="  010-xxxx-xxxx" name="phone"></div>
	            <div><button type="submit">수정</button></div>
            </form>
          </div>
        </div>   
      </div>
      </div>

    <footer>
        <div class="left_footer">
            <img src ="${root}/img/ssafy_icon.png" width="120px">
        </div>
        <div class="Find_Us">
            <h2>Find Us</h2>
            <hr width ="600px"> 
            <br>
        <img src="${root}/img/location.jpg" width="20px"> (SSAFY) 서울시 강남구 테헤란로 멀티스퀘어<br>
        <img src="${root}/img/call.jpg" width="20px"> 1544-9001<br>
        <img src="${root}/img/email.png" width="20px"> <a href="#">admin@ssafy.com</a>
        </div>
        <div class = "foot">
            <br>Copyright bt SSAFY. All rights reserved</div>
    </footer>

</body>
</html>
