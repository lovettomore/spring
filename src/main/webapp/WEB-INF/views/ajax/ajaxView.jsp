<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	$(document).ready(function(){
		
		$("#requestData").on("click", function(){
			//ajax는 jquery의 함수
			$.ajax({
				url:"/ajax/requestData",
				method:"post",
				success:function(data){
					$("#page").text(data.pageVO.page);
					$("#pageSize").text(data.pageVO.pageSize);
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
	});
</script>

<h1>ajax json 데이터 요청</h1>
<a id="requestData">데이터 가져오기</a>
<p>page : <span id="page"></span></p>
<p>pageSize : <span id="pageSize"></span></p>

<h1>ajax json 데이터 요청 (user)</h1>
<a id="user">사용자 데이터 가져오기</a>
<p>userId <input type="text" id="userId" value="sally"></p>
<div id="userJsonInfo"></div>


<h1>ajax json 데이터 요청 (user)</h1>
<a id="userHtml">사용자 데이터 가져오기</a>

<form id="frm">
	<p>userId <input type="text" id="userIdHtml" name="userId" value="brown"></p>
</form>

<div id="userInfo"></div>
