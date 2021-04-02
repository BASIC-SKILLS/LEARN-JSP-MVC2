
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

public class UpdateAction implements Action{
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
		forward.setPath("/views/member/updateForm.jsp");
		
		return forward;
	}

}