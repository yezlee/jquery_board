package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/List.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//얘는 전체리스트를 가져오고
		
		//0.
		
		//1. 서비스 객체
		IBoardService service = BoardServiceImpl.getService();
		
		//2. 메소드 호출
		List<BoardVO> list = service.listAll();

		//3. 결과값 저장
		request.setAttribute("list", list); //"list" 이건 jsp에서 네임!!
		
		//4. 
		request.getRequestDispatcher("board/listAll.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 얘는 페이지별 리스트 가져온다!
			
		//0. page 번호 가져오기
		int cpage = Integer.parseInt(request.getParameter("page")); //이 페이지가 데이터임. 페이지란 이름으로 가져오는거
		
		//1. 서비스 객체
		IBoardService service = BoardServiceImpl.getService();
		
		
		// 전체 글 갯수 가져오기
		int totalCount = service.getTotalCount();
		
		//한페이지당 출력할 글 갯수 cpage
		int perList = 5;
		
		int start = (cpage-1) * perList + 1;
		//cpage = 1 ==> 1
		//cpage = 2 ==> 4
		//cpage = 3 ==> 7
		
		int end = start + perList - 1;
		//start가 19일때 end는 20이야 원래 3의배수면 19,20,21이렇게 끝나는데 21이 없어서
		//그래서 if문 써줘야해
		if(end > totalCount) end = totalCount;
		
		
		//한 화면에 출력될 페이지 갯수
		int perPage = 3; // 보통 5이거나 10이지 
		int totalPage = (int)Math.ceil(totalCount / (double)perList);
		//Math.ceil 이게 반올림. round인건가
		//현재 totalPage는 7임
		
		//cpage가 현재페이지야. 그걸 가지고 계산하면돼
		//한페이지에 5개가 있다고 했을때 현재패이지가 3이면 스타트는 1이고 엔드페이지는 5지
		//한페이지에 5개가 있다고 했을때 현재패이지가 6이면 스타트는 6이고 엔드페이지는 10지
		int startPage = ( (cpage-1) / perPage * perPage ) +1;
		//이건 int여서 가능한것임. 나머지 생각안하고 몫만 생각해서
		//cpage = 1 ==> 1  // cpage = 2 ==> 1
		//cpage = 3 ==> 3  // cpage = 4 ==> 3
		
		int endPage = startPage + perPage -1;
		if(endPage > totalPage) endPage = totalPage;
		//위에카운트 계산하던거랑 같은 방식으로 페이지도 계산하면됨
		
		
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start); // 1 이거나 new Integer(1) 이렇게 쓰는거랑 같아 . 근데 자바는 이렇게 할필요없어?. 자바는 오토박싱이 지원되기때문에
		map.put("end", end); //숫자가 end로 바뀐거야
		
		//2. 메소드 호출
		List<BoardVO> list = service.listPage(map);

		//3. 결과값 저장
		request.setAttribute("list", list); //"list" 이건 jsp에서 네임!!
		//지금 list에는 3번 도는거야. 아깐 20번 돌았는데 쿼리문 바꿨잖아
		request.setAttribute("sp", startPage);
		request.setAttribute("ep", endPage);
		request.setAttribute("tp", totalPage);
		//이렇게 이 속성들을 보내줘서 출력해야하잖아
		
		
		//4. 보내는거지
		request.getRequestDispatcher("board/listPage.jsp").forward(request, response);

		
		
		
	}

}
