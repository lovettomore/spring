<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
   <div class="container-fluid">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
            aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span> <span
               class="icon-bar"></span> <span class="icon-bar"></span> <spanclass="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="${cp}/main.jsp"> JSP/SPRING 
         
         <!-- 값이 들어오지 않았을때 굳이 if문 처리를 하지 않아도 에러가 나지 않아 유동적으로 쓸 수 있음
         	el : ${USER_INFO.name}
         -->
         
         <!-- 사용자가 로그인하지 않고 메인 화면으로 직접 접속 했을 경우 () -->
      	 <span>
         <c:choose>
         	<c:when test="${USER_INFO.userId == null}">
         		접속하지 않은 사용자 입니다.
         	</c:when>
         	<c:otherwise>
         		${USER_INFO.name}
         	</c:otherwise>
         </c:choose>
         </span>
<%--          <c:set var="userName" value="${USER_INFO.name}"/> --%>
<%--          <c:if test="${userName == null}"> --%>
<%--          	<c:set var="userName" value="[]"/> --%>
<%--          </c:if> --%>
         
         
<%--          <c:if test="${USER_INFO.userId == null}"> --%>
<!--          	접속하지 않은 사용자 입니다. -->
<%--          </c:if> --%>
         
         </a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
         <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="${cp}/logout">Logout</a></li>
         </ul>
         <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
         </form>
      </div>
   </div>
</nav>