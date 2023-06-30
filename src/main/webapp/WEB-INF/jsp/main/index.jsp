<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="../common/head.jsp" %>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<!-- 롤링 & 로그인섹션 구간 -->
    <div class="rolling">
        <div class="rolling-img">
            <img src="./images/flower1.png" alt="">
            <div class="rolling-title">
                <p1>Flower Class</p1>
                <h1>이번주의 프리미엄 꽃 <br> 'OPEN'</h1>
                <p2>꽃 시장에서도 만날 수 없는 <br> 프리미엄 꽃 파티!<br></p2>
                <a href=""><span>알록달록한 세상 30% 할인중</span></a>
            </div>
        </div>
    
        <div class="rolling-img">
            <img src="./images/flower2.png" alt="">
            <div class="rolling-title">
                <p1>Flower Class</p1>
                <h1>" 넌 모르잖아 알록달록한 세상 " <br> 봄 특집 ' 더 플로리 '</h1>
                <p2>플라워 샵이 알려 주고 싶은 알록달록한 세상, <br> 재준이도 예솔이도 초대하고 싶은 플라워 클래스 <br></p2>
                <a href=""><span>알록달록한 세상 30% 할인중</span></a>
            </div>
        </div>
        <div class="rolling-img">
            <img src="./images/flower3.png" alt="">
            <div class="rolling-title">
                <p1>Flower Class</p1>
                <h1>" NEW 화병 셀렉션 " <br> 두 번째 'OPEN'</h1>
                <p2>플라워 인테리어 감각을 높여줄 <br> 화병 신상품이 새로 입고되었습니다<br></p2>
                <a href=""><span>알록달록한 세상 30% 할인중</span></a>
            </div>
        </div>
        <div class="rolling-img">
            <img src="./images/flower4.png" alt="">
            <div class="rolling-title">
                <p1>Flower Class</p1>
                <h1>플로리스트 챌린지<br>2기모집!<br></h1>
                <p2>선착순 100명! 늦기전에 신청하세요<br></p2>
                <a href=""><span>알록달록한 세상 30% 할인중</span></a>
            </div>
        </div>
     </div>

    <div class="flower-text">
        <h1>풀집</h1>
        <p>보배롭게 쓰임 받는  꽃 <br> 취향의 식물 <br> 그리고 꽃고 식물에서 비롯된 향 <br> 우리 플라워 샾에는 꽃과 식물과 향이 당신의 일상의 한 부분이 되어 삶의 질을 한 층 더 높여줄 작업을 당신의 감성을 일깨우는 디자인을 합니다</p>
    </div>
    
    <aside class="login">
    	<div class="frm">
		<%if(session.getAttribute("member") == null) { %>
            <h1>정기구독</h1>
            <br>
            <p>100만 명이 선택한 꽃 구독 이제 시작하세요</p>
            <br>
            <a href="member/login"><button><span>로그인</span></button></a>
            <aside class="id">
                <a href="Signup"><span>회원가입</span></a>
                <a href="#"><span>ID/PW 찾기</span></a>
            </aside>
    	</div>
    	<% } else {%>
    		<div class="text-muted">
	            	<p> ${member.name}님 환영합니다</p>
            	</div>
            	<div>
            		<a href="memberinquiry" class="text-decoration-none small">마이 페이지</a>
            		<a href="member/logout" class="btn btn-danger btn-small">로그아웃</a>
            	</div>
    	<% } %>
    </aside>

    <section class="wrap">
        <h1>꽃과 식물 :: 향기나는 물건들</h1>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower1.jpg" alt="">
                </div>
                <div>
                    <h3>몬스테라 선인장</h3>
                    <h4>70,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower2.jpg" alt="">
                </div>
                <div>
                    <h3>휘카스 움베르타</h3>
                    <h4>85,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower3.jpg" alt="">
                </div>
                <div>
                    <h3>몬스테라 테라조</h3>
                    <h4>55,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower4.jpg" alt="">
                </div>
                <div>
                    <h3>함베이 선인장</h3>
                    <h4>60,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower5.jpg" alt="">
                </div>
                <div>
                    <h3>몬드레안</h3>
                    <h4>100,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
        <div class="card">
            <a href="">
                <div>
                    <img src="./images/cardFlower6.jpg" alt="">
                </div>
                <div>
                    <h3>필로덴드론</h3>
                    <h4>50,000원</h4>
                    <p>SALE</p>
                </div>
            </a>
         </div>
    </section>

    <div class="event">
        <h1>우리 :: 풀집은</h1>
        <a href=""><img src="./images/flowerEvent1.png" alt=""></a>
        <p>직접 꽃을 만지며 수 많은 종류의 꽃들과 함께 플라워 클래스!</p>
        <a href=""><img src="./images/flowerEvent2.png" alt=""></a>
        <p>특별한 이유가 있을 때만이 아닌 일상에서도 꽃을 즐기는 문화를 지향하는 플라워 기반 라이프 스타일 브랜드 풀집은 <br> 2023년 2월 런칭한 브랜드로, 꽃이 필요한 순간을 언제나 함께 합니다</p>
        <a href=""><img src="./images/flowerEvent3.png" alt=""></a>
        <p>풀집은 항상 존중·존경심을 기반으로 나 자신(yourself)과 상대방(you)를 위한 흥미로운 컨텐츠로 사랑을 표현하며, <br> 차별화된 혁신을 통한 라이프 스타일 커뮤니케이션을 지향합니다.</p>
    </div>
	
	

	<%@ include file="../common/footer.jsp" %>
	<script src="./javascript/script.js"></script>
	
</body>
</html>