<%@page import="member.vo.MemberVo"%>
<%@page import="common.RegExp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  <script type="text/javascript">
function validateCheck() { 
 
  		var pwd = $('#pwd')
  		var confirmPwd = $('#confirmPwd')

  		
  		if (!pwd.val()) {
			alert('비밀번호를 입력해 주세요.');
			pwd.focus();
			return;
		}
  		
  		if (!confirmPwd.val()) {
			alert('비밀번호확인을 입력해 주세요.');
			confirmPwd.focus();
			return;
		}
  		
  		
  	  	
  	  var regExpPwd = new RegExp("<%= RegExp.EXP_MEMBER_PWD %>","g");
	  	if (regExpPwd.exec(pwd.val())==null) {
	  		alert('비밀번호는 4~12자의 영문(대문자,소문자), 숫자, 특수기호(!@#$%^&*)로 입력해 주세요.');
	  		pwd.focus();
	  		pwd.val(''); 
	  		return; 
	  	}
	  	
	  	if(pwd.val() != confirmPwd.val()) {
	  		alert('비밀번호와 일치하지 않습니다');
	  		confirlmPwd.focus();
	  		confirlmPwd.val(''); 
	  		return; 
	  	}
  	  	
  	  //위를 다 통과하면 강제로 submit시킨다
  	  $('#updateForm').submit();
  	}
</script>
</head>
<body>
<form action="/member/updatePasswordProc" method="post" id="updateForm">
<div>
새 비밀번호 <input type="password" id="pwd" name="pwd">
</div>
<div>
비밀번호 확인 <input type="password" id="confirmPwd" name="confirmPwd">
</div>
<div>
 <input type="hidden" name="id" value="<%=vo.getMber_id()%>">
  <input type="hidden" name="name" value="<%=vo.getMber_nm()%>">
   <input type="hidden" name="age" value="<%=vo.getMber_age()%>">
</div>
<button onclick="validateCheck()">수정</button>
<button onclick="location.href='member/login'">취소</button>
</form>
</body>
</html>