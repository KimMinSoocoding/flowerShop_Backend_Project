<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>  
<%@ include file="../common/header.jsp" %>
	
	<main class="card-main">
     <div class="card">
            <div class="card-header">
                <h2>Board Modify</h2>
            </div>
      	<div class="card-body">
      	  <form method="post">
      	      <div class="writer-title">
			    <label for="email" class="form-label">title</label>
			    <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${board.title}">
			  </div>
			  <div class="writer-content">
			    <label for="content" class="form-label">content</label>
			    <textarea class="form-control" id="content" placeholder="Enter content" name="content" rows="10">${board.content}</textarea>			 
			  </div>
			  <div class="writer-wrtier">
			    <label for="writer" class="form-label">writer</label>
			    <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" value="${member.id}" readonly>
			  </div>
      		  <div class="writer-btn" id="writer_btn">
	      		   <input type="hidden" name="bno" value="${board.bno}">
		           <input type="hidden" name="pageNum" value="${cri.pageNum}">
		           <input type="hidden" name="amount" value="${cri.amount}">
		           <input type="hidden" name="category" value="${cri.category}">
	
		           <c:if test="${not empty cri.type}">
		          	<c:forEach items="${cri.type }" var="type">
		          		<input type="hidden" name="type" value="${type}">
		          	</c:forEach>
		          	<input type="hidden" name="keyword" value="${cri.keyword}">
		           </c:if>
				<button class="on4">수정완료</button>
		        <a href="list" class="#">목록으로</a> 	 
	          </div>
          </form>
        </div>
     </div>
  </main>


<%@ include file="../common/footer.jsp" %>

</body>
</html>