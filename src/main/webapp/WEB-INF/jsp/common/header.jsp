<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <header>
           <div class="top-banner">
               <div class="top-banner-logo"> 
                   <a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/images/banner-logo.png" alt=""/></a>
               </div>
            
            <div class="top-banner-middle">
                <a href=""><span>Flowers Shop</span></a>
                <a href=""><span>About</span></a>
                <a href=""><span>Classes</span></a>
                <a href=""><span>Scents</span></a>
                <a href=""><span>Private</span></a>
            </div>

            <div class="search">
                <!-- <p1>login</p1>
                <p2>Register</p2> -->
                <input type="text" value="Search">
                <i class=xi-search></i>
            </div>
        </div>
    </header>
    
    <div class="menu">
       <div class="board-box"> 
            <a href="${pageContext.request.contextPath}" class="board home"></a>
        </div>
        <div class="dropDown">
           <a href="${pageContext.request.contextPath}/board/list" class="board free"></a>
           <div class="none-dropDown">
               <a href="#" class="board Notice"></a> 
               <a href="#" class="board free2"></a>
           </div>
       </div>
    </div>