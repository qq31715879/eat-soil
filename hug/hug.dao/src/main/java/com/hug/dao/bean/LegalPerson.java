package com.hug.dao.bean;

import java.io.Serializable;
import java.util.List;

/**
* @Description 法人页面
* @author chenjian
* @date 2018年9月4日 下午9:22:32
 */
public class LegalPerson implements Serializable {
	private static final long serialVersionUID = 5717703697770815977L;

	private String headTitle;
	
	private String title1;
	
	private String p1Json;
	
	private String button1;
	
	private String p2Json;
	
	private String button2;
	private String title2;
	
	
	private String pTitle;
	private List<String> prs;
	
	private String title3;
	private String dt;
	private String dd;
	
	private String p3;
	
	private String qaJson;
	
	private String p4;
	
	private String title4;
	
	private String ciJson;
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getP1Json() {
		return p1Json;
	}
	public void setP1Json(String p1Json) {
		this.p1Json = p1Json;
	}
	public String getButton1() {
		return button1;
	}
	public void setButton1(String button1) {
		this.button1 = button1;
	}
	public String getP2Json() {
		return p2Json;
	}
	public void setP2Json(String p2Json) {
		this.p2Json = p2Json;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public List<String> getPrs() {
		return prs;
	}
	public void setPrs(List<String> prs) {
		this.prs = prs;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	public String getQaJson() {
		return qaJson;
	}
	public void setQaJson(String qaJson) {
		this.qaJson = qaJson;
	}
	public String getP4() {
		return p4;
	}
	public void setP4(String p4) {
		this.p4 = p4;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getCiJson() {
		return ciJson;
	}
	public void setCiJson(String ciJson) {
		this.ciJson = ciJson;
	}
	public String getButton2() {
		return button2;
	}
	public void setButton2(String button2) {
		this.button2 = button2;
	}
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	
}