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

	<title>LPROD PAGING LIST</title>
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
						<h2 class="sub-header">LPROD</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>LPROD ID</th>
									<th>LPORD GU</th>
									<th>LPROD NM</th>
								</tr>
								
								<c:forEach items="${lprodList}" var="lprod">
									<tr>
										<td>${lprod.lprod_id}</td>
										<td>${lprod.lprod_gu}</td>
										<td>${lprod.lprod_nm}</td>
									<tr>
								</c:forEach>
							</table>
						</div>

<!-- 						<a class="btn btn-default pull-right">사용자 등록</a> -->
						
						<div class="text-center">
							<ul class="pagination">
							
								<c:choose>
									<c:when test="${pageVO.page == 1}">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${cp}/lprod/lprodPagingList?page=${pageVO.page-1}&pageSize=${pageVO.pageSize}">«</a>
										</li>
									</c:otherwise>
								</c:choose>
								
								<c:forEach begin="1" end="${paginationSize}" step="1" var="i">
									<c:choose>
										<c:when test="${pageVO.page == i}">
											<li class="active"><span>${i}</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp}/lprod/lprodPagingList?page=${i}&pageSize=${pageVO.pageSize}">${i}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:choose>
									<c:when test="${pageVO.page >= paginationSize}">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${cp}/lprod/lprodPagingList?page=${pageVO.page+1}&pageSize=${pageVO.pageSize}">»</a>
										</li>
									</c:otherwise>
								</c:choose>
								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>