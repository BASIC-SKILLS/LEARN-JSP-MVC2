package board.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDao;
import board.vo.BoardVo;
import common.PageInfo;

import static common.JdbcUtil.*;

public class BoardService {
	
	private Connection con;

	private BoardDao setDao() {
		BoardDao dao = BoardDao.getInstance();
		this.con = getConnection();
		dao.setConnection(this.con);
		return dao;
	}
	

	public int getTotalArticleCount(String query) {
		BoardDao dao = setDao();
		int count = dao.getTotalArticleCount(query);
		close(this.con);
		return count;
	}
	

	public ArrayList<BoardVo> getBoardList(PageInfo pi, String query) {
		BoardDao dao = setDao();
		ArrayList<BoardVo> list = dao.getBoardList(pi, query);
		close(this.con);
		return list;
	}
	

	public boolean insertArticle(BoardVo vo) {
		BoardDao dao = setDao();
		int count = dao.insertArticle(vo);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false; 
	}
	
	public BoardVo getArticleDetail(int sq) {
		BoardDao dao = setDao();
		BoardVo vo = dao.getArticleDetail(sq);
		close(this.con);
		return vo;
	}
	
	public boolean increaseArticleHitCount(int sq) {
		BoardDao dao = setDao();
		int count = dao.increaseArticleHitCount(sq);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false; 
	}
	
	//웹개발을 할려면 데이터베이스(쿼리작성, 데이터베이스 구조 분석, 데이터베이스 설계) 공부를 많이 해야한다
	public boolean deleteArticle(int sq) {
		BoardDao dao = setDao();
		int count = dao.deleteArticle(sq);
		boolean isSuccess = false;
		if (count>0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public boolean modifyArticle(BoardVo modify_vo) {
		BoardDao dao = setDao();
		int count = dao.modifyArticle(modify_vo);
		if (count > 0) {
			commit(this.con);
		} else {
			rollback(this.con);
		}
		close(this.con);
		return count > 0 ? true : false; 
	}
}
