package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {
	//전체 게시글 가져오기
	public List<BoardVO> listAll() throws SQLException;//다오가 서비스한테 예외를 던져주고 서비스가 처리하라고 해준거. 그래서 서비스에 트라이캐치가 있는거
	
	//페이지별 게시글 가져오기 -이거랑 (리스트검색-search) 이거랑 비스무리? - 이게 진짜 중요한거!!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<BoardVO> listPage(Map<String, Object> map) throws SQLException; 
	//int start, int end 이렇게 쓸수없으니까 맵을 써야함. Object여기에 start, end주는거야. Integer줘도 되는데 search할때 문자열을 써야해서 오브젝으로 주는거
	
	//전체 글 갯수 가져오기
	public int getTotalCount() throws SQLException;
	
	//게시글 등록
	public int insertBoard(BoardVO vo) throws SQLException;
	
	//게시글 삭제
	public int deleteBoard(int seq) throws SQLException;
	
	//게시글 수정
	public int updateBoard(BoardVO vo) throws SQLException;
	
	//조회수 증가
	//조회수는 열릴때만 증가해야함 - 닫힐때 증가하면 안됨
	public int hitUpdate(int seq) throws SQLException;
	
	//댓글 등록
	public int insertReply(ReplyVO vo) throws SQLException;
	
	
	//댓글 수정
	public int updateReply(ReplyVO vo) throws SQLException; //수정할땐 번호랑 내용이 필요해서 
	
	//댓글 삭제
	public int deleteReply(int renum) throws SQLException; //삭제할땐 번호만 필요하지
	
	//댓글 리스트
	public List<ReplyVO> listReply(int bonum) throws SQLException; //20번 글에 대한 댓글이야 라고 알려줘야하니까 bonum
		
}
