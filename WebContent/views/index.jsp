<%@page import="common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String sq = lm.getMemeberSq(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if (sq==null) {%>
	<a href="/member/join">회원가입</a>
	<a href="/member/login">로그인</a>
<%} else {%>
	<a href="/member/detail">회원정보</a>
	<a href="/member/logout">로그아웃</a>
<%} %>

<a href="/board/list?pn=1&sf=&sk=&sort=desc">게시판</a>
</body>
</html>