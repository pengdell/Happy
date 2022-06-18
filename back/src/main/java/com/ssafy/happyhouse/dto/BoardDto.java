package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "게시판 정보를 나타낸다")
public class BoardDto {
	@ApiModelProperty(value = "글 번호")
	private int no;
	@ApiModelProperty(value = "글 작성자")
	private String userid;
	@ApiModelProperty(value = "글 제목")
	private String subject;
	@ApiModelProperty(value = "글 내용")
	private String content;
	@ApiModelProperty(value = "글 작성시간")
	private String regtime;
	
	public BoardDto(int no, String userid, String subject, String content, String regtime) {
		super();
		this.no = no;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", userid=" + userid + ", subject=" + subject + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}

	

	
}