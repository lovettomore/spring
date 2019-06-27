<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>사용자 등록</title>
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
	
	$(document).ready(function(){
		
		//싱글쿼터로 안묶으면  var msg = 중복된 아이디 입니다.; 이케 되는거임
		
		var msg = "${msg}";
		if(msg != '') alert(msg);
		
		
		//주소찾기 버튼 클릭 이벤트 핸들러
		$("#btnAddrSearch").on("click", function(){
			new daum.Postcode({
		        oncomplete: function(data) {
		        	/*
		        		주소 input value에 설정 data.roadAddress
		        		우편번호 input value에 설정 data.zonecode
		        	*/
		        	
		        	$("#addr1").val(data.roadAddress);
		        	$("#zipcd").val(data.zonecode);
		        }
		    }).open();
		});
		
		//개발용 데이터 초기화 함수 ****** 추후 지울것 ******
		dataInit();
		
		//사용자 등록 클릭버튼 클릭 이벤트 핸들러 
		$("#btnUserReg").on("click", function(){
			
			//원래는 유효성 체크를 하는게 맞아요
			//클라이언트에서도 하고 서버에서도하는게 맞아요 갠적인 의견이에옹
			
			//여기까지 도달하면 유효성 검사 완료(submit)
			$("#frm").submit();
		});
	});
	
	function dataInit(){
		$("#userId").val("lovememore");
		$("#pass").val("love1234");
		$("#name").val("박서경");
		$("#alias").val("또굥이");
		$("#addr1").val("대전광역시 중구 중앙로76");
		$("#addr2").val("영민빌딩 2층 204호");
		$("#zipcd").val("34940");
		$("#birth").val("1991-04-13");
	}
    
</script>

</head>

<body>
<div class="container-fluid">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<%@include file="/WEB-INF/views/common/header.jsp"%>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 등록</h2>
						
						<form id="frm" class="form-horizontal" action="${cp}/user/form" method="post" enctype="multipart/form-data" role="form">
							<div class="form-group">
								<label for="filename" class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="profile" name="profile">
								</div>
							</div>
							
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력하세요." value="${param.userId}">
								</div>
								<label for="userId" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									<span>${msg}</span>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">사용자 비밀번호</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호를 입력하세요." value="${param.pass}">
								</div>
							</div>

							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">사용자 이름</label>
								<div class="col-sm-10">
								<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요." value="${param.name}">
								</div>
							</div>
							
							<div class="form-group">
								<label for="alias" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="alias" name="alias" placeholder="사용자 별명을 입력하세요." value="${param.alias}">
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr1" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소를 입력하세요." value="${param.addr1}" readonly>
								</div>
								<div class="col-sm-1" style="text-align:right">
									<button type="button" id="btnAddrSearch" class="btn btn-default">주소검색</button>
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr2" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소를 입력하세요." value="${param.addr2}">
								</div>
							</div>
							
							<div class="form-group">
								<label for="zipcd" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="zipcd" name="zipcd" placeholder="우편번호를 입력하세요." value="${param.zipcd}" readonly>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="birth" name="birth" placeholder="생일을 입력하세요." value="${param.birth}">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10 text-right">
									<button type="submit" id="btnUserReg"class="btn btn-default">사용자 등록</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>