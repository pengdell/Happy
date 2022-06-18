package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.AptDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

@Mapper
public interface AptDeal {
	public int insert(AptDealDto data)throws SQLException;//add,append
	public int update(AptDealDto data)throws SQLException;//update,modify
	public int delete(String name)throws SQLException;//특정 한개 삭제  delete,remove

	public List<AptDealDto> select(String areaCode, String dong)throws SQLException;// 한개 조회   //select,search,find
	public List<AptDealDto> selectbyName(String name)throws SQLException;// 한개 조회   //select,search,find
	public List<AptDealDto> selectAll()throws SQLException;//모든 조회   select,search,find

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(SidoGugunCodeDto data) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;	
	
}
