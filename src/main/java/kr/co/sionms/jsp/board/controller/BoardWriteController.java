package kr.co.sionms.jsp.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.service.BoardService;
import kr.co.sionms.jsp.service.BoardServiceImpl;
import kr.co.sionms.jsp.util.ParamSolver;
import static kr.co.sionms.jsp.util.ParamSolver.*;

@MultipartConfig( location = ParamSolver.UPLOAD_PATH, fileSizeThreshold = 1024)
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet{
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/login?href=" + URLEncoder.encode(req.getRequestURI(), "utf-8"));
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Board board = new Board(req.getParameter("title"), req.getParameter("content"), req.getParameter("writer"));
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/login?href=?" + URLEncoder.encode(req.getRequestURI(), "utf-8"));
			return;
		}
		Board board = getParams(req, Board.class);
		System.out.println(board);
		boardService.register(board);
		resp.sendRedirect("list");
	}
	
}
