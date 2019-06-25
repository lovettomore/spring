<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<link rel="stylesheet" href="${cp}/css/bootstrap.min.css">
		<link rel="stylesheet" href="${cp}/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${cp}/css/signIn.css">
		<script src="${cp}/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="${cp}/js/js.cookie.js"></script>
		
		<script>
			$(document).ready(function(){
				//이게 싫으면 맨 밑에에 넣으세여.
				console.log("ready document ready");
				console.log("remember me ready" + $("#rememberme").html());
				
				
				/*
					rememberme checkbox
					1.  rememberme cookie가 있는지 확인하고, 있으면 값이 true인지 확인 하세옹
					2. 	rememberme가 true이면 input id="rememberme" 체크박스를 체크 하세옹
				*/
				
				var rememberme = Cookies.get("rememberme");
				if(rememberme == "true"){
					$("#rememberme").prop("checked", true);
					$("#userId").val(Cookies.get("userId"));
					$("#password").focus();
				}
				
				/*
					쿠키를 언제 저장을 할것인가?
					중간에 값을 가로채기 위해서는 버튼의 타입을 submit에서 button으로 타입을 바꿔줘야 해용
				*/
				
				//로그인 버튼이 클릭되었을 때 실행되는 핸들러
				$("#signInBtn").on("click", function(){
					
					//만약 rememberme 체크박스가 체크 되어있는 경우 
					//사용자 아이디 값을 userId 쿠키로 저장
					//true 값을 rememberme cookie 값으로 저장
					
					//만약 rememberme 체크박스가 해제되어 있는 경우
					//userId, rememberme cookie값을 삭제
					
// 					if($("#rememberme").is(":checked")){
// 						Cookies.set("userId", $("#userId").val(), {expires :30});
// 						Cookies.set("rememberme", "true", {expires :30});
// 					}else{
// 						Cookies.remove("userId");
// 						Cookies.remove("rememberme");
// 					}
					
					//로그인 요청을 서버로 전송
					$("#frm").submit();
					
				});
				
			});
			
			//쿠키를 저장 
			//expires
			function setCookie(cookieName, cookieValue, expires){
				var dt = new Date();
				dt.setDate(dt.getDate() + parseInt(expires));
				
				document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + dt.toGMTString();
			}
			
			//쿠키를 삭제
			function deleteCookie(cookieName){
				setCookie(cookieName, "", -5);
			}
			
			
			//console.log("remember me" + $("#rememberme").html());
			
			//console.log("after document ready"); //얘가 먼저 실행 되지요
			
			//쿠키 이름에 해당하는 쿠키 값을 조회
			function getCookie(cookieName){
				
				//String[] cookieArray = CookieUtil.cookieString.split("; ");
				var cookieArray = document.cookie.split("; ");
				
				//String cookieValue = "";
				var cookieValue = "";
				
				//스크립트에서는 향상된 포문이 없기 때문에
				//for(String str : cookieArray) {
				for(var i=0; i<cookieArray.length; i++){
					var str = cookieArray[i];
					
					//if(str.startsWith(cookie+"=")) {
					if(str.startsWith(cookieName+"=")){
						
						//String[] cookieStr = str.split("=");
						var cookieStr = str.split("=");
						
						//cookieValue = cookieStr[1];
						cookieValue = cookieStr[1];
						break;
					}
				}
				
				return cookieValue;
			}
		</script>
	</head>
	<body>
	
		<%--
				어디로 요청을 보낼지 form태그의 action속성을 이용한다.
				어떻게 보낼지 http method ?? form태그 method 속성 (get-default/post)
				/login/login.jsp > /login/loginProcess.jsp 
		--%>

		<div class="container">
			<form id="frm" action="${cp}/login" method="post" class="form-signin">
				<h2 class="form-signin-heading">Please sign in ${cp}</h2>
				
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="text" id="userId" class="form-control" placeholder="userId" name="userId" value="${param.userId}" required>
				
				<label for="inputPassword" class="sr-only">Password</label> 
				<input type="password" id="password" class="form-control" placeholder="password" name="password" value="brown1234" required>
				
				<div class="checkbox">
					<label> <input type="checkbox" id="rememberme" name="rememberme" value="remember-me" >Remember me </label>
				</div>
				<button id="signInBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
			</form>
	
		</div>
	</body>
</html>
