<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>  
<%@ include file="../common/header.jsp" %>

	 <form class="siginUp_frm" name="siginUp_frm" method="post"> 
        <h1>회원가입</h1> 
            <input id="id" name="id" placeholder="아이디를 입력하세요" ><br>
            <!-- <button onclick="idCheck()" type="button" value="아이디 중복확인" name="idcheck">아이디 중복확인</button> -->
            <input type ="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요"><br>
            <input type ="password" id="pw2" name="pw2" placeholder="비밀번호 확인을 입력하세요"><br>
            <input type ="text" id="name" name="name" placeholder="이름을 입력하세요"><br>
            <output name="result"></output>
            <button type="button" onclick="siginup()" name="joinbtn">가입하기</button>
     </form>
     
     <%
/*      	Object result = request.getAttribute("idCheck");
     	String id = (String)request.getAttribute("id"); */
     %>


<%@ include file="../common/footer.jsp" %>

<script>

function siginup(){
    let id = document.getElementById("id");
    let pw = document.getElementById("pw");
    let pw2 = document.getElementById("pw2");
    let name = document.getElementById("name");

    if (id.value.trim().length <= 2){
        alert("아이디를 2글자 이상으로 입력해주세요")
        return false;
    }else if(pw.value.trim().length <= 3){
        alert("비밀번호를 4글자 이상으로 입력해주세요")
        return false;
    }else if(pw2.value !== pw.value){
        alert("비밀번호와 비밀번호 확인이 서로 일치하지 않습니다")
        return false;
    }else if(name.value.trim().length <= 1){
        alert("이름을 2글자 이상 입력해주세요")
        return false;
    }else {
        alert("회원가입을 축하합니다 로그인 해주세요!")
    }
    document.siginUp_frm.submit();
}
</script>

</body>
</html>