package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "사용자의 관심지역을 저장")
public class InterestDto {
	@ApiModelProperty(value = "사용자 아이디")
	private String id;
	@ApiModelProperty(value = "관심지역의 구군코드")
	private String gugunCode;
	@ApiModelProperty(value = "관심지역의 동이름")
	private String dongName;
	public InterestDto(String id, String gugunCode, String dongName) {
		super();
		this.id = id;
		this.gugunCode = gugunCode;
		this.dongName = dongName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "InterestDto [id=" + id + ", gugunCode=" + gugunCode + ", dongName=" + dongName + "]";
	}

}
