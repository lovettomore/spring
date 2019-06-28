<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<script>
	$(document).ready(function(){
		
// 		var languege = "${lang}";
// 		languege == "" ? $("#lang").val("ko") : $("#lang").val("${lang}")
				
		$("#lang").val("${lang}");
		
		$("#lang").on("change", function(){
			$("#frm").submit();
		});
		
	});
</script>

::::: ${lang} :::::<br><br>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">english</option>
		<option value="jp">日本語</option>
	</select>
</form>
<br>
GREETING : <spring:message code="GREETING"/><br>
VISITOR : <spring:message code="VISITOR">
		  	<spring:argument value="brown"></spring:argument>
		  </spring:message>
