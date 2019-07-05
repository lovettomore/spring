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
	
	<script>
		$(document).ready(function(){
			$("#lprodSelect").on("change", function(){
				var value = $(this).children("option:selected").val();
				
				
			});
		});
		
	</script>
</head>

<body>

<form id="lprodGuFrm" action="${cp}/prod/pagingList" method="post">
	<input type="hidden" name="lprodGu" value="">
</form>

<div class="container-fluid">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<%@include file="/WEB-INF/views/common/header.jsp"%>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">PROD</h2>
						<div class="table-responsive">
							<div class="">
								<select name="lprodSelect" id="lprodSelect">
									<c:forEach items="${lprodList}" var="lprod">
									<option value="${lprod.lprod_gu}">${lprod.lprod_nm}</option>
									</c:forEach>
								</select>
							</div>
							<table class="table table-striped">
								<tr>
									<th>PROD ID</th>
									<th>PROD NAME</th>
									<th>PROD LGU</th>
									<th>PROD BUYER</th>
									<th>PROD COST</th>
									<th>PROD PRICE</th>
									<th>PROD SALE</th>
								</tr>
								
								<c:forEach items="${prodList}" var="prod">
									<tr>
										<td>${prod.prod_id}</td>
										<td>${prod.prod_name}</td>
										<td>${prod.prod_lgu}</td>
										<td>${prod.prod_buyer}</td>
										<td>${prod.prod_cost}</td>
										<td>${prod.prod_price}</td>
										<td>${prod.prod_sale}</td>
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
											<a href="${cp}/prod/pagingList?page=${pageVO.page-1}&pageSize=${pageVO.pageSize}">«</a>
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
												<a href="${cp}/prod/pagingList?page=${i}&pageSize=${pageVO.pageSize}">${i}</a>
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
											<a href="${cp}/prod/pagingList?page=${pageVO.page+1}&pageSize=${pageVO.pageSize}">»</a>
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