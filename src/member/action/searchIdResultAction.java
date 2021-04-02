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

public class searchIdResultAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		// 데이터 로드(이름, 나이)
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		// 데이터 검사(null여부, ""여부)
		RegExp re = new RegExp();
			//이름은 빈값만 검사하고 나이는 숫자인지까지 검사한다
		if(!re.isNotEmpty(name)|| !re.validateCheck(TYPE_MEMBER_AGE, age)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요');history.back();</script>");
			out.close();
			return null;
		}
	
		
		
		//데이터로 회원정보 로드 
			// Java Bean에 데이터 세팅
		MemberVo vo = new MemberVo();
		vo.setMber_nm(name);
		vo.setMber_age(Integer.parseInt(age));
			// vo에 해당하는 데이터 로드
		MemberService svc = new MemberService();
		String id = svc.getMemberId(vo);
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보에 해당하는 정보가 없습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		//회원정보의 아이디 attribute에 저장 ( setAttribut )
		request.setAttribute("id", id);
	
		
		
		//searchIdResult.jsp로 이동하여 아이디 보여주기 (디스 패치)
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/searchIdResult.jsp");
		return forward;

	
	}
}
