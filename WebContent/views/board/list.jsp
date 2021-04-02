<%@page import="common.PageInfo"%>
<%@page import="board.vo.BoardVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	String pn = request.getParameter("pn");
	String sk = request.getParameter("sk");
	String sf = request.getParameter("sf");
	String sort = request.getParameter("sort");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$('#sort').val('<%=sort%>').prop('selected', true);
		var sf1 = '<%=sf%>';
			if (sf1 == '') {
				$('#sf').val('sub').prop('selected',true);
			} else {
				$('#sf').val('<%=sf%>').prop('selected',true);
			}
	});



	function validateCheck() {
			var sk = $('#sk');
			if(!sk.val()) {
				alert('검색키워드를 입력해주세요');
				sk.focus();
				return;
			}
			
	
			
			var regExp = new RegExp("^[0-9a-zA-Z가-힣 ]{2,10}$","g");
			if (regExp.exec(sk.val())==null) {
				alert('검색키워드는 2~10자의 영문, 숫자, 한글로 입력해주세요.');
				sk.focus();
				sk.val('');
				return;
			}
			
			location.href="/board/list?pn=1&sf=" + $('#sf option:selected').val() + "&sk=" + sk.val() + "&sort=desc";
		
	}
	
	
	
	function sortArticle() {
		location.href="/board/list?pn=1&sf=<%=sf%>&sk=<%=sk%>&sort=" + $('#sort option:selected').val() ;
	}
</script>

</head>
<body>

총 <%=pi.getTotalArticleCount() %> 개의 글이 있습니다. 


	<div>
		<select id="sort" onchange="sortArticle()">
			<option value="desc">최신순</option>
			<option value="asc">오래된순</option>
		</select>
		<select name="sf" id="sf">
			<option value="sub">제목</option>
			<option value="subttl">제목 + 내용</option>
		</select>
		<input type="text" id="sk" name="sk" value="<%=sk %>" placeholder="2자 이상의 검색키워드">
		<input type="button" value="검색" onclick="validateCheck()">
		<button onclick="location.href='/board/list?pn=1&sf=&sk=&sort=desc'">초기화</button>
	</div>




<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일시</td>
		<td>조회수</td>
	</tr>
<%for (int i=0; i<list.size(); i++) {%>
	<tr onclick="location.href='/board/detail?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>&sq=<%=list.get(i).getBd_sq() %>'">
		<td><%=list.get(i).getBd_sq() %></td>
		<td><%=list.get(i).getBd_sub() %></td>
		<td><%=list.get(i).getMber_id() %></td>
		<td><%=list.get(i).getBd_dttm() %></td>
		<td><%=list.get(i).getBd_hit() %></td>
	</tr>
<%} %>
</table>
<a href="/board/list?pn=1&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>">처음</a>
<a href="/board/list?pn=<%=pi.getPrePageNumber() %>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>">이전</a>

<%for (int i=pi.getStartPageNumber(); i<=pi.getEndPageNumber(); i++) {%>
<a href="/board/list?pn=<%=i %>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>"><%=i %></a>
<%} %>

<a href="/board/list?pn=<%=pi.getNextPageNumber() %>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>">다음</a>
<a href="/board/list?pn=<%=pi.getTotalPageCount() %>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>">끝</a>
<button onclick="location.href='/board/write?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'">글쓰기</button>
</body>
</html>