<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


name <input type="text" value="${userVO.name}" readonly> 
alias <input type="text" value="${userVO.alias}" readonly>
birth <input type="text" value="<fmt:formatDate value="${userVO.birth}" pattern="yyyy-MM-dd"/>" readonly>
