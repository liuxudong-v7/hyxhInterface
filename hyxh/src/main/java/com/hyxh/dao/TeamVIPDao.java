package com.hyxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyxh.entity.TeamPhotos;
import com.hyxh.entity.TeamRegister;
import com.hyxh.entity.PersonalVip;

@Mapper
public interface TeamVIPDao {
	int teamphotoadd(TeamPhotos photo);
	
	List<?> getdraftMobilephone(TeamRegister team);
	
	int adddraft(TeamRegister team);
	
	int updatedraft(TeamRegister team);
	
	int registered(TeamRegister team);
}
