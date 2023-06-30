package kr.co.sionms.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.domain.PageDto;
import kr.co.sionms.jsp.service.BoardService;
import kr.co.sionms.jsp.service.BoardServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet{
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = ParamSolver.getParams(req, Criteria.class);

		req.setAttribute("boards", boardService.list(criteria));
		req.setAttribute("page", new PageDto(boardService.listCount(criteria), criteria));
		
		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp").forward(req, resp);
	}
}
