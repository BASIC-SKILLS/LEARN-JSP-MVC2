package common;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import member.vo.MemberVo;

//로그인 관리해주는 클래스
//HttpSessionBindingListener - 세션에 데이터 추가될 때 파괴될 때 감시하는 것 -그중에서도 로그인에만 반응하게 만들거다
public class LoginManager implements HttpSessionBindingListener{
	private static Hashtable loginUsers = new Hashtable();
	
	//싱글톤으로 구현
	private LoginManager() {
	}
	
	public static LoginManager getInstance() {
		return LoginManager.LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		private static final LoginManager INSTANCE = new LoginManager();
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		loginUsers.put(event.getSession(), event.getName());
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUsers.remove(event.getSession());
	}
	
	public void setSession(HttpSession session, int sq) {
		session.setAttribute(Integer.toString(sq), this);
	}
	
	
	public String getMemeberSq(HttpSession session) {
		return (String) loginUsers.get(session);
	}
	
	//mbersq로 저장할 거다 
	public void removeSession(String sq) {
		//데이터를 순차적으로 가지고 있는 객체Enumeration
		//key값을 Enumeration에 담는다 
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while(e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			// 사용자가 같다면
			//lgoinUsers에 session을 key로 찾으면 sq값이 value로 나온다. 
			//loginUsers.get(session) = sq
			//lgoinUsers<session, session의 name>으로 들어가있다고 생각하자.
			//session의 attribute는 <name, value>로 되어있다.
			//여기서 value를 this로 해놨기 때문에 
			//LoginManager를 실행한 클래스의 객체 lm이 들어가게 된다.
			//만약 /member/LoginProcAction에서  LoinManager lm = LoginManager.getInstance();
			//그럼 session의 attribut는 sq와 LoginProcAction의 lm이 된다. 
			if (loginUsers.get(session).equals(sq)) {
				//세션을 파괴해버릴 거다
				session.invalidate();
			}
		}
	}
}
