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

import com.ssafy.happyhouse.dao.Board;
import com.ssafy.happyhouse.dto.BoardDto;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private Board boardDao;
	
	@ApiOperation(value = "모든 게시글 목록을 보여준다.", response=List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> showList() throws SQLException{
		List<BoardDto> list = boardDao.selectAll();
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글을 보여준다.", response=BoardDto.class)
	@GetMapping("{no}")
	public ResponseEntity<BoardDto> showItem(@PathVariable int no) throws SQLException{
		BoardDto board = boardDao.select(no);
		return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글을 작성한다.")
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) throws Exception{
		int cnt = boardDao.insert(board);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글을 수정한다.")
	@PutMapping
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto board) throws Exception{
		int cnt = boardDao.update(board);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글을 삭제한다.")
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) throws Exception{
		int cnt = boardDao.delete(no);
		
		if(cnt == 1)
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		else
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
}

