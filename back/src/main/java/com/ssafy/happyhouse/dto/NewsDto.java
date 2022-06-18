package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "뉴스 정보를 저장한다")
public class NewsDto {
	@ApiModelProperty(value="뉴스 제목")
	public String title;
	@ApiModelProperty(value = "뉴스 요약")
	public String description;
	@ApiModelProperty(value = "뉴스 일자")
	public String pubDate;
	@ApiModelProperty(value = "뉴스 링크")
	public String link;
	public NewsDto(String title, String description, String pubDate, String link) {
		super();
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "NewsDto [title=" + title + ", description=" + description + ", pubDate=" + pubDate + ", link=" + link
				+ "]";
	}
	
	
	
	
}//NewsDto
