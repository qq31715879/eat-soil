package com.hug.dao.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 主页信息
 */
public class HomePage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4409008925459832347L;
	//首页最上部图片url
    private String homePageTopImgUrl;
    //首页最上部文字
    private String homePageTopWord;
    //首页最上部气泡文字
    private String homePageTopBubleWord;

    //第二部分开始====================
    //主标题
    private String part2MainTitle;
    //副标题
    private String part2SubTitle;
    //对话
    private List<Map<String, String>> dialog;
    //第二部分结束====================


    //第三部分开始=================
    //主标题
    private String part3MainTitle;
    //副标题
    private String part3SubTitle;
    //第三部分结束==================

    //第四部分开始======================
    //主标题
    private String part4MainTitle;
    //副标题
    private String part4SubTitle;
    //图片
    private List<ImageInfo> images;
    //第四部分结束======================


    //第五部分开始========================
    //主标题
    private String part5MainTitle;
    //副标题
    private String part5SubTitle;
    //图片
    private String part5ImgUrl;
    //第五部分结束=========================

    //页脚开始
    //主标题
    private String footerMainTitle;
    //副标题
    private String footerSubtile;
    //页脚结束


    public String getHomePageTopImgUrl() {
        return homePageTopImgUrl;
    }

    public void setHomePageTopImgUrl(String homePageTopImgUrl) {
        this.homePageTopImgUrl = homePageTopImgUrl;
    }

    public String getHomePageTopWord() {
        return homePageTopWord;
    }

    public void setHomePageTopWord(String homePageTopWord) {
        this.homePageTopWord = homePageTopWord;
    }

    public String getHomePageTopBubleWord() {
        return homePageTopBubleWord;
    }

    public void setHomePageTopBubleWord(String homePageTopBubleWord) {
        this.homePageTopBubleWord = homePageTopBubleWord;
    }

    public String getPart2MainTitle() {
        return part2MainTitle;
    }

    public void setPart2MainTitle(String part2MainTitle) {
        this.part2MainTitle = part2MainTitle;
    }

    public String getPart2SubTitle() {
        return part2SubTitle;
    }

    public void setPart2SubTitle(String part2SubTitle) {
        this.part2SubTitle = part2SubTitle;
    }

    public List<Map<String, String>> getDialog() {
        return dialog;
    }

    public void setDialog(List<Map<String, String>> dialog) {
        this.dialog = dialog;
    }

    public String getPart3MainTitle() {
        return part3MainTitle;
    }

    public void setPart3MainTitle(String part3MainTitle) {
        this.part3MainTitle = part3MainTitle;
    }

    public String getPart3SubTitle() {
        return part3SubTitle;
    }

    public void setPart3SubTitle(String part3SubTitle) {
        this.part3SubTitle = part3SubTitle;
    }

    public String getPart4MainTitle() {
        return part4MainTitle;
    }

    public void setPart4MainTitle(String part4MainTitle) {
        this.part4MainTitle = part4MainTitle;
    }

    public String getPart4SubTitle() {
        return part4SubTitle;
    }

    public void setPart4SubTitle(String part4SubTitle) {
        this.part4SubTitle = part4SubTitle;
    }

    public List<ImageInfo> getImages() {
        return images;
    }

    public void setImages(List<ImageInfo> images) {
        this.images = images;
    }

    public String getPart5MainTitle() {
        return part5MainTitle;
    }

    public void setPart5MainTitle(String part5MainTitle) {
        this.part5MainTitle = part5MainTitle;
    }

    public String getPart5SubTitle() {
        return part5SubTitle;
    }

    public void setPart5SubTitle(String part5SubTitle) {
        this.part5SubTitle = part5SubTitle;
    }

    public String getPart5ImgUrl() {
        return part5ImgUrl;
    }

    public void setPart5ImgUrl(String part5ImgUrl) {
        this.part5ImgUrl = part5ImgUrl;
    }

    public String getFooterMainTitle() {
        return footerMainTitle;
    }

    public void setFooterMainTitle(String footerMainTitle) {
        this.footerMainTitle = footerMainTitle;
    }

    public String getFooterSubtile() {
        return footerSubtile;
    }

    public void setFooterSubtile(String footerSubtile) {
        this.footerSubtile = footerSubtile;
    }
}
