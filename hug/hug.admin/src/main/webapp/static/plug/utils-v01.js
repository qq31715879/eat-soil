function isNum(num){
	if(num == undefined || isNaN(num) || !(/^\d+(?:\.\d{1,2})?$/.test(num))){
		alert("请输入有效数字！不为负数且最多保留两位小数");
		return false;
	}
	return true;
}

function checkNum(num){
	return num == undefined || isNaN(num) || num< 0 || !(/^\d+(?:\.\d{1,2})?$/.test(num));
}

function isNullOrEmpty(string){
	return string == undefined || string == "" || string == "undefined" || string == "null";
}

function ajax(url, data, success, callback){
	$.ajax({url : url, data : data, type : "post", async : true, cache : false,
		success : function(result) {
			success(result);
		}, error : function(XMLHttpRequest, textStatus){
			alert("服务器错误 : \nurl : " + url + "\ndata : " + JSON.stringify(data) + "\n" + XMLHttpRequest.status + ", " + textStatus);
        }, complete : function(XMLHttpRequest, textStatus){
        	if(callback != undefined){
        		callback();
        	}
        }
	});
}

function ajaxReturn(url, data, success, callback){
	$.ajax({url : url, data : data, type : "post", async : false, cache : false,
		success : function(result) {
			success(result);
		}, error : function(XMLHttpRequest, textStatus){
			alert("服务器错误 : \nurl : " + url + "\ndata : " + JSON.stringify(data) + "\n" + XMLHttpRequest.status + ", " + textStatus);
        }, complete : function(XMLHttpRequest, textStatus){
        	if(callback != undefined){
        		callback();
        	}
        }
	});
}

function zerofill(val, length){
	val = val + "";
	while (val.length < length) {
		val = "0" + val;
	}
	return val;
}

function toString(string){
	return isNullOrEmpty(string) ? "" : string;
}

function stringFormat(str) {
	var args = arguments, reg = new RegExp("{([1-" + args.length + "])}", "g");
	//匿名函数四个参数
	return String(str).replace(reg,
        function(regText, regIndex, textIndex, text) {
			return args[regIndex];
        }
    );
};

function bindBlurAndEnter(obj, event){
	obj.on("blur", function (){
		event(obj);
	}).on("keydown", function (e){
		if(e.keyCode == 13){
			event(obj);
		}
	});
}

function strMapToObj(strMap){
	let obj= Object.create(null);
	for (let[k,v] in strMap) {
		obj[k] = v;
	}
	return obj;
}

/**
 *map转换为json
 */
 function mapToJson(map) {
	 return JSON.stringify(strMapToObj(map));
 }
