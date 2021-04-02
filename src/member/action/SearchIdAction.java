package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;

public class SearchIdAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//바로 폼으로 이동한다.
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/searchIdForm.jsp");
		
		return forward;
	}

}