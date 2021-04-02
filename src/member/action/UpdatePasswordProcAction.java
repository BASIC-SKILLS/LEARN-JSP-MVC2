package member.action;




import static common.RegExp.TYPE_MEMBER_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.Action;
import common.ActionForward;
import common.BCrypt;

import common.RegExp;
import member.service.MemberService;

import member.vo.MemberVo;

public class UpdatePasswordProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


	
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");

	
		RegExp re = new RegExp();
		
		if(!re.validateCheck(TYPE_MEMBER_PWD, pwd)
				|| !pwd.equals(confirmPwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		
		MemberVo vo = new MemberVo();
		vo.setMber_id(id);
		vo.setMber_pwd(BCrypt.hashpw(pwd,BCrypt.gensalt(12)));
		vo.setMber_nm(name);
		vo.setMber_age(Integer.parseInt(age));
		
		
		
		//비밀번호 업데이츠
		MemberService svc = new MemberService();
		boolean isSuccess = svc.updateMemberPassword(vo);
		if (! isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 변경하는데 실패하였습니다');history.back();</script>");
			out.close();
			return null;
		}
		
		
		//경로설정(/member/detail)
		ActionForward forward = new ActionForward();
		forward.setPath("/member/login");
		forward.setRedirect(true);
		return forward;

	
	}
}
