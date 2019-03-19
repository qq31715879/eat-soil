
(function($){
	$(function(){	
		$('.nnprc-row').click(function(){
			var $url=$(this).data('url');
			$('.nnprc-row').removeClass('row-active');
			$(this).addClass('row-active');
			sessionStorage.setItem('src', $url);
			$('#iframe').prop('src',$url);
		});
		
		$('.nav-paneal-row-title').click(function(){
			var $this=$(this),$par=$this.parent();
			var $img=$this.find('.nav-icon img');
			var $src=$img.data('src');
			var $activesrc=$img.data('active');
			$('.nav-panel-row').removeClass('current-click');
			$par.addClass('current-click');			
			$this.toggleClass('nav-paneal-row-title-active');
			$this.find('.nav-close').toggleClass('nav-open');
			$this.next().toggle();
			if($this.find('.nav-open').length>0){
				$img.attr('src',$activesrc);
			}
			else $img.attr('src',$src);
			var $ncurr=$('.nav-panel-row').not('.current-click');
			$ncurr.find('.nav-paneal-row-title').removeClass('nav-paneal-row-title-active');
			$ncurr.find('.nav-nav-paneal-row-content').hide();
			$ncurr.find('.nav-close').removeClass('nav-open');
			$ncurr.find('.nav-icon img').each(function(){
				$(this).attr('src',$(this).data('src'));
			});
		});
		
		$(window).resize(function(){
			var $windowheight=$(window).outerHeight();
			var $windowwidth=$(window).outerWidth();
			var $navwidth=$('#nav-panel').outerWidth();
			$('#nav-panel').height($windowheight-60);
			$('#conent-panel,#iframe').width($windowwidth-$navwidth-1);
			$('#conent-panel,#iframe').height($windowheight-60);
			
		}).trigger('resize');
		
		wrapMenu();
	});

})(jQuery);

function wrapMenu(){
	var url = "";
	var menus = [
			["head", "homePage2"],
			["instructions1", "useguide1"],
			["instructions2", "useguide2"],
			["instructions3", "useguide3"],
			["instructions4", "useguide4"],
			["legalPerson", "legalperson"],
			["carRenting", "carrental"],
			["wifi", "wifi"],
			["case1", "travel"],
			["case2", "businesstravel"],
			["driver", "driver"],
			["sendrecord","sendrecord"],
			["contact","contact"]
	];
	for (var i = 0; i < menus.length; i++) {
		$("#" + menus[i][0]).data("url", url + menus[i][1] + ".html");
	}
}

                                                                                                                                                       