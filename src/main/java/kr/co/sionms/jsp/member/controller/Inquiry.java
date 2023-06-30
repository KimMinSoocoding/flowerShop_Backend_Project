package kr.co.sionms.jsp.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sionms.jsp.dao.MemberDao;
import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.service.MemberService;
import kr.co.sionms.jsp.service.MemberServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;

@WebServlet("/memberinquiry")
public class Inquiry extends HttpServlet{
	
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/memberinquiry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = ParamSolver.getParams(req, Member.class);
		req.setAttribute("member", member);
	}
	
	

}
