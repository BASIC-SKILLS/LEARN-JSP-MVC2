<%@page import="common.LoginManager"%>
<%@page import="board.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo vo = (BoardVo) request.getAttribute("vo");
	String pn = request.getParameter("pn");
	String sf = request.getParameter("sf");
	String sk = request.getParameter("sk");
	String sort = request.getParameter("sort");
	LoginManager lm = LoginManager.getInstance();
	String sq = lm.getMemeberSq(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>

<script type="text/javascript">
	function modifyArticle() {
		if (confirm("글을 수정하시겠습니까?")) {
			location.href="/board/modify?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>&sq=<%=vo.getBd_sq()%>";
		}
	}
	
	function deleteArticle() {
		if (confirm("글을 삭제하시겠습니까?")) {
			location.href="/board/delete?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>&sq=<%=vo.getBd_sq()%>";
		}
	}

</script>

</head>
<body>
<div>번호 : <%=vo.getBd_sq() %></div>

<div>제목 : <%=vo.getBd_sub() %></div>

<div>내용 : <%=vo.getBd_cntnt() %></div>

<div>작성자 : <%=vo.getMber_id() %></div>

<div>작성일자 : <%=vo.getBd_dttm() %></div>

<div>조회수 : <%=vo.getBd_hit() %></div>

<%if (sq != null && vo.getBd_mber_sq() == Integer.parseInt(sq)) {%>
<button onclick="modifyArticle()">수정</button>
<button onclick="deleteArticle()">삭제</button>
<button onclick="location.href='/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'">목록</button>
<%} else {%>
<button onclick="location.href=='/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'">목록</button>
<%} %>
</body>
</html>

