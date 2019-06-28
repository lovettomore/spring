<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- custom script --%>
<script>
	$(document).ready(function(){
		$("tbody").on("click", ".userTr", function(){
			//$(this).data("userid");
			
			//사용자 아이디를 #userId값으로 설정해주고
			var userId = $(this).children('td:nth-child(1)').text();
			$("#userId").val(userId);
			
			//#frm을 이용하여 submit();
			$("#frm").submit();
		});
		
		// 첫번째 페이지의 사용자 정보를 요청
		//userPagingListAjax(1, 10); 
		userPagingListAjaxHtml(1, 10);
	});
	
	
	
	//얘는 데이터 응답을 html로 받는 경우이고요
	function userPagingListAjaxHtml(page, pageSize){
		$.ajax({
			url:"/user/pagingListAjaxHtml",
			method:"post",
			data: "page=" + page + "&pageSize=" + pageSize,
			success:function(data){
				
				var html = data.split("SEPERATORSEPERATORSEPERATOR");
				
				console.log(html[0]);
				console.log(html[1]);
				//console.log(data);
				
				$(".table tbody").html(html[0]);
				$(".pagination").html(html[1]);
			}
		});
	}
	
	
	
	//얘는 데이터 응답을 json으로 받는 경우에요
	function userPagingListAjax(page, pageSize){
		$.ajax({
			url:"/user/pagingListAjax",
			method:"post",
			data: "page=" + page + "&pageSize=" + pageSize,
			success:function(data){
				//console.log(data);
				
				var html = "";
				
				data.data.userList.forEach(function(user){
					//html 생성
					html += "<tr class='userTr' data-userid='" + user.userId + "'>";
					html += "<td class='userId'>" + user.userId + "</td>";
					html += "<td>" + user.name + "</td>";
					html += "<td>" + user.alias + "</td>";
					html += "<td> </td>";
					html += "</tr>"
				});
				
				
				//페이지네이션 생성
				var pHtml = "";
				var pageVO = data.pageVO;
				
				if(pageVO.page == 1){
					pHtml += "<li class='disabled'><span>«</span></li>";
				}else{
					pHtml += "<li><a href='javascript:userPagingListAjax("+ (pageVO.page - 1) + "," + pageVO.pageSize + ");'>«</a></li>"
				}
				
				//data라는 객체에 data라는 속성에 paginationSize
				
				for(var i = 1; i <= data.data.paginationSize; i++){
					if(pageVO.page == i){
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					}else{
						pHtml += "<li><a href='javascript:userPagingListAjax("+ i + "," + pageVO.pageSize + ");'>" + i + "</a></li>"
					}
				}
				
				if(pageVO.page >= data.data.paginationSize){
					pHtml += "<li class='disabled'><span>»</span></li>";
				}else{
					pHtml += "<li><a href='javascript:userPagingListAjax("+ (pageVO.page + 1) + "," + pageVO.pageSize + ");'>»</a></li>"
				}
				
				
				console.log("html :", html);
				console.log("pHtml :", pHtml);
				$(".table tbody").html(html);
				$(".pagination").html(pHtml);
			}
			
		});
	}
	
</script>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">User Paging List (ajax)</h2>
		<div class="table-responsive">
		
			<!-- 사용자 상세조회 : userId가 필요 -->
			<form action="${cp}/user/user" method="get" id="frm">
				<input type="hidden" id="userId" name="userId">
			</form>
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th>사용자 아이디(el)</th>
						<th>사용자 이름</th>
						<th>사용자 별명</th>
						<th>등록일시</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</div>

		<div class="btn_wrapper" style="float:right; width:100%; margin-bottom:20px">
			<a href="${cp}/user/form" class="btn btn-default pull-right" style="margin-left:5px">사용자 등록</a> 
			<a href="${cp}/user/userListExcel" class="btn btn-default pull-right" >EXCEL DOWNLOAD</a>
		</div>
		
		<div class="text-center">
			<ul class="pagination">
				
			</ul>
		</div>
	</div>
</div>