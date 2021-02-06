package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;


@WebServlet("/ReplySave.do")
public class ReplySave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//0. ajax 요청시 전송데이터 받기 - reply객체 받기
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		String name = request.getParameter("name");
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setBonum(bonum);
		vo.setName(name);
		vo.setCont(cont);
		
		//1. service 객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		//2. service 메소드 호출 public int insertReply(ReplyVO vo); 이거를
		int rnum = service.insertReply(vo);
		
		//3. 결과값을 request에 저장하기
		request.setAttribute("rnum", rnum);
		
		//4. jsp로 forward하기
		request.getRequestDispatcher("board/replySave.jsp").forward(request, response);
		
		
		
	}

}
