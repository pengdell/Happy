package com.ssafy.happyhouse.dto;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(description = "아파트 거래 상세 정보를 나타낸다.") // Controller에서 @ApiOperation(value = "",produces="application/json", response=AptDealDto.class) 했어야 Swagger에 재대로 나타남
public class AptDealDto{
	@ApiModelProperty(value = "거래금액")
	private int price_deal;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "법정동")
	private String dong;
	@ApiModelProperty(value = "아파트")
	private String aptName;
	@ApiModelProperty(value = "년")
	private int deal_year;
	@ApiModelProperty(value = "월")
	private int deal_month;
	@ApiModelProperty(value = "일")
	private int deal_day;
	@ApiModelProperty(value = "전용면적")
	private String exclusivePrivateArea;
	@ApiModelProperty(value = "지번")
	private String jiNum;
	@ApiModelProperty(value = "지역코드")
	private String areaCode;
	@ApiModelProperty(value = "층")
	private int floor;
	
	public AptDealDto(){
	}
	public AptDealDto(int price_deal, int buildYear, int deal_year, String dong, String aptName, int deal_month, int deal_day, String exclusivePrivateArea, String jiNum, String areaCode, int floor){
		this();
		this.price_deal = price_deal;
		this.buildYear = buildYear;
		this.dong = dong;
		this.aptName = aptName;
		this.deal_year = deal_year;
		this.deal_month = deal_month;
		this.deal_day = deal_day;
		this.exclusivePrivateArea = exclusivePrivateArea;
		this.jiNum = jiNum;
		this.areaCode = areaCode;
		this.floor = floor;
	}
	
	public int getPrice_deal() {
		return price_deal;
	}
	public void setPrice_deal(int price_deal) {
		this.price_deal = price_deal;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getDeal_year() {
		return deal_year;
	}
	public void setDeal_year(int deal_year) {
		this.deal_year = deal_year;
	}
	public int getDeal_month() {
		return deal_month;
	}
	public void setDeal_month(int deal_month) {
		this.deal_month = deal_month;
	}
	public int getDeal_day() {
		return deal_day;
	}
	public void setDeal_day(int deal_day) {
		this.deal_day = deal_day;
	}
	public String getExclusivePrivateArea() {
		return exclusivePrivateArea;
	}
	public void setExclusivePrivateArea(String exclusivePrivateArea) {
		this.exclusivePrivateArea = exclusivePrivateArea;
	}
	public String getJiNum() {
		return jiNum;
	}
	public void setJiNum(String jiNum) {
		this.jiNum = jiNum;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	@Override
	public String toString() {
		return "AptDeal [price_deal=" + price_deal
				+ ", buildYear=" + buildYear
				+ ", dong=" + dong
				+ ", aptName=" + aptName
				+ ", deal_year=" + deal_year
				+ ", deal_month=" + deal_month
				+ ", deal_day=" + deal_day
				+ ", exclusivePrivateArea=" + exclusivePrivateArea
				+ ", jiNum=" + jiNum
				+ ", areaCode=" + areaCode
				+ ", floor=" + floor
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		return this.dong == ((AptDealDto)obj).dong &&
		this.aptName == ((AptDealDto)obj).aptName &&
		this.deal_year == ((AptDealDto)obj).deal_year &&
		this.deal_month == ((AptDealDto)obj).deal_month &&
		this.deal_day == ((AptDealDto)obj).deal_day &&
		this.floor == ((AptDealDto)obj).floor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.dong, this.aptName, this.deal_year, this.deal_month, this.deal_day, this.floor);
	}
}