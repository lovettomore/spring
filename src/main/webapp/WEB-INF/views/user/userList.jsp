<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>사용자 리스트</title>
	<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
</head>

<body>
	<div class="container-fluid">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<%@include file="/WEB-INF/views/common/header.jsp"%>
	
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디(el)</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								
								<%/* 
										[userList의 데이터를 한건씩 조회 해서 pageContext.setAttribute("user", vo)]
										items = "루프를 볼 대상을 el로 넣어줌" 
										var = "userList에 있는 데이터들을 user라는 속성의 이름으로 하나씩 뽑아서 쓸수 있게 el말고 그냥 표현"
										user.userId 얘는 게터세터에 있는 그 이름이랑 같아요.
										얘는 속성명이에요 UserVO에 있는 속성명과 같아야 해요.
								
								*/%>
								<c:forEach items="${userList}" var="user">
									<tr>
										<td>${user.userId}</td>
										<td>${user.name}</td>
										<td>${user.alias}</td>
										<td></td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>