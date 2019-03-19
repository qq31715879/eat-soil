package com.hug.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hugl.core.redis.BZRedis;

@Controller
@RequestMapping("/sendRecord")
public class SendRecordController {
	
	private static final String RECORD_KEY = "sendRecord";
	@ResponseBody
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public boolean saveHomePage(String arr) throws Exception {
    	System.out.println(arr);
    	BZRedis.INSTANCE.lpush(RECORD_KEY, arr);
    	return true;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getInfo",method = RequestMethod.GET)
	public String getInfo(String key) throws Exception {
		System.out.println((String)BZRedis.INSTANCE.get(key));
		return BZRedis.INSTANCE.get(key);
	}

}
