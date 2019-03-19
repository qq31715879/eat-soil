package com.hug.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hugl.core.redis.BZRedis;


@Controller
@RequestMapping("/homepage")
public class HomePageController {

	@ResponseBody
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public boolean saveHomePage(String key, String value) throws Exception {
    	System.out.println(key);
    	System.out.println(value);
    	value = value.replaceAll("\n", "<br>");
    	BZRedis.INSTANCE.set(key,value);
    	return true;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getInfo",method = RequestMethod.GET)
	public String getInfo(String key) throws Exception {
		System.out.println((String)BZRedis.INSTANCE.get(key));
		return BZRedis.INSTANCE.get(key);
	}

}
