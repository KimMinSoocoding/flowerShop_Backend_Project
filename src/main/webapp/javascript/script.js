/**
 * 
 */

$(function(){
        $('.rolling').slick({
            dots:false,
            slidesToShow: 1,
            slidesToScroll:1,
            infinite: true,
            autoplay: true,
            autoplaySpeed: 2000,
            fade: false,
            vertical: false,
            arrows: true,
            prevArrow: "<a>Prev</a>",
            nextArrow: "<a>Next</a>",
            prevArrow: '<i class="xi-angle-left-min prev-btn"></i>',
            nextArrow: '<i class="xi-angle-right-min next-btn"></i>'
        }); 
    });
    
  function siginup(){
    let id = document.getElementById("id");
    let pw = document.getElementById("pw");
    let pw2 = document.getElementById("pw2");
    let name = document.getElementById("name");

    if (id.value.trim().length <= 4){
        alert("아이디를 5글자 이상으로 입력해주세요")
        return false;
    }else if(pw.value.trim().length <= 4){
        alert("비밀번호를 5글자 이상으로 입력해주세요")
        return false;
    }else if(pw2.value !== siginUpPw.value){
        alert("비밀번호와 비밀번호 확인이 서로 일치하지 않습니다")
        return false;
    }else if(name.value.trim().length <= 1){
        alert("이름을 2글자 이상 입력해주세요")
        return false;
    }else {
        alert("회원가입을 축하합니다")
    }
    document.siginUp_frm.submit();
}