package com.hug.dao.bean;

import java.io.Serializable;

/**
 * 页面图片信息，包含
 * 1.图片url
 * 2.图片主标题
 * 3,图片副标题
 */
public class ImageInfo implements Serializable {
	private static final long serialVersionUID = 5008150751639745934L;

	private String imgUrl;

    private String mainTitle;

    private String subTitle;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
