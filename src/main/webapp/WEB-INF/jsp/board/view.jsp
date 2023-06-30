<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>  
<%@ include file="../common/header.jsp" %>
	
		<div class="detail">
	        <div class="detail-head">
	            <div class="top">
	                <div class="num">${board.bno}</div>
	                <div class="title">${board.title}</div>
	                <div class="writer">${board.writer}</div>
	                <div class="date">${board.regdate}</div>
	                <div class="count">${board.hitcount}</div>
	            </div>   
	        </div> 
	        <div class="detail-writer">
				<textarea id="content" placeholder="Enter content" name="content" rows="10" readonly>${board.content}</textarea>
	        </div>
	        
	        <div class="mb-3">
			    <p class="">files</p>
			    <c:forEach items="${board.attachs}" var ="attach">
			    <p><a href="${pageContext.request.contextPath}/download?${attach.queryString}">${attach.origin}</a></p>
			    </c:forEach>
			  </div>
	        
	        <div class="detail-btn">
	            <a href="list?${cri.fullQueryString}" class="on">목록</a>
	            <a href="modify?bno=${board.bno}&${cri.fullQueryString}" >수정</a>
	            <a href="remove?bno=${board.bno}&${cri.fullQueryString}" class="remove">삭제</a>
	        </div>
	    </div>
      	<div class="replyArea"  id="#replyArea">
            <p for="writer" class=""><i class="fas fa-comment-dots"></i> replies</p>
            <div class="commentArea">
                 <textarea class="" id="commentArea" placeholder="Enter commets" rows="3"></textarea>	
                 <button type="button" class="">댓글 작성</button>  
            </div>
         </div>         
            <ul class="replies">
                <li class="">
                    <div class="">
                      <p>댓글이 없습니다</p> 
                    </div>
                </li>
            </ul> 
  

<%@ include file="../common/footer.jsp" %>

<script>
	$(".remove").click(function(){
		return confirm("정말 삭제 하시겠습니까?");
	});

	let contextPath ='${pageContext.request.contextPath}';
	let replyPath = contextPath + "/reply"
	let bno = '${board.bno}';
	let writer ='${member.id}';
	
	showList();
	function showList(){
	$.ajax({
		url : replyPath,
		data : {bno:bno},
		success : list
	})
  }
	// 댓글조회 목록  
    function list(replies){
    // let jsonStr='[{"rno":2,"content":"댓글댓글","regDate":"2023-03-13 12:57:46","writer":"id1","bno":513},{"rno":4,"content":"댓글댓글","regDate":"2023-03-13 12:57:51","writer":"id1","bno":513},{"rno":5,"content":"댓글댓글","regDate":"2023-03-13 12:57:52","writer":"id1","bno":513}]';
    // let replies = JSON.parse(jsonStr);
    // console.log(replies)
    let str = "";
    if(!replies.length){
    	str = ` <li class="list-unstyled px-4">
            		<div class="mb-4 small text-center">
            			<p>댓글이 없습니다</p> 
          			</div>
       			 </li>`;
        $(".replies").html(str);
        return;
    }
    
    for(let i in replies){
        let r = replies[i];
        console.log(r.rno, r.content, r.regDate, r.writer, r.bno);
        let isMine = writer === r.writer
		// let isMine = true
        str += `
                    <li class="list-unstyled px-4" data-rno="\${r.rno}">
                      <div class="row"> 
                            <a class="text-muted small mb-3 col text-decoration-none" href="">
                                <span class="fs-6 fw-bold">\${r.writer}</span>
                                <span class="mx-5">\${r.regDate}</span>
                            </a>
                			<div class="col text-end ">`;
                	str +=	isMine ? '<a href="" class="text-muted"><i class="fas fa-trash"></i></a>' : ''
                 	str +=	`</div>
                        </div>
                      	<div class="mb-4 small">
                        <p>\${r.content}</p> 
                      </div>
                    </li>
        `;
    }
    $(".replies").html(str);
  }
	
 // 댓글 작성 
    $("#commentArea").next().click(function(){
        let content = $("#commentArea").val()
        if(!writer){
        	alert("로그인 후 작성하세요");
        	location.href = contextPath + "/member/login?href=" + encodeURIComponent(location.pathname + location.search + '#replyArea');
        	return;
        }
        else if(!content){
        	alert("댓글 내용을 입력하세요")
        	return;
        }
        $.ajax({
        	url : replyPath,
        	data : {bno:bno, content:content, writer:writer},
        	method : "POST",
        	success : function(data){
        		alert("댓글이 성공적으로 작성되었습니다");
        		$("#commentArea").val("");
        		showList();
        	}
        })
    })

    // 댓글 삭제
    $(".replies").on("click", "li a.text-muted", function(){
        event.preventDefault();
        if (!confirm("댓글을 삭제하시겠습니까?")){
        	return false;
        }        
        let rno = $(this).closest("li").data("rno");
        $.ajax({
        	url : replyPath + "?rno=" + rno,
        	method : "DELETE",
        	success : function(data){
        		if(data == 1){
	        		alert("댓글이 성공적으로 삭제되었습니다");
	        		showList();
        		}
        	}
        })
    })
</script>

</body>
</html>