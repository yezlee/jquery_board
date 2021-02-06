<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int rnum = (Integer)request.getAttribute("rnum");
	if(rnum > 0){
%>

	{
		
		"sw" :	"저장 성공"
	
	}

<%		
	}else{ 
%>

	{
		
		"sw" :	"저장 실패"
	
	}


<%		
	}
%>

