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
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardSave
 */
@WebServlet("/boardSave.do")
public class BoardSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//0. 클라이언트 데이터 가져오기
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject"); //("subject") 이건 꼭 일치해야해
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String content = request.getParameter("content");
		
		
		System.out.println(writer + "test");
		System.out.println(subject);
		System.out.println(password);
		System.out.println(mail);
		System.out.println(content);
		
		BoardVO vo = new BoardVO();
		vo.setWriter(writer);
		vo.setSubject(subject);
		vo.setPassword(password);
		vo.setMail(mail);
		vo.setContent(content);
		
		vo.setWip(request.getRemoteAddr());
		
		
		//1. 서비스 객체
		IBoardService service = BoardServiceImpl.getService();
		 
		//2. 메소드 호출
		int seq = service.insertBoard(vo);

		//3. 결과값 저장
		request.setAttribute("result", seq); //"result" 이것도 공통으로 쓸 키
		
		//4. 
		request.getRequestDispatcher("board/result.jsp").forward(request, response);  //result.jsp 이거 공통으로 쓰려고
				
		
		
	}

}
