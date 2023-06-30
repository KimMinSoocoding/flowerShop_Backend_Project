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
			    <label for="email" class="form-label">회원이름</label>
			    <input type="text" class="form-control" id="title" placeholder="Enter name" name="name" value="${member.name}" readonly>
			  </div>
			  <div class="writer-content">
			    <label for="content" class="form-label">회원 아이디 </label>
			    <textarea class="form-control" id="content" placeholder="Enter id" name="id" rows="10" readonly>${member.id}</textarea>			 
			  </div>
			  <div class="writer-wrtier">
			    <label for="writer" class="form-label">가입일자</label>
			    <input type="text" class="form-control" id="regdate" placeholder="Enter regdate" name="regdate" value="${member.regdate}" readonly>
			  </div>
      		  <div class="writer-btn" id="writer_btn">
				<button class="on4"><a href="memberModify?id=${member.id}">수정</a></button>
		        <a href="${pageContext.request.contextPath}" class="#">홈으로</a> 	 
	          </div>
          </form>
        </div>
     </div>
  </main>


<%@ include file="../common/footer.jsp" %>

</body>
</html>