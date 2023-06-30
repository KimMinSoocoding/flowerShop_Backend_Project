package kr.co.sionms.jsp.service;

import java.util.List;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Criteria;

public interface BoardService {
	
	// CRUD 
	Long register(Board board);
	
	Board get(Long bno);
	
	List<Board> list(Criteria cri);
	
	int listCount(Criteria cri);
	
	void modify(Board board);
	
	void remove(Long bno);
	
}
