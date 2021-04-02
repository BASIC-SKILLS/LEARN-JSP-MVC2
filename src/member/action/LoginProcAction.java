package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.BCrypt;
import common.LoginManager;
import common.RegExp;
import member.service.MemberService;
import member.vo.MemberHistoryVo;
import member.vo.MemberVo;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("mber_id");
		String pwd = request.getParameter("mber_pwd");

		RegExp re = new RegExp();

		if (!re.isNotEmpty(id) || !re.isNotEmpty(pwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 또는 비밀번호를 입력해주세요.');history.back();</script>");
			out.close();
			return null;
		}

		MemberService svc = new MemberService();
		MemberVo vo = svc.getMemberInfo(id);
		// 암호화된 비밀번호와 입력된 비밀번호를 비교해서 같은지 다른지 알려준다
		if (vo == null || !BCrypt.checkpw(pwd, vo.getMber_pwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 또는 비밀번호를 확인해주세요.');history.back();</script>");
			out.close();
			return null;
		}

		// 로그인 처리 (로그인 매니저 클래스를 통해 세션에 추가)
		LoginManager lm = LoginManager.getInstance();
		// 패스워드 초기화, vo에 패스워드 값 안들어가고 아이디 값만 들어간다
		vo.setMber_pwd(null);
		lm.setSession(request.getSession(), vo.getMber_sq());

		// 히스토리 저장
		MemberHistoryVo mhVo = new MemberHistoryVo();
		mhVo.setHist_mber_sq(vo.getMber_sq());
		mhVo.setHist_gb(true);

		svc.insertMemberHistory(mhVo);
		
		HttpSession session = request.getSession();
		String ref = (String) session.getAttribute("ref");

		ActionForward forward = new ActionForward();
		if(ref == null) {
			forward.setPath("/");
		} else {
			forward.setPath(ref);
			session.removeAttribute("ref");
		}
		
		forward.setRedirect(true);
		return forward;
	}
}
