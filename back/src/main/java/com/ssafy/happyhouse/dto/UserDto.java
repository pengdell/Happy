package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "유저 상세 정보를 나타낸다.")
public class UserDto {
	@ApiModelProperty(value = "아이디")
	private String id;
	@ApiModelProperty(value = "비밀번호")
	private String pw;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "전화번호")
	private String phone;
	
	public UserDto(String id, String pw, String name, String address, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
}
