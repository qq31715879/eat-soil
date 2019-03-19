package com.hug.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hugl.core.redis.BZRedis;

/**
 * @Description 考区驾驶证页面
 * @author chenjian
 * @date 2018年9月1日 下午2:07:40
 */
@Controller
@RequestMapping("/web")
public class WebController {

	@ResponseBody
	@RequestMapping(value = "/{key}")
	public Object view(@PathVariable String key) throws Exception {
		return BZRedis.INSTANCE.get(key);
	}
}
