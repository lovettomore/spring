<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Main</title>
	<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
</head>

<body>
	<div class="container-fluid">
		<!-- sidebar  -->
		<tiles:insertAttribute name="sidebar"/>
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!-- header -->
				<tiles:insertAttribute name="header"/>
				
				<!-- body container -->
				<tiles:insertAttribute name="body"/>
				
			</div>
		</div>
	</div>
	
</body>
</html>