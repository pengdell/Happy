package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dao.Answer;
import com.ssafy.happyhouse.dto.AnswerDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	private Answer answerDao;
	
	@ApiOperation(value = "질문글에 대한 모든 답변을 보여준다.", response=AnswerDto.class)
	@GetMapping("{boardno}")
	public ResponseEntity<List<AnswerDto>> showList(@PathVariable int boardno) throws SQLException{
		List<AnswerDto> list = answerDao.select(boardno);
		return new ResponseEntity<List<AnswerDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "답변을 작성한다.")
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody AnswerDto answer) throws Exception{
		int cnt = answerDao.insert(answer);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "답변을 수정한다.")
	@PutMapping
	public ResponseEntity<String> updateBoard(@RequestBody AnswerDto answer) throws Exception{
		int cnt = answerDao.update(answer);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "답변을 삭제한다.")
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) throws Exception{
		int cnt = answerDao.delete(no);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
}
