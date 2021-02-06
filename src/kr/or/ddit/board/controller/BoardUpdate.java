package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/boardUpdate.do")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//0. writer, subject, mail, password, content, seq
		
		//0
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject"); 
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		

		BoardVO vo = new BoardVO();
		
		vo.setWriter(writer);
		vo.setSubject(subject);
		vo.setPassword(password);
		vo.setMail(mail);
		vo.setContent(content);
		
		vo.setSeq(seq);
		
		
		
		
		
		
		
		//1
		IBoardService service = BoardServiceImpl.getService();
		
		//2
		int cnt = service.updateBoard(vo);
		
		//3
		request.setAttribute("result", cnt);
		
		//4
		request.getRequestDispatcher("board/result.jsp").forward(request, response);
		
		
		
	}

}
