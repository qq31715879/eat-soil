var BZLayUI = {
		alert : function(content, area){
			layer.open({
                type: 1, btn: '关闭', btnAlign: 'c'
                ,area: area == undefined ? ['270px', '160px'] : area
                ,offset: "auto", shade: 0 //不显示遮罩
                ,content: '<div style="padding: 20px 100px;">'+content+'</div>'
                ,yes: function(){
                	layer.closeAll();
                }
			});
		},
		upload : function(id, choiceId, uploadId){
			layui.use('upload', function(){
		        var $ = layui.jquery ,upload = layui.upload;
		        //选完文件后不自动上传
		        upload.render({
		            elem: '#' + choiceId, url: '../../file/upload/' + id, auto: false, bindAction: '#' + uploadId,
		            done: function(res){
		            	BZLayUI.alert("上传成功 ! ");
		            	$("#" + choiceId + "-img").attr("src", "../../file/view/" + id + "?" + Math.random()); 
		            }
		        });
		 	});
		}
}

var BZJson = {
		 toString : function(json){
			 return JSON.stringify(json);
		 },
		 toObject : function(string){
			 return JSON.parse(string);
		 }
}