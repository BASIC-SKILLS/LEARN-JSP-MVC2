<%@page import="member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<div>
	회원번호 : <%=vo.getMber_sq() %>
</div>
<div>
	아이디 : <%=vo.getMber_id() %>
</div>
<div>
	이름 : <%=vo.getMber_nm() %>
</div>
<div>
	나이 : <%=vo.getMber_age()%>
</div>
<div>
	가입일시 : <%=vo.getMber_dttm() %>
</div>
<a href="/member/update">회원정보수정</a>
<a href="/member/leave">회원탈퇴</a>
<a href="/member/password">비밀번호 변경</a>
</body>
</html>