package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.AnswerDto;

@Mapper
public interface Answer {
	public int insert(AnswerDto answer)throws SQLException;//add,append
	public int update(AnswerDto answer)throws SQLException;//update,modify
	public int delete(int no)throws SQLException;//특정 한개 삭제  delete,remove
	public List<AnswerDto> select(int boardno)throws SQLException;// 질문글에 대한 답변들 불러옴
}
