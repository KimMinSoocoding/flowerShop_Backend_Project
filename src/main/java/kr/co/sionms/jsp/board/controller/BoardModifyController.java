package kr.co.sionms.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.service.BoardService;
import kr.co.sionms.jsp.service.BoardServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;

@WebServlet("/board/modify")
public class BoardModifyController extends HttpServlet{
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login");
			return;
		}
		req.setAttribute("cri", ParamSolver.getParams(req, Criteria.class));
		req.setAttribute("board",boardService.get(Long.valueOf(req.getParameter("bno")))); // Long.valueOf 숫자로 형변환
		req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Board board = new Board(req.getParameter("title"), req.getParameter("content"), req.getParameter("writer"));
//		board.setBno(Long.valueOf(req.getParameter("bno")));
		
		Criteria cri = ParamSolver.getParams(req, Criteria.class);
		Board board = ParamSolver.getParams(req, Board.class);
		boardService.modify(board);
		resp.sendRedirect("view?bno="+board.getBno() + "&" + cri.getFullQueryString());
	}
	
}
