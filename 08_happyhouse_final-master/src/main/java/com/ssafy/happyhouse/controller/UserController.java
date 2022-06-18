package com.ssafy.happyhouse.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dao.User;
import com.ssafy.happyhouse.dto.InterestDto;
import com.ssafy.happyhouse.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/user")
@Api(value="SSAFY")
public class UserController {
	
	//UserDao Interface 추가
    @Autowired
    private User dao;

    @ApiOperation(value = "로그인을 한다")
    @GetMapping("/login") //로그인
    public ResponseEntity<?> login(String id, String pw) throws SQLException {
		UserDto result = dao.select(id, pw);
	
		if(result != null) 
			return new ResponseEntity<UserDto>(result, HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
    }
    
    @ApiOperation(value = "회원가입을 한다")
    @PostMapping("/regist") //회원가입
    public ResponseEntity<String> regist(@RequestBody UserDto user) throws SQLException {
    	
    	int cnt = dao.insert(user);
    	//pk가 중복되면 500에러가 뜨는데 이걸 아이디가 중복된다고 넘겨주고 싶음
    	if(cnt == 1)
    		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    	else
    		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
    }
    
    
    @ApiOperation(value = "회원정보를 수정한다")
    @PutMapping("/modify") //회원정보 수정
    public ResponseEntity<String> modify(@RequestBody UserDto user) throws SQLException {
    	int cnt = dao.update(user);
    	if(cnt == 1)
    		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    	else
    		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
    
    }
    
    @ApiOperation(value = "회원탈퇴를 한다")
    @DeleteMapping("/remove/{id}") //회원 탈퇴
    public ResponseEntity<String> remove(@PathVariable String id) throws SQLException {
    	System.out.println("id " + id);
    	
    	int cnt = dao.delete(id);
    	if(cnt == 1)
    		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    	else
    		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);

    }
    
    @ApiOperation(value = "관심지역을 등록")
    @PostMapping("/interest")
    public void postInterest(@RequestBody InterestDto interest) throws SQLException{
    	System.out.println("insert" + interest);
    	dao.insertInterest(interest);	
    }
  
    
    @ApiOperation(value = "관심지역 삭제")
    @DeleteMapping("/interest")
    public void removeInterest(@RequestBody InterestDto interest) throws SQLException{
    	System.out.println("delete " + interest);
    	dao.deleteInterest(interest);
    }
    
    @ApiOperation(value = "사용자의 관심지역 얻어오기")
    @GetMapping("/interest/{id}")
    public ResponseEntity<List<InterestDto>> getInterest(@PathVariable String id) throws SQLException{
    	return new ResponseEntity<List<InterestDto>>(dao.selectInterest(id), HttpStatus.OK);
    }
}//END
