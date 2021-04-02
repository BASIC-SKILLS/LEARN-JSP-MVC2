package common;

import java.util.regex.Pattern;

public class RegExp {
	
	public static final int TYPE_MEMBER_ID = 1;
	public static final int TYPE_MEMBER_PWD = 2;
	public static final int TYPE_MEMBER_NAME = 3;
	public static final int TYPE_MEMBER_AGE = 4;
	
	//페이지네이션 페이지넘버 검사 
	public static final int TYPE_NUMBER = 11;
	
	public static final int TYPE_SEARCH = 12;
	
	public static final int TYPE_SUB =13;
	
	// 정규표현식 테스트 할 수 있는 페이지 : https://regexr.com/
	public static final String EXP_MEMBER_ID = "^[a-z0-9]{4,8}$";
	public static final String EXP_MEMBER_PWD = "^[a-zA-z0-9!@#$%^&*]{4,12}$";
	public static final String EXP_MEMBER_NAME = "^[가-힣]{2,5}$";
	public static final String EXP_MEMBER_AGE = "^[0-9]{1,3}$";
	
	// 숫자인지만 일단 검사, 자리수에 상관없이 숫자이면 통과, 0이상인지 검사하는 것은 여기서 못한다
	public static final String Exp_NUMBER = "^[0-9]*$";
	
	public static final String EXP_SEARCH = "^[0-9a-zA-z가-힣 ]{2,10}$";
	
	public static final String EXP_SUB ="^.{2,50}$";
	
	public boolean isNotEmpty(String data) {
		if (data == null || data.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean validateCheck(int type, String data) {
		
		boolean result = false;
		
		if (!isNotEmpty(data)) {
			return result;
		} 
		
		switch (type) {
		//아이디 검사
		case TYPE_MEMBER_ID: result = Pattern.matches(EXP_MEMBER_ID, data); break;
		case TYPE_MEMBER_PWD: result = Pattern.matches(EXP_MEMBER_PWD, data); break;
		case TYPE_MEMBER_NAME: result = Pattern.matches(EXP_MEMBER_NAME, data); break;
		case TYPE_MEMBER_AGE: result = Pattern.matches(EXP_MEMBER_AGE, data); break;
		case TYPE_NUMBER: result = Pattern.matches(Exp_NUMBER, data); break;
		case TYPE_SEARCH: result = Pattern.matches(EXP_SEARCH, data); break;
		case TYPE_SUB: result = Pattern.matches(EXP_SUB, data); break;
		}
		
		return result;
	}
}
