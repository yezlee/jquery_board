<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
%>    
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
