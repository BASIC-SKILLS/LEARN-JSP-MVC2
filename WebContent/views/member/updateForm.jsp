<%@page import="common.RegExp"%>
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
<title>회원정보 수정</title>
</head>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  <script type="text/javascript">
  
  
  	function validateCheck() { 
  		
  		var name = $('#name')
  		var age = $('#age')
  		
  		if (!name.val()) {
			alert('이름을 입력해 주세요.');
			name.focus();
			return;
		}
  		
  		if (!age.val()) {
			alert('나이를 입력해 주세요.');
			age.focus();
			return;
		}
  		

	  	
	  	var regExpName = new RegExp("<%= RegExp.EXP_MEMBER_NAME %>","g");
  	  	if (regExpName.exec(name.val())==null) {
  	  		alert('이름은 2~5자의 한글로 입력해 주세요.');
  	  		name.focus();
  	  		name.val(''); 
  	  		return; 
  	  	}
  	  	
  	  var regExpAge = new RegExp("<%= RegExp.EXP_MEMBER_AGE %>","g");
	  	if (regExpAge.exec(age.val())==null) {
	  		alert('나이는 3자리 이내의 숫자로 입력해주세요.');
	  		age.focus();
	  		age.val(''); 
	  		return; 
	  	}
	  
  	  	
  	  $('#updateForm').submit();
  	}
    </script>
<body>
<form action="/member/updateProc" method="post" id="updateForm">
<div>
	아이디 : <%=vo.getMber_id() %>
</div>
<div>
	이름 : <input type="text" id="name" name="mber_nm" value="<%=vo.getMber_nm() %>">
</div>
<div>
	나이 : <input type="text" id="age" name="mber_age" value="<%=vo.getMber_age() %>">
</div>
</form>
<button onclick="validateCheck()">수정</button>
<button onclick="location.href='/member/detail'">취소</button>
</body>
</html>