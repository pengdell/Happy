package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.AptDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.naver.NewsService;

import io.swagger.annotations.ApiOperation;

@Controller
public class MainController {
	
	@Autowired
	NewsService news;
	
	@ApiOperation(value = "메인 페이지로 이동한다")
    @GetMapping("/")  //main page로 이동
    public String index() { 
    	return "index";
    }
	
	@ApiOperation(value = "검색어에 대한 뉴스를 제공한다.",produces="application/json", response=NewsDto.class)
	@GetMapping("/news/{keyword}")
	public ResponseEntity<List<NewsDto>> getNews(@PathVariable String keyword) throws Exception {
		return new ResponseEntity<List<NewsDto>>(news.getNews(keyword), HttpStatus.OK);
	}
	
	
}
