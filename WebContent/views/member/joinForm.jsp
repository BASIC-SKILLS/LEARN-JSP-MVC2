<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "common.RegExp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  <script type="text/javascript">
  //중복하기를 눌렀을 때 통과되어야만 submit이 되어야 한다.
  //중복하기 눌렀을 때 통과했는지 안했는지 체크하는 변수
  	var flag = false;
  
  	function initFlag() {
  		flag = false;
  	}
  
  	function checkId() {
  		//입력된 아이디 한 번 더 검사하고 아이디 중복확인을 한다
  		var id = $('#id')
  		if (!id.val()) {
  			alert('아이디를 입력해 주세요.');
  			id.focus();
  			return;
  		}
  		var regExpId = new RegExp("^[a-z0-9]{4,8}$","g");
  	  	if (regExpId.exec(id.val())==null) {
  	  		alert('아이디는 4~8자의 영문소문자, 숫자로 입력해 주세요.');
  	  		id.focus();
  	  		id.val(''); 
  	  		return; 
  	  	}
  		//Ajax방식으로 아이디 검사
  		//Ajax방식 : 화면이 안바뀌고 백앤드 갔다올 수 있다 실행결과 받아서 처리한다
  		$.ajax({
  			//백엔드로 보낼 주소 -경로설정
  			url : "/member/ajaxCheckId"
  			// 어떤 형태로 전송할 지
  			, type : "post"
  			// json은 key : value 로 이루어져있다, 데이터구분은 ,로 한다. 
  			, dataType : "json"
  			//실제 데이타
  			, data : {
  				mber_id : id.val()
  			}
  			//에러나면 이 함수를 실행한다
  			, error : function() {
  				alert('서버 통신 실패');
  			}
  			// data : result.jsp에서 {}사이에 있는 것들이 data이다.
  			, success : function(data) {
  				if (data.flag == 'Y') {
  					alert('사용할 수 있는 아이디입니다.');
  					flag = true;
  				} else {
  					alert('중복된 아이디입니다.')
  					//flag를 flase로 만든다
  					initFlag();
  				}
  			}
  		});
  	}
  
  
  	function validateCheck() { 
  		var id = $('#id')
  		var pwd = $('#pwd')
  		var confirmPwd = $('#confirmPwd')
  		var name = $('#name')
  		var age = $('#age')

  		
  		if (!id.val()) {
  			alert('아이디를 입력해 주세요.');
  			id.focus();
  			return;
  		}
  		
  		if (!pwd.val()) {
			alert('비밀번호를 입력해 주세요.');
			pwd.focus();
			return;
		}
  		
  		if (!confirmPwd.val()) {
			alert('비밀번호를 입력해 주세요.');
			confirmPwd.focus();
			return;
		}
  		
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
  		
  		
  		var regExpId = new RegExp("<%= RegExp.EXP_MEMBER_ID %>","g");
  	  	if (regExpId.exec(id.val())==null) {
  	  		alert('아이디는 4~8자의 영문소문자, 숫자로 입력해 주세요.');
  	  		id.focus();
  	  		id.val(''); 
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
	  	
	 if (!flag) {
		 alert("아이디 중복확인을 해 주세요.");
		 return;
	 }
  	  	
  	  //위를 다 통과하면 강제로 submit시킨다
  	  $('#joinForm').submit();
  	}
    </script>
</head>
<body>
<form action="/member/joinProc" method="post" id="joinForm">
<div>
아이디<input type="text" name="mber_id" id="id" oninput="initFlag()">
<button type="button" onclick="checkId()">중복확인</button>
</div>
<div>
비밀번호<input type="password" name="mber_pwd" id="pwd">
</div>
<div>
비밀번호 확인<input type="password" name="confirm_mber_pwd" id="confirmPwd">
</div>
<div>
이름<input type="text" name="mber_nm" id="name">
</div>
<div>
나이<input type="text" name="mber_age" id="age">
</div>
</form>
<button onclick="validateCheck()">가입</button>
<button onclick="location.href='/'">가입취소</button>
</body>
</html>