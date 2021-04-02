package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.RegExp;
import member.service.MemberService;
import member.vo.MemberVo;

import static common.RegExp.*;

public class UpdateProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 여부 확인
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
		
		String name = request.getParameter("mber_nm");
		String age = request.getParameter("mber_age");
		
		RegExp re = new RegExp();
		
		if(!re.validateCheck(TYPE_MEMBER_NAME, name)
			|| !re.validateCheck(TYPE_MEMBER_AGE, age)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		//이제 vo하나 만들어서 insert한다
		MemberVo vo = new MemberVo();
		vo.setMber_sq(Integer.parseInt(sq));
		vo.setMber_nm(name);
		vo.setMber_age(Integer.parseInt(age));
		
		MemberService svc = new MemberService();
		//업데이트 성공여부를 확인해야 한다
		boolean isSuccess = svc.updateMemberInfo(vo);
		if(!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 수정에 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member/detail");
		forward.setRedirect(true);
		return forward;
	}
}
