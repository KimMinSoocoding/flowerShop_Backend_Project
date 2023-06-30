package kr.co.sionms.jsp.member.controller;

import static kr.co.sionms.jsp.util.ParamSolver.isLogin;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.service.BoardService;
import kr.co.sionms.jsp.service.BoardServiceImpl;
import kr.co.sionms.jsp.service.MemberService;
import kr.co.sionms.jsp.service.MemberServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;
@WebServlet("/memberModify")
public class MemberModify extends HttpServlet{
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/login?href=" + URLEncoder.encode(req.getRequestURI(), "utf-8"));
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/memberModify.jsp").forward(req, resp);
//		req.getSession().getAttribute("member");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = ParamSolver.getParams(req, Member.class);
		req.setAttribute("member", member);
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date reg = new Date();
		String regdate = df.format(reg);

		
		
		req.getSession().setAttribute("member", member);
		System.out.println(req.getAttribute("member"));
		memberService.modify(member);
		String redirectStr = req.getContextPath();
		resp.sendRedirect(redirectStr);
	}
	
}
