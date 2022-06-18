<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>회원 가입</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${root}/css/main.css'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- <script src='js/main.js'></script> -->
<%--     <script src='${root}/js/register.js'></script> --%>
</head>
<body>
    <!-- 상단 header-->
    <nav class = "navbar_sign_information">
        <div id = "before_sign" >
            <div>
                <a class="Sign_up" href="${root}/user/mvregister"> Sign Up</a>
            </div>
            <div>
                <a class = "nav-link text-white" href="#" data-toggle="modal" data-target="#loginModal"> Login</a>
            </div>
        </div>
        
    </nav>


    <!--Modal-->

    <div class="modal" id="loginModal">
      <div class="modal-dialog modal-sm">
          <div class="modal-content" style="background-color: rgb(61, 61, 61)">
              <!-- <div class="modal-header"></div> -->
              <!--Modal Body-->
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
              <!--Modal Body end-->
              <!-- <div class="modal-footer"></div> -->
          </div>
      </div>
  </div>

  <!--Modal-->


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
      <img src="${root}/img/arrow.png" width="20px">회원가입
    </div>
      
    <div class="register_main">
      <div class="register_content">
        <h2>회원 가입</h2>
        <div class="register_form">
          <div class="form_left">
            <div>아이디 <span>*</span></div>
            <div>비밀번호 <span>*</span></div>
            <div> 이름 <span>*</span></div>
            <div> 주소 <span>*</span></div>
            <div>전화번호 <span>*</span></div>
          </div>
	
		<form method="post" action="${root}/user/regist">   <!-- ##회원가입 -->
	          <div class="form_right">
	            <div><input type="text" id="register-id" name="id" ></div>
	            <div><input type="password" id="register-password" placeholder="  영문 숫자 포함 6자리 이상" name="pw"></div>
	            <div><input type="text" id="register-name" placeholder="  User Name" name="name"></div>
	            <div><input type="text" id="register-address" placeholder="  address" name="address"></div>
	            <div><input type="text" id="register-phone" placeholder="  010-xxxx-xxxx" name="phone"></div>
	            <div><button type="submit">등록</button></div>
	          </div>
         </form>
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

   <!--Modal-->

    <!-- <div class="modal" id="loginModal">
      <div class="modal-dialog modal-sm">
          <div class="modal-content" style="background-color: rgb(61, 61, 61)">
              <!- <div class="modal-header"></div> -->
              <!--Modal Body-->
              <!-- <div class="modal-body">
                  <form>
                      <div class="form-group">
                          <label for="login-id" class="text-white">아이디</label>
                          <input type="text" class="form-control" id="login-id" 
                          style="background-color: rgb(94, 96, 97)"/>
                      </div>
                      <div class="form-group">
                          <label for="login-password" class="text-white">비밀번호</label>
                          <input type="password" class="form-control" id="login-password" 
                          style="background-color: rgb(94, 96, 97);"/>  
                      </div>
                      <button onclick="login();" type="button" class="btn btn-light">로그인</button>
                      <div class="mt-3"><button type="submit" class="btn btn-dark">비밀번호 찾기</button></div>
                  </form>
              </div> -->
              <!--Modal Body end-->
              <!-- <div class="modal-footer"></div> -->
          <!-- </div>
      </div>
  </div>  -->

  <!--Modal-->

</body>
</html>
