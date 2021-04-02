package board.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import common.Action;
import common.ActionForward;
import common.LoginManager;



public class WriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 확인 
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
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/writeForm.jsp");
		return forward;
	}
	
}













