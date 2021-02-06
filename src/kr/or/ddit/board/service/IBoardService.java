package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {

	//전체 게시글 가져오기
	public List<BoardVO> listAll();
	
	//페이지별 게시글 가져오기 -이거랑 (리스트검색-search) 이거랑 비스무리? - 이게 진짜 중요한거!!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<BoardVO> listPage(Map<String, Object> map); 
	//int start, int end 이렇게 쓸수없으니까 맵을 써야함. Object여기에 start, end주는거야. Integer줘도 되는데 search할때 문자열을 써야해서 오브젝으로 주는거
	
	//전체 글 갯수 가져오기
	public int getTotalCount();
	
	//게시글 등록
	public int insertBoard(BoardVO vo);
	
	//게시글 삭제
	public int deleteBoard(int seq);
	
	//게시글 수정
	public int updateBoard(BoardVO vo);
	
	//조회수 증가
	public int hitUpdate(int seq);
	
	//댓글 등록
	public int insertReply(ReplyVO vo);
	
	//댓글 수정
	public int updateReply(ReplyVO vo); //수정할땐 번호랑 내용이 필요해서 
	
	//댓글 삭제
	public int deleteReply(int renum); //삭제할땐 번호만 필요하지
	
	//댓글 리스트
	public List<ReplyVO> listReply(int bonum);
	
	
}
