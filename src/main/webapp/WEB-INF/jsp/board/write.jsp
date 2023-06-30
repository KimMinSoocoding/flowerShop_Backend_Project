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
                <h2>Board Write</h2>
            </div>
      	<div class="card-body" enctype="multipart/form-data">
      	  <form method="post">
      	      <div class="writer-title">
			    <label for="email" class="form-label">제목</label>
			    <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" >
			  </div>
			  <div class="writer-content">
			    <label for="content" class="form-label">내용</label>
			    <textarea class="form-control" id="content" placeholder="Enter content" name="content" rows="10"></textarea>			 
			  </div>
			  <div class="writer-wrtier">
			    <label for="writer" class="form-label">작성자</label>
			    <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" value="${member.id}" readonly>
			  </div>
			  <div class="writer-files">
			    <label for="file" class="form-label">files</label>
			    <input type="file" class="form-control" id="file" name="file" multiple>
			  </div>
      		  <div class="writer-btn" id="writer_btn">
				<button class="on4">글쓰기</button>
		        <a href="list" class="#">목록으로</a> 	 
	          </div>
          </form>
        </div>
     </div>
  </main>

<%@ include file="../common/footer.jsp" %>

</body>
</html>