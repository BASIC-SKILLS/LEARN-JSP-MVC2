package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import common.BCrypt;
import common.RegExp;
import member.service.MemberService;
import member.vo.MemberVo;

import static common.RegExp.*;

public class JoinProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("mber_id");
		String pwd = request.getParameter("mber_pwd");
		String confirmPwd = request.getParameter("confirm_mber_pwd");
		String name = request.getParameter("mber_nm");
		String age = request.getParameter("mber_age");
		
		RegExp re = new RegExp();
		
		//알고리즘만 담당하고 validateCheck하는 책임은 클래스에 떠넘긴 것이다
		if(!re.validateCheck(TYPE_MEMBER_ID, id)
				|| !re.validateCheck(TYPE_MEMBER_PWD, pwd)
				|| !re.validateCheck(TYPE_MEMBER_NAME, name)
				|| !re.validateCheck(TYPE_MEMBER_AGE, age)
				|| !pwd.equals(confirmPwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		//이제 vo하나 만들어서 insert한다
		MemberVo vo = new MemberVo();
		vo.setMber_id(id);
		vo.setMber_pwd(BCrypt.hashpw(pwd,BCrypt.gensalt(12)));
		vo.setMber_nm(name);
		vo.setMber_age(Integer.parseInt(age));
		
		MemberService svc = new MemberService();
		int count = svc.getCheckId(id);
		if (count > 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('중복된 아이디입니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		boolean isSuccess = svc.registerMember(vo);
		if(!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 가입에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
