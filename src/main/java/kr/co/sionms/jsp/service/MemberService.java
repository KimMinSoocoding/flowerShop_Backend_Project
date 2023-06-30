package kr.co.sionms.jsp.service;

import kr.co.sionms.jsp.domain.Member;

public interface MemberService {
	// 회원가입 
	void register(Member member);
	// 로그인 
	int login(String id, String pw);
	
	// 회원목록 조회 
	Member get(String id);
	
	// 회원 정보 수정 
	void modify(Member member);
	
	// 탈퇴
	void remove(String id);
}
