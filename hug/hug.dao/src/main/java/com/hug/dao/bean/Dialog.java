package com.hug.dao.bean;

import java.io.Serializable;

/**
 * 对话框
 */
public class Dialog implements Serializable {
	private static final long serialVersionUID = -669323286555227925L;

	private String question;

    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
