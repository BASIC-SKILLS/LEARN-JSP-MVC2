//1.  로그인이 필요한 서비스 이동 (/member/detail) ->  세션에 로그인 페이지 이동하도록 한 경로를 세션에 저장 -> 즉 세션에 /member/detail 이거 저장
//2. 로그인 처리
//3. 세션에 경로가 있으면 경로 이동, 없으면 홈으로 이동 

//비밀번호 변경
//1. controller a. 액션실행
//2. action a.로그인 여부 확인
//			b.데이터 가져옴(현재비밀번호, 비밀번호, 비밀번호 확인)
//			c.데이터검사
//			d.sq로 회원 정보 로드(from DB)
//          e.현재 비밀번화 회원 정보의 비밀번호 확인 ->LoginProcAction 참조
//			f.sq, 변경할 비밀번호 암호화 해서 데이터베이스에 저장 -> JoinProcAction 참조 
//          g.경로설정(/member/detail)

//아이디찾기
// action 1. 데이터(이름, 나이) 로드
// 		  2. 데이터 검사 (null여부, ""여부만 검사)
// 		  3. 데이터로 회원정보 로드(from DB)
// 		  4. 회원정보의 아이디 attribute에 저장 ( setAttribut )
// 		  5. searchIdResult.jsp로 이동하여 아이디 보여주기 (디스 패치)

//비밀번호 찾기
//action 1. 데이터(아이디, 이름, 나이) 로드
// 	     2. 데이터 검사 (null 여부, ""여부만 검사, 나이는 데이터 검사까지)
// 	     3. 데이터로 회원이 존재하는지 로드 (form DB)
// 	     4. searchPasswordProc.jsp로 이동
//searchPasswordProc.jsp 1. 비밀번호
// 					     2. 비밀번호 확인
// 					     3. 변경 버튼
// 					     4. 취소 버튼
// 					     5. 변경 클릭시 비밀번호 데이터 검사


package member.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Action;
import common.ActionForward;
import member.action.AjaxCheckIdAction;
import member.action.DetailAction;
import member.action.JoinAction;
import member.action.JoinProcAction;
import member.action.LeaveAction;
import member.action.LoginAction;
import member.action.LoginProcAction;
import member.action.LogoutAction;
import member.action.PasswordAction;
import member.action.SearchIdAction;
import member.action.SearchPasswordAction;
import member.action.SearchPasswordProcAction;
import member.action.UpdateAction;
import member.action.UpdatePasswordAction;
import member.action.UpdatePasswordProcAction;
import member.action.UpdateProcAction;
import member.action.searchIdResultAction;

//회원 관련된 건 중간에 member넣고 보드에 관련된 건 중간에 board넣는다
//중간경로에 member가 들어가면 모조리 다 받겠다
@WebServlet("/member/*")
public class  MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// <a href="/member/join">회원가입</a>일 경우 /memebr/join으로 들어온다
		String requestURI = request.getRequestURI(); 
		// join만 남는다
		String command = requestURI.substring("/member/".length()); 
		

		ActionForward forward = null;
		
		if (command.equals("join")) {
			Action action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("ajaxCheckId")) {
			Action action = new AjaxCheckIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("joinProc")) {
			Action action = new JoinProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("login")) {
			Action action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("loginProc")) {
			Action action = new LoginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("logout")) {
			Action action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("detail")) {
			Action action = new DetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("update")) {
			Action action = new UpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("updateProc")) {
			Action action = new UpdateProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("leave")) {
			Action action = new LeaveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("password")) {
			Action action = new PasswordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("updatePassword")) {
			Action action = new UpdatePasswordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("searchId")) {
			Action action = new SearchIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("searchIdResult")) {
			Action action = new searchIdResultAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("searchPassword")) {
			Action action = new SearchPasswordAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("searchPasswordProc")) {
			Action action = new SearchPasswordProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("updatePasswordProc")) {
			Action action = new UpdatePasswordProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // 통쨰로 외운다
				dispatcher.forward(request, response);
			}
		}

	}
}
