package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;

public class JoinAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//setpath한 쪽으로 이동한다.
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/joinForm.jsp");
		
		return forward;
	}

}