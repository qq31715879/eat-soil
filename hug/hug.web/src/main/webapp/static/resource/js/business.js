(function($){

    
	//隐藏弹窗
	function hide(popid){     //需要通过事件调用：onclick="hide('#pop1')"
	  $(popid).hide();        
	  $('#mask').hide();
	}
	//显示弹窗
	function show(popid){
	  $(popid).show();
	  $('#mask').show();
	}

    /* tab切换的公用方法 */
    /* 参数说明：
      tab_u:string tab标签(JQ Selector)
      tab_b:string tab切换的内容(JQ Selector)
      e:string 鼠标的动作['click'|'mouseover']
    */
    function initTab(tabs, contents, e){
        e = e || 'mouseover';
        tabs = $(tabs).not('.split');
        contents = $(contents);

        contents.not(':first').hide();
        tabs.on(e, function(){
            var tab = $(this);
            var index = tabs.index(tab);
            tabs.removeClass('selected');
            tab.addClass('selected');
            contents.hide().eq( index ).show();
        }).eq(0).addClass('selected');
    }

	window.pub_tab = initTab;
	window.show = show;
	window.hide = hide;
})(jQuery);

 




