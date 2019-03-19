package com.hug.dao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 考取驾驶证
 * @author chenjian
 * @date 2018年9月2日 下午3:13:21
 */
public class Driver implements Serializable {

	private static final long serialVersionUID = 5128350510728455531L;
	private String headTitle;
	private String driverTitle;
	private String driverBody;
	private String driverRuleTitle;
	private List<String> driverRules;
	private String dataTitle;
	private List<String> driverDatas;

	public String getDriverTitle() {
		return driverTitle;
	}

	public void setDriverTitle(String driverTitle) {
		this.driverTitle = driverTitle;
	}

	public String getDriverBody() {
		return driverBody;
	}

	public void setDriverBody(String driverBody) {
		this.driverBody = driverBody;
	}

	public String getDriverRuleTitle() {
		return driverRuleTitle;
	}

	public void setDriverRuleTitle(String driverRuleTitle) {
		this.driverRuleTitle = driverRuleTitle;
	}

	public List<String> getDriverRules() {
		return driverRules;
	}

	public void setDriverRules(List<String> driverRules) {
		this.driverRules = driverRules;
	}

	public List<String> getDriverDatas() {
		return driverDatas;
	}

	public void setDriverDatas(List<String> driverDatas) {
		this.driverDatas = driverDatas;
	}

	public String getHeadTitle() {
		return headTitle;
	}

	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}

	public String getDataTitle() {
		return dataTitle;
	}

	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
}