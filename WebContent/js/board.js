
//html이 너무 길어져서 따로 빼서 작성하는거야 여기에



currentPage = 1;



var hitUpdateServer = function(btn){ //btn ==> 제목이 있는 a태그 - list 
	
	$.ajax({
		url : '/board/hitUpdate.do',
		type : 'get',
		data : {"seq" : v_idx}, //data : "seq=" + v_idx 
		success : function(res){
			//alert(res.sw);
			//성공한거 확인하고 나서 화면에도 구현을 해야해.
			//여기까지하면 조회수는 증가하는데 다시 또 클릭하면 화면에 증가가 안됨. 디비에선 증가되고
			hit = $(btn).parents('.panel').find('.h_span').text();
			//hit은 지금 string이야
			//그래서 hit++못해 따라서 형변환해줘야함
			hit = parseInt(hit)+1;
			$(btn).parents('.panel').find('.h_span').text(hit);
			
			
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})
}




var boardUpdateServer = function(){
	
	$.ajax({
		url : '/board/boardUpdate.do',
		type : 'post',
		data : board, //seq, writer, subject, content, password, mail
		success : function(res){
			alert(res.sw); 
			listPageServer(currentPage);
			
			//성공하면, 이제 화면수정 - 수정하는 modal창에 있는 값을 다시 가져와서(board객체) 화면에 출력한다. 
			$(pbody).find('.n_span').text(board.writer);
			$(pbody).find('.m_span').text(board.mail);
			
			content = board.content;
			content = content.replace(/\n/g, "<br>");
			
			$(pbody).find('.c_span').html(content);
			$(pbody).find('a').text(board.subject);
			
			//날짜는 그냥 오늘날짜로 바꾸는거지. 디비에서 그거 하나만 셀렉하려고 하진 않을거야
			today = new Date();
			today = today.toLocaleDateString(); //Date를 쓰면 날짜만 나오고 그거 안쓰면 시간도 같이 나옴
			//이렇게 만들어놓은 투데이를 이제 넣어야지			
			$(pbody).find('.w_span').text(today);
			
			
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
}


var boardDeleteServer = function(btn){ //btn - 삭제버튼
	
	$.get(
			'/board/boardDelete.do',
			{"seq" : v_idx},
			function(res){
			//	alert(res.sw);
				//화면에서 지우기 - 이건 ajax는 비동기야. 화면에 있는 것만 없애면 돼.
				
				$(btn).parents('.panel').remove();
				
			},
			'json'
	)
	
}


var boardSaveServer = function(){
	
	$.ajax({
		url : '/board/boardSave.do',
		data : $('#w_form').serializeJSON(),
		type : 'post',
		dataType : 'json', 
		success : function(res){
			//alert(res.sw);
			listPageServer(1);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
}


var replyDeleteServer = function(btn){ //btn댓글 삭제 버튼 - 지금 막 이름 지어준거임
	
	$.ajax({
		url : '/board/replyDelete.do',
		type : 'get',//data가 없거나 간단한 데이터는 get으로 가고 중요하거나 많으면 post로 간다.
		data : {"renum" : v_idx},
		success: function(res){
			//성공 
			//비동기이니깐 html의 replyDeleteServer(); 거기서안하고, 여기서 success하고 나면 그 댓글 화면을 삭제할거야
			//alert(res.sw);
			
			$(btn).parents('.rep').remove(); // 이걸 해줘야지 db에서도 사라지고, 댓글창 화면에서도 사라짐.
			
			
		},
		error :  function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json' 	
	})
	
//	ajax는 안에 들어가는 애들 순서가 바뀌어도 되는데
//	단축메뉴로 할때는 순서가 정해져있음. ppt5 p10에 있음
	
 
	
//	$.get(
//			'/board/replyDelete.do',
//			{"renum" : v_idx},
//			 function(res){
//				alert(res.sw);
//			},
//			'json' 
//	)
	
	
	
	
}




var replyModifyServer = function(){
	$.ajax({
		url : '/board/replyModify.do',
		type : 'post',
		data : {"renum" : v_idx, "cont" : modify_cont},
		success : function(res){
			alert(res.sw);//이렇게하면 성공인지 실패인지 찍겠지
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json' 
	})
}





var replyListServer = function(insert_btn){  //insert_btn : 댓글등록버튼!!! 이건..아래서 보낸거때문에 쓰는거??
											// 제목을 클릭하면 a태그가 this야.
	
	$.ajax({
		url : '/board/ReplyList.do',
		type : 'post',
		data : {"bonum" : v_idx}, // 얘는 reply.bonum 이거 하나만 가져가는거라 {}여기안에 이렇게 넣어주는거?
		//reply를 거쳐오고 안거쳐오고 차이때문에 reply.bonum 이걸 => v_idx 이걸로 바꿈
		//v_idx 이걸로 바꿔서넣을건데. 이건 전역변수라서 여기서도 사용 가능
		success :function(res){
			
			$(insert_btn).parents('.panel').find('.pbody').find('.rep').remove();
			
			code ='';
			$.each(res, function(i,v){ //res[i].name 이거 대신에 v.name으로 쓸수있다~~
				
				code += 	'<div class="panel-body rep">';
				code += 		'<p class="p1">';
				code += 			v.name + ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.redate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += 			'<br><br><span class="cont">' + v.cont+ '</span>'
				code += 		'</p>';
				code += 		'<p class="p2">';
				code += 			'<button type="button" idx="' + v.renum + '" name="r_modify" class="action">댓글 수정</button>';
				code += 			'<button type="button" idx="' + v.renum + '" name="r_delete" class="action">댓글 삭제</button>';
				code += 		'</p>'
				code += '</div>'
					
			})
			
			$(insert_btn).parents('.panel').find('.pbody').append(code); //parent는 직속부모 parents하면 저 위에 조상?
		},
		error :function(xhr){
			alert("상태 : " + xhr.status)
		},
		dataType : 'json'
	})
}

var replySaveServer = function(insert_btn){ //insert_btn : 등록버튼!!! html에서가져온거야
		
	$.ajax({
		url : '/board/ReplySave.do',
		type : 'post',
		data : reply, //bonum, name, cont ====> 얘는 객체를 가져갈까 reply하나에 통으로 가져가는거고
		success :function(res){
			replyListServer(insert_btn); //성공하면 html에서 가져온 insert_btn이걸 다시 넣어주고 		
		},
		error :function(xhr){
			alert("상태 : " + xhr.status) // 이건에러 날거를 생각해서 만드는거니까 완성본에서 에러는 없어도됨
		},
		dataType : 'json'
	})
}




//페이지별 리스트 - html에서 listPageServer(2) 호출 - 2페이지란 뜻
//페이지3개씩만 가져와라 혹은 5개씩 가져와라 이런식으로
//cpage 변수는 페이지번호이고 controller로 전송한다. 

// v는 res[i]이거였는데 이걸 res.dataArr[i]로 바꾼거지
// 그게  $.each(res.dataArr, function(i,v)  여기서==>  res.dataArr 이거
// 근데 어차피 v로 받아서 결과는 안바뀌고 똑같

var listPageServer = function(cpage){
	$.ajax({
		url : '/board/List.do',
		type : 'post',
		data : {"page" : cpage},
		dataType : 'json',
		success : function(res){
			
			code = '<div class="panel-group" id="accordion">';
			$.each(res.dataArr, function(i,v){
				
				code += '<div class="panel panel-default">';
				code += 	'<div class="panel-heading">';
				code +=			'<h4 class="panel-title">';
				code += 			'<a name="list" class= "action" idx="' +v.seq+ '" data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.seq + '">' + v.title + '</a>';
				code += 		'</h4>';
				code += 	'</div>';
				code += 	'<div id="collapse' + v.seq + '" class="panel-collapse collapse">';
				code += 		'<div class="panel-body pbody">';
				code += 			'<p class="p1">';
				code += 				'작성자 : <span class="n_span">' + v.name + '</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메일 : <span class="m_span">' + v.mail + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += 				'조회수 : <span class="h_span">' + v.hit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성날짜 : <span class="w_span">' + v.wdate + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += 			'</p>';
				code += 			'<p class="p2">';
				code += 				'<button type="button" idx="' + v.seq + '" name="modify" class="action">수정</button>';
				code += 				'<button type="button" idx="' + v.seq + '" name="delete" class="action">삭제</button>';
				code += 			'</p>'
				code += 			'<hr>';
				code += 			'<p class="p3"><span class="c_span">';
				code += 				v.cont;
				code += 			'</span></p>';
				code += 			'<p class="p4">';
				code += 				'<textarea class="area" cols="60"></textarea>';
				code += 				'<button type="button" idx="' + v.seq + '"  name="reply" class="action rep_b">댓글등록</button>';
				code += 			'</p>';
				code += 		'</div>';
				code += 	'</div>';
				code += '</div>';
			})
			code += '</div>';	
			
			$('.box').html(code);
			
			
			
			//출력할때 html로 하거나 append로 할수있어  ==> html은 지우고 append는 내용지우고 append하는거야/ append할때 empty()이거 안해주면 옆에가서 계속 붙음 
			//pageList에 append를 이용해서 출력. 이거 쓰려면 비워줘야해 그래서 empty. 혹 remove하면 내용이 다 지워져서 안돼
			
			$('#pageList').empty();
			//이전버튼
			if(res.sp > 1){ //previous는 1쪽에는 필요없으니까
				pager = '<ul class="pager">';
				pager += '<li><a href="#" class="prev">Previous</a></li>';
				pager += '</ul>';
				$('#pageList').append(pager);
			}

			
			//페이지번호 출력
			pager = '<ul class="pagination pager">'; 
			// 위에서 pager 어팬딩해줘서 다시 = 으로 시작
			
			for(i=res.sp; i<=res.ep; i++){
				if(currentPage == i){
					pager += '<li class="active"><a class="paging" href="#">' + i + '</a></li>';
				}else{
					pager += '<li><a class="paging" href="#">' + i + '</a></li>';
				}
			}
			pager += '</ul>';
			$('#pageList').append(pager); //다시 출력하기위해서 어팬딩해주고
			
			
			//다음 버튼 출력
			if(res.ep < res.tp){
				pager = '<ul class="pager">';
				pager += '<li><a href="#" class="next">Next</a></li>';
				pager += '</ul>';
				$('#pageList').append(pager);
			}
			
				
//			pager = '<li><a href="#">Next</a></li>';
			
		},
		error : function(xhr){
			alert("상태 : " + xhr.status)
		}
		
		
		
	})//ajax
}



var listAll = function(){
	//게시글 가져오기 - ajax써야지
	$.ajax({
		url : '/board/List.do',
		type : 'get',
//		type : 'post',
		data : {"page" : 1},
		dataType : 'json',
		success : function(res){
			
			code = '<div class="panel-group" id="accordion">';
			$.each(res, function(i,v){
				
				code += '<div class="panel panel-default">';
				code += 	'<div class="panel-heading">';
				code +=			'<h4 class="panel-title">';
				code += 			'<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.seq + '">' + v.title + '</a>';
				code += 		'</h4>';
				code += 	'</div>';
				code += 	'<div id="collapse' + v.seq + '" class="panel-collapse collapse">';
				code += 		'<div class="panel-body">';
				code += 			'<p class="p1">';
				code += 				'작성자 : ' + v.name + ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메일 : ' + v.mail + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += 				'조회수 : ' + v.hit + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성날짜 : ' + v.wdate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				code += 			'</p>';
				code += 			'<p class="p2">';
				code += 				'<button type="button" idx="' + v.seq + '" name="modify" class="action">수정</button>';
				code += 				'<button type="button" idx="' + v.seq + '" name="delete" class="action">삭제</button>';
				code += 			'</p>'
				code += 			'<hr>';
				code += 			'<p class="p3">';
				code += 				v.cont;
				code += 			'</p>';
				code += 			'<p class="p4">';
				code += 				'<textarea class="area" cols="60"></textarea>';
				code += 				'<button type="button" idx="' + v.seq + '"  name="reply" class="action rep_b">댓글등록</button>';
				code += 			'</p>';
				code += 		'</div>';
				code += 	'</div>';
				code += '</div>';
			})
			code += '</div>';	
			
			$('.box').html(code);
			
		},
		error : function(xhr){//XMLHttpRequest
			alert("상태 : " + xhr.status)
		}
	})
}