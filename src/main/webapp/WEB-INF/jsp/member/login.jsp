<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>  
<%@ include file="../common/header.jsp" %>

    <form name="sigin_frm" method="post"> 
       <h1>로그인</h1> 
           <label for="id"></label><br>
           <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" ><br>
           <label for="pw"></label><br> 
           <input type ="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요"><br>
           <div class="mb-3">
              <p class="text-danger small">${param.msg}</p>
           </div>
           <input type="checkbox" namd="chk" id="chk" value="remember-me"> 아이디 저장<br>
           <output name="result" id="result"></output>
           <button type="submit" onclick="sigin()">로그인</button>
    </form>

<%@ include file="../common/footer.jsp" %>

</body>
</html>