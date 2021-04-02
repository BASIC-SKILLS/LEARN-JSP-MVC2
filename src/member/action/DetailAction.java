//로그인 되어 있는 mber_sq 세션에서 불러오기
//mber_sq는 String 이므로 int형으로 변환
//mber_sq로 데이터베이스에서 회원 정보 로드
//attribute에 저장
//view 이동
//view에서는 attribute에서 데이터로드하여 정보 보여줌 

package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import member.service.MemberService;
import member.vo.MemberVo;

public class DetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인검사
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sq = lm.getMemeberSq(session);
		if(sq == null) {
			String requestURI = request.getRequestURI(); 
			session.setAttribute("ref", requestURI);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
			out.close();
			return null;
		} 
		
		MemberService svc = new MemberService();
		
		MemberVo vo= svc.getMemberDetail(Integer.parseInt(sq));
		if(vo==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 로드하는데 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		request.setAttribute("vo", vo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/detail.jsp");
		
		return forward;
	}

}