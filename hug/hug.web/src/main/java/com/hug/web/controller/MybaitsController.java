package com.hug.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hug.dao.bean.User;
import com.hug.web.service.MybaitsService;

@Controller
@RequestMapping("/mapper")
public class MybaitsController {

	@Autowired
	private MybaitsService mybaitsService;

	@ResponseBody
	@RequestMapping("/map")
	public Map<Integer, User> demo() {
		return mybaitsService.selectMap();
	}
	
	public static void main(String[] args) {
		String[] demo = StringUtils.tokenizeToStringArray("a,b", ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		for (String string : demo) {
			System.out.println(string);
		}
	}
}
