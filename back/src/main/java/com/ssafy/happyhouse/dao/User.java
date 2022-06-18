package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.InterestDto;
import com.ssafy.happyhouse.dto.UserDto;

@Mapper
public interface User {
	public int insert(UserDto data)throws SQLException;//add,append
	public int update(UserDto data)throws SQLException;//update,modify
	public int delete(String id)throws SQLException;//특정 한개 삭제  delete,remove
	public UserDto select(String id, String pw)throws SQLException;// 한개 조회   //select,search,find

	public int insertInterest(InterestDto interest) throws SQLException;
	public int deleteInterest(InterestDto interest) throws SQLException;
	public List<InterestDto> selectInterest(String id) throws SQLException;
}