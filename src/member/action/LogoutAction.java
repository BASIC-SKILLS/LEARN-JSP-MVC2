package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Action;
import common.ActionForward;
import common.LoginManager;

public class LogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		if(lm.getMemeberSq(session) == null) {
			session.invalidate();
		} else {
			lm.removeSession(lm.getMemeberSq(session));
		}
		
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}

}