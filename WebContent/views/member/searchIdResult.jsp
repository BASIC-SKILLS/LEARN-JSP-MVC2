<%@page import="member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
</head>
<body>
<div>
	아이디 : <%=id %>
</div>
<a href="/memeber/login">로그인</a>
<a href="/member/searchPassword">비밀번호 찾기</a>
<a href="/">홈으로</a>
</body>
</html>