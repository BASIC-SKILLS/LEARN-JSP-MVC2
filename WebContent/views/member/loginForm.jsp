<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script
src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  <script type="text/javascript">
function validateCheck() { 
  		var id = $('#user_id')
  		var pwd = $('#user_pwd')
  		
  		if (!id.val()) {
  			alert('아이디가 입력되지 않았습니다.');
  			id.focus();
  			return false;
  		}
  		
  		if (!pwd.val()) {
  			alert('비밀번호가 입력되지 않았습니다.');
  			id.focus();
  			return false;
  		}
  		
  		return true;
}
</script>
</head>
<body>
<form action="/member/loginProc" method="post" onsubmit="return validatecheck">
<div>아이디<input type="text" id="id" name="mber_id"></div>
<div>비밀번호<input type="password" id="password" name="mber_pwd"></div>

<div>
<input type="submit" value="로그인">
<input type="button" value="취소" onclick="location.href='/'">
</div>

<a href="/member/join">회원가입</a>
<a href="/member/searchId">아이디찾기</a>
<a href="/member/searchPassword">비밀번호찾기</a>
</form>
</body>
</html>