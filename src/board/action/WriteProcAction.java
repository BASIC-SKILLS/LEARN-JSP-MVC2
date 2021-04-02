package board.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.LoginManager;

import common.RegExp;

import static common.RegExp.TYPE_SUB;


public class WriteProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sq = lm.getMemeberSq(session);
		if(sq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
			out.close();
			return null;
		} 
		
		String sub = request.getParameter("sub");
		String cntnt = request.getParameter("cntnt");
		
		//사용자가 작성한 데이터 로드 및 검사
		RegExp re = new RegExp();
		if (sub == null || sub.equals("") || !re.validateCheck(TYPE_SUB, sub) 
				||cntnt == null || cntnt.equals("") ) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		BoardVo vo = new BoardVo();
		vo.setBd_mber_sq(Integer.parseInt(sq));
		vo.setBd_sub(sub);
		vo.setBd_cntnt(cntnt);
		
		
		//vo데이터 데이터베이스 저장
		BoardService svc = new BoardService();
		boolean isSuccess = svc.insertArticle(vo);
		
		if (!isSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('게시물을 저장하는데 실패하였습니다.');history.back();</script>");
				out.close();
				return null;
			}
		
		//경로설정 - 리스트로 돌아가야함 - 리다이렉트이동
		ActionForward forward = new ActionForward();
		forward.setPath("/board/list?pn=1&sf=&sk=&sort=desc");
		forward.setRedirect(true);
		
		return forward;
		
	
		
	}
	
}













