<%@page import="common.RegExp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pn = request.getParameter("pn");
	String sk = request.getParameter("sk");
	String sf = request.getParameter("sf");
	String sort = request.getParameter("sort");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  <script type="text/javascript">

  	function validateCheck() { 
  		var sub = $('#sub');
  		var cntnt = $('#cntnt');


  		
  		if (!sub.val()) {
  			alert('제목을 입력해 주세요.');
  			id.focus();
  			return;
  		}
  		
  		if (!cntnt.val()) {
			alert('내용을 입력해 주세요.');
			pwd.focus();
			return;
		}
 
  		
  		
  		var regExpSub = new RegExp("<%= RegExp.EXP_SUB %>","g");
  	  	if (regExpSub.exec(sub.val())==null) {
  	  		alert('제목은 2~50자로 입력해 주세요.');
  	  		sub.focus();
  	  		sub.val(''); 
  	  		return; 
  	  	}
  	  	
  	  
  	  //위를 다 통과하면 강제로 submit시킨다
  	  $('#writeForm').submit();
  	}
    </script>
</head>
<body>
	<form action="/board/writeProc" method="post" id="writeForm">
		제목<input type="text" name="sub" id="sub">
		내용<textarea rows="10" cols="10" name = "cntnt" id ="cntnt"></textarea>
	</form>
	
	<button onclick="validateCheck()">저장</button>
	<button onclick="location.href='/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'">취소</button>
</body>
</html>