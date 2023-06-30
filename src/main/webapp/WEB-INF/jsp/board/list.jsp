<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>  
<%@ include file="../common/header.jsp" %>

    	<div class="board-wrap">
        <div class="board-title">
            <h1 >풀집 커뮤니티 게시판 <i class=xi-message-o></i></h1>
   			<c:set var="amounts" value="5,10,25,50,100"/>
            <select class="amount">
              <c:forTokens items="${amounts}" delims="," var="amount">
                 <option value="${amount}" ${page.cri.amount == amount ? 'selected' : ''}>${amount}개씩보기</option>                                      
              </c:forTokens>
            </select>
            
           <div class="list-header">
            <form class="searchFree">
                 <input type="hidden" name="page" value="1">
                 <input type="hidden" name="amount" value="10">
                 <input type="hidden" name="category" value="1">
                 <div class="seachCheck">
	                  <div class="seachCheckBox">
	           			 <input class="searchTitle" type="checkbox" id="check1" name="type" value="title" ${fn:contains(fn:join(page.cri.type, ','), 'title') ? 'checked' : ''}>
	 					 <label class="" for="check1">제목</label>
	           			 <input class="searchContent" type="checkbox" id="check2" name="type" value="content" ${fn:contains(fn:join(page.cri.type, ','), 'content') ? 'checked' : ''}>
	 					 <label class="" for="check2">내용</label>
	           			 <input class="searchWriter" type="checkbox" id="check3" name="type" value="writer" ${fn:contains(fn:join(page.cri.type, ','), 'writer') ? 'checked' : ''}>
	 					 <label class="" for="check3">작성자</label>
	                  </div>
	                  <div class="searchBox">
	                     <input type="text" class="searchInput" placeholder="Search" name="keyword" value="">
	                     <button class="searchButton" type="submit"><i class="fas fa-search"></i></button>
	                   </div>
                  </div>
             </form>
            </div>
             
        </div>
        <div class="board-list-wrap">
            <div class="board-list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">글쓴이</div>
                    <div class="date">작성일</div>
                    <div class="count">조회</div>
                </div>    
              <c:forEach var="board" items="${boards}" varStatus="stat">
                <div>
                    <div class="num">${board.bno}</div>
                    <div class="title"><a href="view?bno=${board.bno}&${page.cri.fullQueryString}">${board.title}</a><span class="small text-muted">[${board.replyCnt}]</span></div>
                    <div class="writer">${board.writer}</div>
                    <div class="date">${board.regdate}</div>
                    <div class="count">${board.hitcount}</div>
                </div>
              </c:forEach>
            </div>

            
            <div class="board-page">
            <c:if test="${page.doublePrev}">>
                <a class="bt first" href="list?pageNum=${page.startPage-1}&${page.cri.queryString}"><i class="fas fa-angle-double-left"></i></a>
            </c:if>
            <c:if test="${page.prev}">
                <a class="bt prev" href="list?pageNum=${page.cri.pageNum-1}&${page.cri.queryString}"><i class="fas fa-angle-left"></i></a>
            </c:if>
			  <c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
	             <a class="num focus ${page.cri.pageNum == i ? 'active' : '' }" href="list?pageNum=${i}&${page.cri.queryString}">${i}</a>
	          </c:forEach>
	          <c:if test="${page.next}">
                <a class="bt next" href="list?pageNum=${page.cri.pageNum+1}&${page.cri.queryString}"><i class="fas fa-angle-right"></i></a>
	          </c:if>
	          <c:if test="${page.doubleNext}">
                <a class="bt last" href="list?pageNum=${page.endPage+1}&${page.cri.queryString}"><i class="fas fa-angle-double-right"></i></a>
	          </c:if>
            </div>
            <form class="frm" name="frm" method="get">
                    <a href="write" class="on2">게시글 작성</a>
            </form> 
        </div>
    </div>
                


<%@ include file="../common/footer.jsp" %>

</body>
<script>
$(".amount").change(function(){

	let page = '${page.cri.pageNum}';
	let amount = $(this).val();
	let category = '${page.cri.category}';
	let type = '${page.cri.typeStr}';
	
	let obj = {pageNum:page , amount:amount, category : category};
	location.search = $.param(obj) + type;
})
$(".list-header :checkbox:checked").length ? '' : $(".list-header :checkbox:eq(0)").prop("checked", true);

$(".list-header form").submit(function(){
	if(!$(this).find(":checkbox:checked").length || !$(this).find(":text").val().trim()) {
		alert("검색 타입을 지정하고 검색어를 입력하세요")
		return false;
	}
})
</script>
</html>