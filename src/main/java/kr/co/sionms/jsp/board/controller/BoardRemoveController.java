package kr.co.sionms.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.service.BoardService;
import kr.co.sionms.jsp.service.BoardServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;

@WebServlet("/board/remove")
public class BoardRemoveController extends HttpServlet{
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = ParamSolver.getParams(req, Criteria.class);
		boardService.remove(Long.valueOf(req.getParameter("bno"))); // Long.valueOf 숫자로 형변환
		resp.sendRedirect("list" + "?" + cri.getFullQueryString());
	}
}
