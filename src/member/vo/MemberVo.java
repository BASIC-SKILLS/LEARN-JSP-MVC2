package member.vo;

//세션에 저장하는 걸 int mber_sq가 아니라 MemberVo자체에 저장한다.
public class MemberVo {
	private int mber_sq;
	private String mber_id;
	private String mber_pwd;
	private String mber_nm;
	private int mber_age;
	private String mber_dttm;
	private boolean mber_del_fl;
	
	
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public String getMber_id() {
		return mber_id;
	}
	public void setMber_id(String mber_id) {
		this.mber_id = mber_id;
	}
	public String getMber_pwd() {
		return mber_pwd;
	}
	public void setMber_pwd(String mber_pwd) {
		this.mber_pwd = mber_pwd;
	}
	public String getMber_nm() {
		return mber_nm;
	}
	public void setMber_nm(String mber_nm) {
		this.mber_nm = mber_nm;
	}
	public int getMber_age() {
		return mber_age;
	}
	public void setMber_age(int mber_age) {
		this.mber_age = mber_age;
	}
	public String getMber_dttm() {
		return mber_dttm;
	}
	public void setMber_dttm(String mber_dttm) {
		this.mber_dttm = mber_dttm;
	}
	public boolean isMber_del_fl() {
		return mber_del_fl;
	}
	public void setMber_del_fl(boolean mber_del_fl) {
		this.mber_del_fl = mber_del_fl;
	}

}
