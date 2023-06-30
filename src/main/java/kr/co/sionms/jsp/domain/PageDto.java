package kr.co.sionms.jsp.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.Data;

@Data
public class PageDto {
	// 하단에 출력 될 페이지 사이즈
	private int pageCount = 10;
	// 시작 페이지 숫자 
	private int startPage;
	// 종료 페이지 숫자 
	private int endPage;
	// 게시글 총 갯수
	private int total;
	// next, prev 
	private boolean prev;
	private boolean next;
	
	private boolean doubleNext;
	private boolean doublePrev;
	// Criteria
	private Criteria cri;
	
	public PageDto(int total, Criteria cri) {
		this(10, total, cri);
	}
	
	
	@Override
	public String toString() {
		return "PageDto [pageCount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage + ", total="
				+ total + ", prev=" + prev + ", next=" + next + ", doubleNext=" + doubleNext + ", doublePrev="
				+ doublePrev + ", cri=" + cri + "]";
	}



	public PageDto(int pageCount, int total, Criteria cri) {
		this.pageCount = pageCount;
		this.total = total;
		this.cri = cri;
		// cri.getAmount();
		// cri.getPageNum();
		// total
		endPage = (cri.getPageNum() + (pageCount-1)) / pageCount * pageCount; // 현재보고있는 페이지 1페이지 + 밑에보여질페이지 10 -1 = 10 
		startPage = endPage - (pageCount -1); // 마지막페이지 10 - ( 10 - 1 ) = 1 
		int realEnd = (total + (cri.getAmount() -1)) / cri.getAmount(); 
//		System.out.println("realEnd : " + realEnd);
		if(endPage > realEnd) {
			endPage = realEnd;
		}
		
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
		
		doublePrev = startPage > 1;
		doubleNext = endPage < realEnd;
	}
	
	public static void main(String[] args) {
		Criteria cri = new Criteria(11, 10); // 현재페이지 , 한페이지당 n개씩보기 
		PageDto page = new PageDto(223,cri); // 게시물갯수 , 
		System.out.println(page);
	}
	
	
}
