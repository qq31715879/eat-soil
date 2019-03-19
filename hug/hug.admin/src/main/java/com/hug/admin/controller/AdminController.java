package com.hug.admin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hug.core.string.BZString;
import com.hug.dao.bean.BusinessTravel;
import com.hug.dao.bean.Driver;
import com.hug.dao.bean.LegalPerson;
import com.hug.dao.bean.Wifi;
import com.hugl.core.redis.BZRedis;

/**
 * @Description 后台请求
 * @author chenjian
 * @date 2018年9月1日 下午2:07:40
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@ResponseBody
	@RequestMapping(value = "/driver")
	public boolean saveDriver(Driver driver, String driverRulesStr, String driverDatasStr) throws Exception {
		driver.setDriverRules(toArray(driverRulesStr));
		driver.setDriverDatas(toArray(driverDatasStr));
		return BZRedis.INSTANCE.set("driver", driver);
	}

	@ResponseBody
	@RequestMapping(value = "/businesstravel")
	public boolean savebusinessTravel(BusinessTravel businesstravel, String nodeTitlesStr, String nodeContentsStr)
			throws Exception {
		businesstravel.setNodeTitles(toArray(nodeTitlesStr));
		businesstravel.setNodeContents(toArray(nodeContentsStr));
		return BZRedis.INSTANCE.set("businesstravel", businesstravel);
	}

	@ResponseBody
	@RequestMapping(value = "/travel")
	public boolean saveTravel(BusinessTravel businesstravel, String nodeTitlesStr, String nodeContentsStr)
			throws Exception {
		businesstravel.setNodeTitles(toArray(nodeTitlesStr));
		businesstravel.setNodeContents(toArray(nodeContentsStr));
		return BZRedis.INSTANCE.set("travel", businesstravel);
	}

	@ResponseBody
	@RequestMapping(value = "/wifi")
	public boolean saveWifi(@RequestBody Wifi wifi) throws Exception {
		return BZRedis.INSTANCE.set("wifi", wifi);
	}

	@ResponseBody
	@RequestMapping(value = "/legalperson")
	public boolean saveLegalperson(@RequestBody LegalPerson legalperson) throws Exception {
		return BZRedis.INSTANCE.set("legalperson", legalperson);
	}

	@ResponseBody
	@RequestMapping(value = "/select/{key}")
	public Object view(@PathVariable String key) throws Exception {
		return BZRedis.INSTANCE.get(key);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	@SuppressWarnings("unchecked")
	public boolean save(String json) throws Exception {
		Map<String, String> map = JSON.parseObject(json, Map.class);
		for (String key : map.keySet()) {
			BZRedis.INSTANCE.set(key, map.get(key));
		}
		return true;
	}

	private List<String> toArray(String string) {
		return Arrays.asList(BZString.subLastOne(string).split(","));
	}
}
