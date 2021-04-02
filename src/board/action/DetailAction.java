package board.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.RegExp;

import static common.RegExp.TYPE_NUMBER;

import java.io.PrintWriter;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// sq 데이터 로드
		String sq = request.getParameter("sq");
		
		// sq 데이터 검사
		RegExp re = new RegExp();
		if (sq == null || sq.equals("") || !re.validateCheck(TYPE_NUMBER, sq) || Integer.parseInt(sq) < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');history.back()';</script>");
			out.close();
			return null;
		}
		
		// sq에 해당하는 글 정보 로드
		BoardService svc = new BoardService();
		
		// 쿠키 조회 후 조회수 업데이트 여부 결정
		
		boolean isGet = false;	// 쿠키 존재 여부 확인 변수
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String value = null;	// 쿠키 값 저장할 변수
			for (Cookie c : cookies) {
				// sq 쿠키가 있는 경우
				if (c.getName().equals("board")) {
					// 쿠키 값 저장하여 분해 뒤 sq가 존재하는지 확인
					value = c.getValue();
					String valueArray[] = value.split("-");
					for (int i = 0; i < valueArray.length ;i++) {
						if (valueArray[i].equals(sq)) {
							isGet = true;
						}
					}
				}
			}
			// sq 쿠키가 없을 경우
			if (!isGet) {
				boolean isSuccess = svc.increaseArticleHitCount(Integer.parseInt(sq));
				if (!isSuccess) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('조회 수 수정에 실패하였습니다.');history.back()';</script>");
					out.close();
					return null;
				}
				
				// 쿠키 데이터 저장(key, value)
				Cookie c1 = null;
				// 쿠키값 없을 경우
				if (value == null) {	// 쿠키값 없을 경우
					c1 = new Cookie("board", sq);
				} else {	// 쿠키값 있을 경우
					c1 = new Cookie("board", value + "-" + sq);
				}
				
				// 쿠키 만료 시간 설정 (60sec * 60min * 24h * 365d)
				c1.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(c1);
			}
		}
		
		BoardVo vo = svc.getArticleDetail(Integer.parseInt(sq));
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 정보를 불러오는데 실패하였습니다.');history.back()';</script>");
			out.close();
			return null;
		}
		
		// attribute에 vo 저장
		request.setAttribute("vo", vo);
		
		// view 경로 설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/detail.jsp");
		return forward;
	}
}
















