<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	cookie/index.jsp
	<hr>
	<a href="result">쿠키 확인</a>
	<a href="result02">쿠키 확인02</a>
	<%--
		쿠키 생성
		Cookie cookie = new Cookie("key", "value");
		cookie.setMaxAge(5);
		res.addCookie(cookie);
	--%>
	
	<%--
		Cookie[] arrCo = request.getCookies();
	 --%>
	
</body>
</html>