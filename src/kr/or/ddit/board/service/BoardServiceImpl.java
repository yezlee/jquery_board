package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public class BoardServiceImpl implements IBoardService{

	//다오 객체 얻어오고, 자기꺼만들고 
	private IBoardDao dao;
	private static IBoardService service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getDao();
	}
	
	public static IBoardService getService() {
		if(service==null) service = new BoardServiceImpl();
		
		return service;
	}
	
	
	@Override
	public List<BoardVO> listAll() { // listAll() 라는 메소드를 만들어주고, 리턴타입이 List<BoardVO> 이고, 
		/* 
		List<BoardVO> list = null; 변수를 선언해주고(리턴에 써주려고)
		list = dao.listAll();
		return list;
		
		이렇게 1,2,3번 후루룩 쓰고 2번에서 트라이캐치해주고
		
		 */
		
		List<BoardVO> list = null;
		try {
			list = dao.listAll(); //다오에서 수행한걸 얘가 받아서
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list; //이걸 컨트롤러에 돌려주는거지. 컨트롤러는 그걸 가지고 jsp로 가는거야
	}

	@Override
	public List<BoardVO> listPage(Map<String, Object> map) {
		List<BoardVO> list = null;
		try {
			list = dao.listPage(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		int cnt = 0; //1단계
		
		try {
			cnt = dao.getTotalCount(); //2단계 (트라이캐치해주지)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt; //3단계
	}

	@Override
	public int insertReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		int rnum = 0;
		try {
			rnum = dao.insertReply(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rnum;
	}

	@Override
	public List<ReplyVO> listReply(int bonum) {

		List<ReplyVO> list = null;
		
		try {
			list = dao.listReply(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int updateReply(ReplyVO vo) {
		int res = 0;
		try {
			res = dao.updateReply(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res; //몇개 수정했는지 알려주는. 근데 결과는 1이지. 번호는 한개밖에 없으니까 하나밖에 수정못하지
	}

	@Override
	public int deleteReply(int renum) {
		int res=0;
		try {
			res = dao.deleteReply(renum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res; //위에 수정이랑 마찬가지
	}

	@Override
	public int insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		
		int seq = 0;
		try {
			seq = dao.insertBoard(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return seq;
	}

	@Override
	public int deleteBoard(int seq) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = dao.deleteBoard(seq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO vo) {

		int cnt = 0;
		try {
			cnt = dao.updateBoard(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int hitUpdate(int seq) {
		
		int cnt = 0;
		
		try {
			cnt = dao.hitUpdate(seq);
			
			//ajax가 아닐때는 이렇게 해야함.
			
			//dao,listPage(1,5);
			
			//지금처럼 이렇게 서비스에서 다오를 연거푸 호출할수있어
			//이렇게 계좌뭐시기 뭐하고 여러개 업데이트 할일이 있어
			//이때 이걸 통으로 묶어서 트라이캐치해야함. 트랜잭션이 돼야하니까
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return cnt;
	}
	

}
