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
                <h2>회원정보</h2>
            </div>
      	<div class="card-body">
      	  <form method="post">
      	      <div class="writer-title">
			    <label for="name" class="form-label">회원이름</label>
			    <input type="text" class="form-control" id="title" placeholder="Enter title" name="name" value="${member.name}">
			  </div>
			  <div class="writer-content">
			    <label for="id" class="form-label">회원 아이디 </label>
			    <textarea class="form-control" id="content" placeholder="Enter content" name="id" rows="10" readonly>${member.id}</textarea>			 
			  </div>
			  <div class="writer-wrtier">
			    <label for="regdate" class="form-label">가입일자</label>
			    <input type="text" class="form-control" id="regdate" placeholder="Enter writer" name="regdate" value="${member.regdate}" readonly>
			  </div>
			  	<input type="hidden" id="pw" name="pw" value="${member.pw}">
      		  <div class="writer-btn" id="writer_btn">
				<button class="on4">수정완료</button>
				<!-- <button class="on4">회원탈퇴</button> -->
		        <a href="${pageContext.request.contextPath}" class="#">홈으로</a> 	 
		        <a href="${pageContext.request.contextPath}" class="#">회원탈퇴</a> 	 
	          </div>
          </form>
        </div>
     </div>
  </main>


<%@ include file="../common/footer.jsp" %>

</body>
</html>