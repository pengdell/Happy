package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;

@Mapper
public interface Board {
	public int insert(BoardDto board)throws SQLException;//add,append
	public int update(BoardDto board)throws SQLException;//update,modify
	public int delete(int no)throws SQLException;//특정 한개 삭제  delete,remove
	public BoardDto select(int no)throws SQLException;// 한개 조회   //select,search,find
	public List<BoardDto> selectAll()throws SQLException;//모든 조회   select,search,find
}
