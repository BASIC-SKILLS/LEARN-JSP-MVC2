package common;

//페이지네이션을 담당하는 클래스
public class PageInfo {
	// 페이지를 표시하기 위해 필요한 것 : 현재페이지, 한 페이지에 표시할 글의 갯수, 글의 총개수...
	
	//한 페이지에 보여줄 글의 개수
	// 바꿀 수 없도록 상수로 미리 선언해둔다 
	private final int articleCountPerPage = 10;
	
	//페이지 그룹 개수 (한페이지당 보여줄 총 페이지의 개수)
	// 1,2,3,4,5 >>> 6,7,8,9,10 >>> 11,12,13,14,15 >>> ...
	private final int pageGroupCount = 5;
	
	//현재페이지 번호
	private int nowPageNumber;
	
	//글의 총 개수
	private int totalArticleCount;	
	
	//시작 글 번호
	private int startArticleNumber;
	
	//총 페이지 개수 (=마지막페이지)
	private int totalPageCount;
	
	//시작페이지 번호
	private int startPageNumber;
	
	//끝페이지 번호
	private int endPageNumber;
	
	//이전 페이지 번호
	private int prePageNumber;
	
	//다음 페이지 번호
	private int nextPageNumber;
	
	
	
	
	
	
	//생성자
	public PageInfo(int nowPageNum, int totalArticleCount) {
		this.nowPageNumber = nowPageNum;
		this.totalArticleCount = totalArticleCount;
		
		// 페이지개수 계산
		//ceil함수는 인자 값보다 크거나 같은 가장 작은 정수 값을 double형으로 반환한다.
				// 한페이지당 보여줄 글의 개수는 10개. 예를들어 전체 글 개수가 37이다. 페이지는 총 4개가 되어야 한다. 그럼 37/10 = 3.7. 세일함수로 인해 4.000000. 이걸 int형으로 바꾸면 4가 된다. 
		this.totalPageCount = (int)Math.ceil((double) this.totalArticleCount/this.articleCountPerPage);
		if (this.totalPageCount < 1) {
			this.totalPageCount = 1;
		}
		
		// 현재 페이지 확정
		if (this.nowPageNumber > this.totalPageCount) {
			this.nowPageNumber = this.totalPageCount;
		}
		
		// 이전 페이지 번호 계산
		this.prePageNumber = this.nowPageNumber - 1;
		if (this.prePageNumber < 1) {
			this.prePageNumber = 1;
		}
		
		// 다음 페이지 번호 계산
		this.nextPageNumber = this.nowPageNumber + 1;
		if (this.nextPageNumber > this.totalPageCount) {
			this.nextPageNumber = this.totalPageCount;
		}
		
		// 0,10 / 10,10 / 20,10 / 30,10 이렇게 되게 하는 거, 데이터베이스의 limit의 앞에 오는 숫자 
		this.startArticleNumber = (this.nowPageNumber - 1) * this.articleCountPerPage;
		
		
		
		//for문 돌리기 위해 필요한 것들 
		// 현재페이지에 따라 시작페이지 번호가 달라지는 걸 설정한다
		// 한페이지에 보여줄 글의 개수 10개, 페이지 그룹은 5개, nowPageNumber=pn으로 입력받은 값을 가져오는 것, pn이 1~5까지는 spg(스타트페이지넘버)가 1이 되어야 하고, pn이6~10까지는 spg가 6이 되어야 하고... 하는 것을 계산하는 식
		this.startPageNumber = (int) (((double) this.nowPageNumber / this.pageGroupCount + 0.9) - 1) * this.pageGroupCount + 1;
		// 현재페이지에 따라 끝페이지 번호가 달라지는 걸 설정한다 pn이 1~5까지는 epg(엔드페이지넘버)가 5이 되어야 하고, pn이6~10까지는 epg가 10이 되어야 하고... 하는 것을 계산하는 식
		this.endPageNumber = this.startPageNumber + (this.pageGroupCount -1);
		if (this.endPageNumber > this.totalPageCount) {
			this.endPageNumber = this.totalPageCount;
		}
	}
	
	
	
	
	
	
	
	
	

	public int getPrePageNumber() {
		return prePageNumber;
	}










	public void setPrePageNumber(int prePageNumber) {
		this.prePageNumber = prePageNumber;
	}










	public int getNextPageNumber() {
		return nextPageNumber;
	}










	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}










	public int getNowPageNumber() {
		return nowPageNumber;
	}

	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getStartArticleNumber() {
		return startArticleNumber;
	}

	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPageNumber() {
		return startPageNumber;
	}

	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

	public int getArticleCountPerPage() {
		return articleCountPerPage;
	}

	public int getPageGroupCount() {
		return pageGroupCount;
	}
	
	
	
}
