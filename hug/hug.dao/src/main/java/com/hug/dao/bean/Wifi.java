package com.hug.dao.bean;

import java.io.Serializable;
import java.util.List;

public class Wifi implements Serializable {
	private static final long serialVersionUID = -3380730263759820607L;

	private String headTitle;

	private String p1;
	/**
	 * 需要换行
	 */
	private String p2;
	/**
	 * 蓝色标题块
	 */
	private String p3Title;
	private String p3Content;
	/**
	 * 中间可添加块
	 */
	private List<String> p4s;
	private String p4sJson;
	/**
	 * 椭圆快
	 */
	private String p5;
	/**
	 * 下划线
	 */
	private String p6;

	private String wifiP1;
	private String time;
	private String detail;
	private String title;
	private String head;
	/**
	 * WIFI内容
	 */
	private List<String> lis;

	private List<String> divs;
	private String divsJson;

	private String tips;

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3Title() {
		return p3Title;
	}

	public void setP3Title(String p3Title) {
		this.p3Title = p3Title;
	}

	public String getP3Content() {
		return p3Content;
	}

	public void setP3Content(String p3Content) {
		this.p3Content = p3Content;
	}

	public List<String> getP4s() {
		return p4s;
	}

	public void setP4s(List<String> p4s) {
		this.p4s = p4s;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getWifiP1() {
		return wifiP1;
	}

	public void setWifiP1(String wifiP1) {
		this.wifiP1 = wifiP1;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public List<String> getLis() {
		return lis;
	}

	public void setLis(List<String> lis) {
		this.lis = lis;
	}

	public List<String> getDivs() {
		return divs;
	}

	public void setDivs(List<String> divs) {
		this.divs = divs;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getP4sJson() {
		return p4sJson;
	}

	public void setP4sJson(String p4sJson) {
		this.p4sJson = p4sJson;
	}

	public String getDivsJson() {
		return divsJson;
	}

	public void setDivsJson(String divsJson) {
		this.divsJson = divsJson;
	}

	public String getHeadTitle() {
		return headTitle;
	}

	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
}