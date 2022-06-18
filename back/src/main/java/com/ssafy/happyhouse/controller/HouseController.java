package com.ssafy.happyhouse.controller;

import java.io.IOException;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.AptDealDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.ssafy.happyhouse.dao.AptDeal;

@Controller
@RequestMapping("/house")
@Api(value="SSAFY")
public class HouseController{
	
	@Autowired
	private AptDeal aptDealDao;


    @ApiOperation(value = "이름으로 아파트와 주택을 검색한다",produces="application/json", response=AptDealDto.class)
	@GetMapping("/searchname")  //이름으로 아파트, 주택 검색
	public ResponseEntity<? extends Object> search(@RequestParam("name") String name) throws SQLException {
		List<AptDealDto> aptDealList = aptDealDao.selectbyName(name);
		if(aptDealList.size()!=0) 
			return new ResponseEntity<List<AptDealDto>>(aptDealList, HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.OK) ;
	}
    
    @ApiOperation(value = "시도 정보를 반환한다")
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptDealDao.getSido(), HttpStatus.OK);
	}
	
    @ApiOperation(value = "구군 정보를 반환한다")
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptDealDao.getGugunInSido(sido), HttpStatus.OK);
	}
	
    @ApiOperation(value = "동 정보를 반환한다")
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugunName") String gugunName, @RequestParam("sidoName") String sidoName) throws Exception {
		SidoGugunCodeDto dto = new SidoGugunCodeDto();
		dto.setSidoName(sidoName);
		dto.setGugunName(gugunName);
		return new ResponseEntity<List<HouseInfoDto>>(aptDealDao.getDongInGugun(dto), HttpStatus.OK);
	}
	
    @ApiOperation(value = "아파트 정보를 반환한다")
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(aptDealDao.getAptInDong(dong), HttpStatus.OK);
	}    
	
}

