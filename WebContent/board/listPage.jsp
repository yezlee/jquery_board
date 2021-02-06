<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
	int totalP = (Integer)request.getAttribute("tp");
	int startP = (Integer)request.getAttribute("sp");
	int endP = (Integer)request.getAttribute("ep");
	
	//지금 []안엔 배열만 넣을수있어
	//근데 tp, sp얘네들은 배열이아니야
	//그래서 그밖에 {}이거 사이에 []밖에 적어줌
	
	//success에서 res.dataArr[i] 이렇게 써야하는데 이걸 v로 받았기때문에 따로 고치진않아고돼
	//근데 res.tp 얘네들은 바꿔줘야지
	
%> 
{   

	"tp" : "<%= totalP %>",
	"sp" : "<%= startP %>",
	"ep" : "<%= endP %>",
	"dataArr" :   


[
	<%
		for(int i=0; i<list.size(); i++){
			BoardVO vo = list.get(i);
			if(i>0) out.print(",");
			
			//키 : 밸류 - 키는 암거나 써도 되는거지
			//vo가 아래 {}있는애들
			//여러게 있는 애들을 하나씩 돌려서 뽑는거 위에 for문으로
	%>

		{
			"seq" : "<%= vo.getSeq() %>",
			"name" : "<%= vo.getWriter() %>",
			"title" : "<%= vo.getSubject() %>",
			"hit" : "<%= vo.getHit() %>",
			"mail" : "<%= vo.getMail() %>",
			"cont" : "<%= vo.getContent().replaceAll("\r", "").replaceAll("\n", "<br>") %>",
			"wdate" : "<%= vo.getWdate() %>",
			"wip" : "<%= vo.getWip() %>",
			"pass" : "<%= vo.getPassword() %>"
			
		}

	<%
		}
	%>
]
}