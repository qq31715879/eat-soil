package com.hug.dao.bean;

import java.io.Serializable;
import java.util.List;

/**
* @Description 旅游
* @author chenjian
* @date 2018年9月2日 下午5:42:12
 */
public class BusinessTravel implements Serializable{

	private static final long serialVersionUID = 554093478815364309L;
	
	private String headTitle;
	private String title;
	private String context;
	
	private List<String> nodeTitles;
	private List<String> nodeContents;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public List<String> getNodeTitles() {
		return nodeTitles;
	}
	public void setNodeTitles(List<String> nodeTitles) {
		this.nodeTitles = nodeTitles;
	}
	public List<String> getNodeContents() {
		return nodeContents;
	}
	public void setNodeContents(List<String> nodeContents) {
		this.nodeContents = nodeContents;
	}
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
}