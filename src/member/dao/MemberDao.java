package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.vo.MemberHistoryVo;
import member.vo.MemberVo;

import static common.JdbcUtil.*;

//싱글톤으로 구현한다
public class MemberDao {
	
	private Connection con;
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		return MemberDao.LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getCheckId(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//아이디 똑같고 회원탈퇴하지 않은 것
			pstmt = con.prepareStatement("select count(*) from ki_mber_tb where binary(mber_id)=? and mber_del_fl=0");
			pstmt.setString(1, id);
			// 결과를 rs에 넣어준다
			rs = pstmt.executeQuery();
			// rs에 있는 거 다 넣어준다
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int registerMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into ki_mber_tb(mber_id, mber_pwd, mber_nm, mber_age) values(?,?,?,?)");
			pstmt.setString(1, vo.getMber_id());
			pstmt.setString(2, vo.getMber_pwd());
			pstmt.setString(3, vo.getMber_nm());
			pstmt.setInt(4, vo.getMber_age());
			count = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public MemberVo getMemberInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			pstmt = con.prepareStatement("select  mber_sq, mber_id, mber_pwd, mber_nm from ki_mber_tb where binary(mber_id)=? and mber_del_fl=0");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setMber_id(rs.getString("mber_id"));
				vo.setMber_pwd(rs.getString("mber_pwd"));
				vo.setMber_nm(rs.getString("mber_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int insertMemberHistory(MemberHistoryVo vo) {
		PreparedStatement pstmt = null;
		int count =0;
		try {
			pstmt = con.prepareStatement("insert into ki_mber_hist_tb(hist_mber_sq, hist_evt_gb) values(?,?)");
			pstmt.setInt(1, vo.getHist_mber_sq());
			pstmt.setBoolean(2, vo.isHist_gb());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public MemberVo getMemberDetail(int sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			pstmt = con.prepareStatement("select mber_sq, mber_id, mber_pwd, mber_nm, mber_age,mber_dttm from ki_mber_tb where mber_sq=? and mber_del_fl=0");
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setMber_id(rs.getString("mber_id"));
				vo.setMber_pwd(rs.getString("mber_pwd"));
				vo.setMber_nm(rs.getString("mber_nm"));
				vo.setMber_age(rs.getInt("mber_age"));
				vo.setMber_dttm(rs.getString("mber_dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int updateMemberInfo(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update ki_mber_tb set mber_nm=?, mber_age=? where mber_sq=? and mber_del_fl=0");
			pstmt.setString(1, vo.getMber_nm());
			pstmt.setInt(2, vo.getMber_age());
			pstmt.setInt(3, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int leaveMember(int sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update ki_mber_tb set mber_del_fl=? where mber_sq=? and mber_del_fl=0");
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, sq);
			count = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int updatePassword(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update ki_mber_tb set mber_pwd=? where mber_sq=? and mber_del_fl=0");
			pstmt.setString(1, vo.getMber_pwd());
			pstmt.setInt(2, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public String getMemberId(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;

		try {
			pstmt = con.prepareStatement("select mber_id from ki_mber_tb where (mber_nm=? and mber_age=?) and mber_del_fl=0");
			pstmt.setString(1, vo.getMber_nm());
			pstmt.setInt(2, vo.getMber_age());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				id = rs.getString("mber_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	public int getMemberCount(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from ki_mber_tb where (binary(mber_id)=? and mber_nm=? and mber_age=?) and mber_del_fl=0");
			pstmt.setString(1, vo.getMber_id());
			pstmt.setString(2, vo.getMber_nm());
			pstmt.setInt(3, vo.getMber_age());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int updateMemberPassword(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update ki_mber_tb set mber_pwd=? where (mber_id=? and mber_nm=? and mber_age=?) and mber_del_fl=0");
			pstmt.setString(1, vo.getMber_pwd());
			pstmt.setString(2, vo.getMber_id());
			pstmt.setString(3, vo.getMber_nm());
			pstmt.setInt(4, vo.getMber_age());
			count = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
