<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int res = (Integer)request.getAttribute("res");
	if(res > 0){
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
