package board.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.PageInfo;
import common.RegExp;

import static common.RegExp.*;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//페이지 번호 로드
		String pn = request.getParameter("pn");
		RegExp re = new RegExp();
		//페이지 숫자가 0보다 큰지를 여기서 검사한다.
		if (!re.validateCheck(TYPE_NUMBER, pn) || Integer.parseInt(pn) < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		// 검색 키워드 처리 
		String sf = request.getParameter("sf");
		String sk = request.getParameter("sk");
		if (sf == null || sk == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		if (!sk.equals("") && !re.validateCheck(TYPE_SEARCH, sk)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('검색키워드는 2~10자의 영문,숫자,한글로 입력해주세요.');history.back();</script>");
			out.close();
			return null;
		}
	
		
		
		//정렬처리
		String sort = request.getParameter("sort");
		if(sort == null || sort.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		

		String query = makeQuery(sf,sk,sort);
	
		
		BoardService svc = new BoardService();
		int totalArticleCount = svc.getTotalArticleCount(query);
		
		
		PageInfo pi = new PageInfo(Integer.parseInt(pn), totalArticleCount);
		
		
		// ki_board_tb로부터 리스트 로드
		ArrayList<BoardVo> list = svc.getBoardList(pi, query);
		
		// attribute에 리스트 저장
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		// 경로 제어
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/list.jsp");
		return forward;
		
	}
	
		//makeQuery 메소드 만들기
		private String makeQuery(String sf, String sk, String sort) {
			//검색키워드 쿼리
			String query = "";
			//sf, sk 데이터를 이용해 쿼리 추가 
			if (!sk.equals("")) {
				sk = "'%" + sk + "%'";
				if (sf.equals("sub")) {
					//제목으로 검색 쿼리
					query = " where kbt.bd_sub like " + sk;
				} else if (sf.equals("subttl")) {
					//제목+내용으로 검색 쿼리 
					query = " where kbt.bd_sub like " + sk + " or kbt.bd_cntnt like " + sk;
				}
			}
			
			
			//정렬
			if (sort.equals("desc")) {
				query += " order by kbt.bd_sq desc";
			} else if (sort.equals("asc")) {
				query += " order by kbt.bd_sq asc";
			} else {
				query += " order by kbt.bd_sq desc";
			}
			
			
			return query;
			
			
			
		}
	
}













