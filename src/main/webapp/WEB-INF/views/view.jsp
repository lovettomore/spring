<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/main/process" method="post">
		<p><label>userId</label><input type="text" name="userId" value="brown"></p>
		<p><label>userId</label><input type="text" name="userId" value="sally"></p>
		
		<p><label>name</label><input type="text" name="name" value="브라운"></p>
		<p><label>name</label><input type="text" name="name" value="샐리"></p>
		
		<p><label>addr1</label><input type="text" name="addr[0].addr1" value="대전시 중구"></p>
		<p><label>addr2</label><input type="text" name="addr[0].addr1" value="중앙로 56"></p>
		
		<p><label>addr1</label><input type="text" name="addr[1].addr1" value="대전시 중구"></p>
		<p><label>addr2</label><input type="text" name="addr[1].addr1" value="중구춍"></p>
		<p><input type="submit" value="전송"></p>
	</form>
</body>
</html>