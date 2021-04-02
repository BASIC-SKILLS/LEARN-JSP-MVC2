package member.action;




import static common.RegExp.TYPE_MEMBER_PWD;

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

import member.vo.MemberVo;

public class UpdatePasswordAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sq = lm.getMemeberSq(session);
		if (sq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
			out.close();
			return null;
		}

		// 데이터 가져옴(현재비밀번호, 비밀번호, 비밀번호확인)
		String nowPwd = request.getParameter("nowPwd");
		String pwd = request.getParameter("mber_pwd");
		String confirmPwd = request.getParameter("confirm_mber_pwd");

		// 데이터 검사
		RegExp re = new RegExp();
		
		if(nowPwd == null || nowPwd.equals("")
				|| !re.validateCheck(TYPE_MEMBER_PWD, pwd)
				|| !pwd.equals(confirmPwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력한 정보를 확인해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		//sq로 회원 정보 로드(from DB)
		MemberService svc = new MemberService();
		MemberVo vo = svc.getMemberDetail(Integer.parseInt(sq));
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 불러오는데 실패하였습니다');history.back();</script>");
			out.close();
			return null;
		}
		
		
		//현재 비밀번화 회원 정보의 비밀번호 확인
			// 암호화된 비밀번호와 입력된 비밀번호를 비교해서 같은지 다른지 알려준다
		if (!BCrypt.checkpw(nowPwd, vo.getMber_pwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('현재 비밀번호를 확인해주세요');history.back();</script>");
			out.close();
			return null;
		}
		
		
		//sq, 변경할 비밀번호 암호화 해서 데이터베이스에 저장
		
			//MemberVo객체에 데이터 저장
		MemberVo vo1 = new MemberVo();
		vo1.setMber_sq(Integer.parseInt(sq));
		vo1.setMber_pwd(BCrypt.hashpw(pwd,BCrypt.gensalt(12)));
		
		
			//데이터 베이스에 비밀번호 업데이트
		boolean isSuccess = svc.updatePassword(vo1);
		if(!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 가입에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		
		//경로설정(/member/detail)
		ActionForward forward = new ActionForward();
		forward.setPath("/member/detail");
		forward.setRedirect(true);
		return forward;

	
	}
}
