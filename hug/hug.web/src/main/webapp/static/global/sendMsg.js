function  sendMsg(pa1, pa2,pa3) {
	var i = 0;
	var arr = [];
	 $(pa1+" input[type='text']").each(function(){
		 arr[i++] =  $(this).val();
		});
	 $(pa1+" input[type='checkbox']").each(function(){
		 arr[i++] =  $(this).is(':checked');
  		});
	 
	 if (arr[0] == '') {
		hide(pa2);
		hide(pa3);
		 return;
	 }
	 $.ajax({
    		url:"../../sendRecord/save",
    		type: "POST",
    		data: {arr:JSON.stringify(arr)},
    		success: function(data) {
    		}
    		});

	hide(pa2);
	hide(pa3);
}
var BZContactInfo = {
		load : function(){
			var dd;
			 $.ajax({
					url:"../../homepage/getInfo",
					type: "GET",
					async : false,
					data: {key: 'contacttable'},
					success: function(data) {
						if(data == undefined || data == ""){
						}else{
							dd = JSON.parse(data);
							$(".icon-tel").text(dd[0].tel);
							$(".icon-email").text(dd[0].mail);
						}
					}
			 });
		}
}