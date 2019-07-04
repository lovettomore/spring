<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	$(document).ready(function(){
		
		$("#requestData").on("click", function(){
			//ajax는 jquery의 함수
			$.ajax({
				url:"/ajax/requestData",
				method:"post",
				success:function(data){
					console.log(data);
					//console.log > {pageVO: {page: 5, pageSize: 10}}
					$("#page").text(data.pageVO.page);
					$("#pageSize").text(data.pageVO.pageSize);
				}
			});
		});
		
		/* 
			org : {pageVO: {page: 5, pageSize: 10}} > data.pageVO.page로 접근
			@responseBody : {page: 5, pageSize: 10} > data.page로 접근
		*/
		
		$("#requestDataResponseBody").on("click", function(){
			//ajax는 jquery의 함수
			$.ajax({
				url:"/ajax/requestDateResponseBody",
				method:"post",
				success:function(data){
					//console.log > {page: 5, pageSize: 10}
					$("#pageResponseBody").text(data.page);
					$("#pageSizeResponseBody").text(data.pageSize);
				}
			});
		});
		
		//USER 클릭시 이벤트 핸들러
		$("#user").on("click", function(){
			$.ajax({
				url:"/ajax/user",
				method:"post",
				data:"userId=" + $("#userId").val(), //데이터를 보내야해요
				success:function(data){
					console.log(data);
// 					name <input type="text" id="name" readonly> 
// 					birth <input type="text" id="birth" readonly>
// 					alias <input type="text" id="alias" readonly>

					var html ="";
					html += "name <input type='text' id='name' value='"+ data.userVO.name +"' readonly>";
					html += "birth <input type='text' id='birth' value='"+ data.userVO.alias +"' readonly>";
					html += "alias <input type='text' id='alias' value='"+ data.userVO.birthStr +"' readonly>";
					
					$("#userJsonInfo").html(html);
					
// 					$("#name").val(data.userVO.name);
// 					$("#alias").val(data.userVO.alias);
// 					$("#birth").val(data.userVO.birthStr);
					
				}
			
			});
		});
		
		//userHtml 클래스 이벤트 핸들러
		$("#userHtml").on("click", function(){
			$.ajax({
				url:"/ajax/userHtml",
				method:"post",
				data: $("#frm").serialize(),
				success:function(data){
					//document.getElementById("userInfo").innerHTML(data);
					//document.getElementById("userInfo").innerTEXT(data);
					$("#userInfo").html(data);
				}
			});
		});
		
		
		//전송할 데이터 객체를 준비 (2019 07 04)
		/*
			이것을 만드는거에요 지금
			public class UserVO(){
				private String userId;
				private String pass;
				private String name;
				.
				.
				.
			}
			
			UserVO user = new UserVO();
			user.setUserId("brown");
			user.setPass("brown1234");
			
		*/
		//var user2 = new Object();
		var user = {userId:"brown", pass:"brown1234"};
		
		//JSON.stringify() : 자바스크립스 객체를 json 문자열로 생성
		//JSON.parse("JSON문자열") : json문자열을 자바스크립트 객체로 변경 > 얘는 잘 안쓸 확률이 높지만 그렇다고 안쓰진 않아요
		$("#userFormString").text("userId=brown&pass=brown1234");
		$("#userJsonString").text(JSON.stringify(user));
		
		
		$("#userJsonStringBtn").on("click", function(){
			//@ResponseBody 데이터 전송
			$.ajax({
				url:"/ajax/requestBody",
				method:"post",
				contentType : "application/json", //ajax를 통해서 보내는 데이터 형식이 json임을 알려준다.
				//서버로부터 받고싶은 데이터 타입을 지정을 할수가 있어요.
				//dataType : "json", //우리가 일을 할때는 이 형태를 많이 쓸꺼에요. server 측으로부터 받고자하는 데이터 타입이죠(Accept header)
				dataType : "xml", //얘로좀 받아보고 싶어요
				data : JSON.stringify(user),
				success : function(data){
					console.log(data);
					
					$("#userJsonResult .userID").text(data.getElementsByTagName("userId")[0].childNodes[0].textContent);
					$("#userJsonResult .pass").text(data.getElementsByTagName("pass")[0].childNodes[0].textContent);
					
// 					$(".userID").text(data.userId);
// 					$(".pass").text(data.pass);
				}
				
			});
		});
		
		
	});
</script>

<h3>ajax json 데이터 요청</h3>
<a id="requestData">데이터 가져오기</a>
<p>page : <span id="page"></span></p>
<p>pageSize : <span id="pageSize"></span></p>

<br><br>

<h3>ajax json 데이터 요청 (responseBody를 이용해서 작업))</h3>
<a id="requestDataResponseBody">데이터 가져오기</a>
<p>page : <span id="pageResponseBody"></span></p>
<p>pageSize : <span id="pageSizeResponseBody"></span></p>

<br><br>

<h3>ajax json 데이터 요청 (user)</h3>
<a id="user">사용자 데이터 가져오기</a>
<p>userId <input type="text" id="userId" value="sally"></p>
<div id="userJsonInfo"></div>

<br><br>

<h3>ajax json 데이터 요청 (user)</h3>
<a id="userHtml">사용자 데이터 가져오기</a>

<form id="frm">
	<p>userId <input type="text" id="userIdHtml" name="userId" value="brown"></p>
</form>

<div id="userInfo"></div>

<br><br>

<h3>ajax json 데이터 보내기(2019 07 04)</h3>
<a id="userJsonStringBtn">데이터 보내기</a><br>
요청 받는 데이터 기준 <div id="userFormString"></div><br>
요청 보내려는 데이터 <div id="userJsonString"></div><br>
받는 데이터
<div id="userJsonResult">
	userId : <span class="userID"></span><br>
	pass : <span class="pass"></span>
</div>
