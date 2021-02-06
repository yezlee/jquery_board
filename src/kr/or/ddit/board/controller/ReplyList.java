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


@WebServlet("/ReplyList.do")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReplyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//0. bonum 가져오기
		int bonum = Integer.parseInt(request.getParameter("bonum")) ;
		
		//1. service 객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		//2. service()메소드 호출하기 - 결과값 얻기
		List<ReplyVO> list = service.listReply(bonum);
		
		//3. 결과값을 request에 저장하기
		request.setAttribute("list", list);
		
		//4. jsp로 forward하기
		request.getRequestDispatcher("board/replyList.jsp").forward(request, response);
		//replyList.jsp이건 이렇게 해놓고 내가 jsp를 만든다
		
	}

}
