package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;


@WebServlet("/replyModify.do")
public class ReplyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//0. bonum 가져오기
		int renum = Integer.parseInt(request.getParameter("renum"));
		String cont = request.getParameter("cont");
		
		//1. service 객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		//2. service()메소드 호출하기 - 결과값 얻기
		ReplyVO vo = new ReplyVO();
		
		vo.setRenum(renum);
		vo.setCont(cont);
		
		int res = service.updateReply(vo);
		
		//3. 결과값을 request에 저장하기
		request.setAttribute("res", res);
		
		//4. jsp로 forward하기
		request.getRequestDispatcher("board/replyUpdate.jsp").forward(request, response);
				
		
		
		
	}

}
