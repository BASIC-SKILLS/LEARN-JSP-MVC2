package common;

public class ActionForward {
	//서블릿에서 요청 처리 후 포워딩 될 경로  최종적으로 view페이지 어디로 갈지 결정
	private String path;
	//포워딩 방식
	// (true : redirect, false : dispatch0
	private boolean redirect;
	
	public ActionForward() {}
	
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	


	
	
}
