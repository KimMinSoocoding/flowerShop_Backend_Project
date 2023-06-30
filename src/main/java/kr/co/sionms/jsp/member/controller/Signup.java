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
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.service.MemberService;
import kr.co.sionms.jsp.service.MemberServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;

@WebServlet("/Signup")
public class Signup extends HttpServlet{
	
	private MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao = new MemberDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Member member = ParamSolver.getParams(req, Member.class);
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		
//		String idcheck = req.getParameter("idcheck");
		
		memberService.register(member);
		
		String redirectStr = req.getContextPath();
		resp.sendRedirect(redirectStr);
		
	}
	
	

}
