package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;


@WebServlet("/boardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//0
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		//1
		IBoardService service = BoardServiceImpl.getService();
		
		//2
		int cnt = service.deleteBoard(seq);
		
		//3
		request.setAttribute("result", cnt);
		
		//4
		request.getRequestDispatcher("board/result.jsp").forward(request, response);
		
		
	}

}
