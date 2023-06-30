package kr.co.sionms.jsp.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import kr.co.sionms.jsp.dao.MemberDao;
import kr.co.sionms.jsp.domain.Member;

public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDao = new MemberDao();
	
	@Override
	public void register(Member member) {
		memberDao.insert(member);
	}

	
	@Override
	public int login(String id, String pw) {
		Member member = memberDao.selectOne(id);
		if(member == null) {
			return 2;
		}
		else if(!member.getPw().equals(pw)) {
			return 3;
		}else {
			return 1;	
		}
	}
	
	@Override
	public Member get(String id) {
		return memberDao.selectOne(id);
		
	}
	
	public void remove(String id) {
		// 작성한 게시글 아이디 변경 
		// 작성한 댓글 아이디 변경 
		// 회원 테이블 내에서 삭제
		memberDao.delete(id);
	}
	
	@Override
	public void modify(Member member) {
		memberDao.update(member);
	}
}
