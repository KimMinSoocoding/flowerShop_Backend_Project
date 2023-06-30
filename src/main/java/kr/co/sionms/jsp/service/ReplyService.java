package kr.co.sionms.jsp.service;

import java.util.List;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Reply;

public interface ReplyService {
	
	// CRUD 
	Long register(Reply reply);
	
	Reply get(Long rno);
	
	List<Reply> list(Long bno);
	
	int remove(Long rno);
	
}
