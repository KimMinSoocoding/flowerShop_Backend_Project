package kr.co.sionms.jsp.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sionms.jsp.dao.MemberDao;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.service.MemberService;
import kr.co.sionms.jsp.service.MemberServiceImpl;


@WebServlet("/member/logout")
public class Logout extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath());
	}
		
	
}
