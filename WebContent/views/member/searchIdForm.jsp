<%@page import="common.RegExp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script
src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
 <script type="text/javascript">
function validateCheck() { 
	
  		var name = $('#name')
  		var age = $('#age')
  		
  		
  		if (!name.val()) {
  			alert('이름이 입력되지 않았습니다.');
  			name.focus();
  			return false;
  		}
  		
  		if (!age.val()) {
  			alert('나이가 입력되지 않았습니다.');
  			age.focus();
  			return false;
  		}
  		
  		var regExpAge = new RegExp("<%= RegExp.EXP_MEMBER_AGE %>","g");
	  	if (regExpAge.exec(age.val())==null) {
	  		alert('나이는 3자리 이내의 숫자로 입력해주세요.');
	  		age.focus();
	  		age.val(''); 
	  		return; 
	  	}
  		
  		$('#searchIdForm').submit();
 
}
</script>
</head>
<body>
<form action="/member/searchIdesult" method="post" id="searchIdForm">
<div>
이름 : <input type="text" id="name" name="name">
</div>
<div>
나이 : <input type="text" id="age" name="age">
</div>
</form>
<button onclick="validateCheck()">아이디 찾기</button>
<button onclick="location.href='/member/login'">취소</button>
</body>
</html>