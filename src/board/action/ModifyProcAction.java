package board.action;

import static common.RegExp.TYPE_NUMBER;
import static common.RegExp.TYPE_SUB;

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

public class ModifyProcAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인여부 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemeberSq(session);
		if (mber_sq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 하세요!');location.href='/member/login';</script>");
			out.close();
			return null;
		}

		// 데이터로드(페이지번호, sf,sk,sort,sq)
		String pn = request.getParameter("pn");
		String sf = request.getParameter("sf");
		String sk = request.getParameter("sk");
		String sort = request.getParameter("sort");
		String bd_sq = request.getParameter("sq");
		
		//사용자가 작성한 데이터 로드
		String sub = request.getParameter("sub");
		String cntnt = request.getParameter("cntnt");

		// 페이지번호, sf,sk,sort는 null검사,bd_sq null,빈값, 숫자, 0보다 큰지 검사

		if (pn == null || sf == null || sk == null || sort == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		RegExp re = new RegExp();
		if (bd_sq == null || bd_sq.equals("") || !re.validateCheck(TYPE_NUMBER, bd_sq)
				|| (Integer.parseInt(bd_sq)) < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		// sq를 가지고 데이터베이스에서 글 작성자 bd_mber_sq를 불러온다
		BoardService svc = new BoardService();
		BoardVo vo = svc.getArticleDetail(Integer.parseInt(bd_sq));
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 정보를 불러오는데 실패하였습니다.');histor;y.back();</script>");
			out.close();
			return null;
		}

		// mber_sq와 bd_mber_sq가 같은지 비교
		if (Integer.parseInt(mber_sq) != vo.getBd_mber_sq()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		// 사용자가 작성한 데이터 검사
		if (sub == null || sub.equals("") || !re.validateCheck(TYPE_SUB, sub) || cntnt == null || cntnt.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		
		// 데이터 업데이트
		BoardVo modify_vo = new BoardVo();
		modify_vo.setBd_sub(sub);
		modify_vo.setBd_cntnt(cntnt);
		modify_vo.setBd_sq(Integer.parseInt(bd_sq));

		boolean isSuccess = svc.modifyArticle(modify_vo);
		if (!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시물을 수정하는데 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}

		// 경로설정-리다이렉트방식
		ActionForward forward = new ActionForward();
		forward.setPath("/board/detail?pn=" + pn + "&sf=" + sf + "&sk=" + sk + "&sort=" + sort + "&sq=" + bd_sq);
		forward.setRedirect(true);
		return forward;

	}

}



