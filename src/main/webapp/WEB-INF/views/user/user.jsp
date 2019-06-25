<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>사용자 상세 조회</title>
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		//사용자 등록 클릭버튼 클릭 이벤트 핸들러 
		$("#btnUserModify").on("click", function(){
			$("#frm").submit();
		});
	});
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
						<h2 class="sub-header">사용자 상세 조회</h2>
							
						<form id="frm" class="form-horizontal" action="${cp}/userModify" method="get" role="form">
							<input type="hidden" id="userId" name="userId" value="${userVO.userId}">
						
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<img src="${cp}/profile?userId=${userVO.userId}" style="width:150px">
								</div>
								<label for="userNm" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									${msg}
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.userId}</label>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.name}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.alias}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.addr1}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.addr2}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.zipcd}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<label class="control-label"><fmt:formatDate value="${userVO.birth}" pattern="yyyy-MM-dd"/></label>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button" id="btnUserModify" class="btn btn-default">사용자 수정</button>
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