package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "질문글에 대한 답변을 나타낸다.")
public class AnswerDto {
	@ApiModelProperty(value = "답변 번호")
	private int no;
	@ApiModelProperty(value = "질문글 번호")
	private int boardno;
	@ApiModelProperty(value = "답변 작성자")
	private String userid;
	@ApiModelProperty(value = "답변 내용")
	private String content;
	@ApiModelProperty(value = "답변 작성시간")
	private String regtime;
	public AnswerDto(int no, int boardno, String userid, String content, String regtime) {
		super();
		this.no = no;
		this.boardno = boardno;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
		return "AnswerDto [no=" + no + ", boardno=" + boardno + ", userid=" + userid + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	
	
}
