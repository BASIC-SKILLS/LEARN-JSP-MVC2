package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;

import common.RegExp;
import member.service.MemberService;
import member.vo.MemberVo;

import static common.RegExp.TYPE_MEMBER_AGE;

public class SearchPasswordProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 데이터 로드(아이디, 이름, 나이)
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		// 데이터 검사(null여부, ""여부)
		RegExp re = new RegExp();
		// 이름은 빈값만 검사하고 나이는 숫자인지까지 검사한다
		if (!re.isNotEmpty(id) || !re.isNotEmpty(name) || !re.validateCheck(TYPE_MEMBER_AGE, age)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요');history.back();</script>");
			out.close();
			return null;
		}

		// 데이터로 회원이 존재하는지 로드

		MemberVo vo = new MemberVo();
		vo.setMber_id(id);
		vo.setMber_nm(name);
		vo.setMber_age(Integer.parseInt(age));

		MemberService svc = new MemberService();
		int count = svc.getMemberCount(vo);
		if (count < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보에 해당하는 정보가 없습니다.');history.back();</script>");
			out.close();
			return null;

		}
		
		
		///views/member/searchPasswordProc.jsp로 넘기기 위해서
		request.setAttribute("vo", vo);
		
		// searchPasswordProc.jsp로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/searchPasswordProc.jsp");
		return forward;
	}
}
