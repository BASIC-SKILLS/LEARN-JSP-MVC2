package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.DeleteAction;
import board.action.DetailAction;
import board.action.ListAction;
import board.action.ModifyAction;
import board.action.ModifyProcAction;
import board.action.WriteAction;
import board.action.WriteProcAction;
import common.Action;
import common.ActionForward;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String command = requestURI.substring("/board/".length());
		
		ActionForward forward = null;
		
		if (command.equals("list")) {
			Action action = new ListAction();
			try {
				//ListAction이 Action인터페이스의 추상클래스 execute를 실행하고
				// 재정의하고 return되는 forward가 
				//아래의 forward로 값이 삽입된다.
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("write")) {
			Action action = new WriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("writeProc")) {
			Action action = new WriteProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("detail")) {
			Action action = new DetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}   else if (command.equals("delete")) {
			Action action = new DeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("modify")) {
			Action action = new ModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("modifyProc")) {
			Action action = new ModifyProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  
		
		
		
		
		
		
		//그래서 그 결과 forward가 null이 아니니까 
		if (forward != null) {
			//리다이렉트 방식이면
			if (forward.isRedirect()) {
				//forward에 담겨진 주소로 리다이렉트 방식으로 이동하고
				//리다이렉트는 주소값이 변한다, 즉 기존의 request가 삭제되고 새로운 request가 만들어진다
				//그래서 getAttribute를 쓸 수 없는 것이다.
				//Attribute 할 필요가 없으면 리다이렉트 방식을 쓴다
				response.sendRedirect(forward.getPath());
			} else {
				//디스패치 방식이면 forward에 담겨진 주소로 디스패치 방식으로 이동한다.
				//디스패치 방식은 주소값이 변하지 않는다. 즉, 기존의 request가 유지된다.
				//그래서 getAttribute를 쓸 수 있는 것이다.
				//Attribute를 할 필요가 있다면 디스패치 방식을 쓴다
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}












