package kr.co.sionms.jsp.service;

import java.util.List;

import kr.co.sionms.jsp.dao.ReplyDao;
import kr.co.sionms.jsp.domain.Reply;

public class ReplyServiceImpl implements ReplyService {
	

	private ReplyDao dao = new ReplyDao();
	
	@Override
	public Long register(Reply reply) {
		return (long)dao.insert(reply);
	}
	
	@Override
	public Reply get(Long rno) {
		return dao.selectOne(rno);
	}
	
	@Override
	public List<Reply> list(Long bno) {
		return dao.selectList(bno);
	}

	@Override
	public int remove(Long rno) {
	  return dao.delete(rno);
	}
	
}
