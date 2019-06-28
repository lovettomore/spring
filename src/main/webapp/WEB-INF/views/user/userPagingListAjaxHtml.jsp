<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				
<%-- 클릭했을 때 사용자가 누군지 키값을 아는게 좋을거 같아요. --%>
<c:forEach items="${data.userList}" var="user" varStatus="status">
	<tr class="userTr" data-userid="${user.userId}">
		<td>${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.alias}</td>
		<td></td>
	</tr>
</c:forEach>


SEPERATORSEPERATORSEPERATOR


<c:choose>
	<c:when test="${pageVO.page == 1}">
		<li class="disabled"><span>«</span></li>
	</c:when>
	<c:otherwise>
		<li>
			<a href="javascript:userPagingListAjaxHtml(${pageVO.page-1}&pageSize=${pageVO.pageSize});">«</a>
		</li>
	</c:otherwise>
</c:choose>

<!-- 
	내가 현재 어떤 페이지에 있는지 어떻게 알 수 있지?
	pageVO에 있어요. page와 pageSize를 이용해서 pageVO를 만들어용.
-->
<c:forEach begin="1" end="${data.paginationSize+1}" step="1" var="i">
	<c:choose>
		<c:when test="${pageVO.page == i}">
			<li class="active"><span>${i}</span></li>
		</c:when>
		<c:otherwise>
			<li>
				<a href="javascript:userPagingListAjaxHtml(${i}, ${pageVO.pageSize});">${i}</a>
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
			<a href="javascript:userPagingListAjaxHtml(${pageVO.page+1}&pageSize=${pageVO.pageSize}">»</a>
		</li>
	</c:otherwise>
</c:choose>
