// JavaScript Document
/*<!--页面自适应js-->*/
rem(720);

function rem(baseWidth) {
    var root = document.documentElement;
    var w = root.clientWidth;
    w = w > 720 ? 720 : w < 320 ? 320 : w;
    var fz = w / baseWidth * 100;
    root.style.fontSize = fz + "px";
}


//隐藏弹窗
function hide(popid) {
    $(popid).hide();
    $('#mask').hide();
};
//显示弹窗
function show(popid) {
    $(popid).show();
    $('#mask').show();
};

// 弹窗-点击空白处关闭
$(function(){
    clickHidePop("#formPop","#defaultPop")
})

function clickHidePop(el1,el2){
    var maskBg = $("#popMask"); 
    var mainPop1 = $(el1);
    var mainPop2 = $(el2);

    maskBg.not(mainPop1,mainPop2).on("click",function(){
        maskBg.hide();
        mainPop1.hide();
        mainPop2.hide();
    })
}

function stopPropagation(e) {
    if (e.stopPropagation){
        e.stopPropagation();
    }
    else{
        e.cancelBubble = true;
    }
}

// nav下拉框切换
$(function () {
    var nav = $("#nav");
    var list = nav.find("li");
    var hasList = nav.find(".lis");
    
    hasList.on("mouseover",function(){
        var cur = $(this);
        $(this).find(".down-arr").addClass("up-arr");
        cur.find(".lis_hidden").show();
    });
    hasList.on("mouseout",function(){
        var cur = $(this);
        cur.find(".lis_hidden").hide();
        $(".down-arr").removeClass("up-arr");
    });


    list.on("mouseover",function(){
        $(this).find("a").addClass("hover");
    });
    list.on("mouseout",function(){
        $(this).find("a").removeClass("hover");
    });


    
    // 点击空白部分隐藏
    $(document).bind('click', function () {
        $('.lis_hidden').hide();
    });
    // 阻止冒泡
    $('.lis').bind('click', function (e) {
        stopPropagation(e);
    });
});

//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
$(function () {
    $(window).scroll(function(){
        if ($(window).scrollTop()>100){
            $("#toTop").fadeIn(1500);
        }else
        {
            $("#toTop").fadeOut(1500);
        }
    });
    //当点击跳转链接后，回到页面顶部位置
    $("#toTop").click(function(){
        $('body,html').animate({scrollTop:0},1000);
        return false;
    });
});





































