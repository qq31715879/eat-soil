package com.hug.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hug.dao.bean.SendRecord;
import com.hugl.core.redis.BZRedis;

@Controller
@RequestMapping("/sendRecord")
public class SendRecordController {
	
	private static final String RECORD_KEY = "sendRecord";
	
	@ResponseBody
	@RequestMapping(value = "/getInfo",method = RequestMethod.GET)
	public JSONObject getInfo(int page, int limit) throws Exception {
		List<SendRecord> returnList = new ArrayList<SendRecord>();
		int start = (page - 1) * limit;
		int end = page * limit;
		int count =  BZRedis.INSTANCE.llen(RECORD_KEY);
		List<String> list = BZRedis.INSTANCE.lrange(RECORD_KEY,start, end);
		for (String s : list) {
			SendRecord record = new SendRecord();
			String[] newString = s.replace("[", "").replace("]", "").split(",");
			for (int i = 0; i<newString.length; i ++) {
				newString[i] = newString[i].replaceAll("\"", "");
			}
			record.setName(newString[0]);
			record.setTel(newString[1]);
			record.setCompany(newString[2]);
			record.setIs1(newString[3]);
			record.setIs2(newString[4]);
			record.setIs3(newString[5]);
			record.setIs4(newString[6]);
			record.setIs5(newString[7]);
			record.setIs6(newString[8]);
			returnList.add(record);
		}
		
		 JSONObject returnObj = new JSONObject();
		 returnObj.put("code", 0);
		 returnObj.put("count", count);
		 returnObj.put("data", returnList);
		 return returnObj;
	}

}
