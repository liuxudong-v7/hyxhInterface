package com.hyxh.service;

import com.hyxh.entity.TeamPhotos;
import com.hyxh.entity.TeamRegister;
import com.hyxh.entity.PersonalVip;
import com.hyxh.util.ResultData;

public interface TeamVIPService {
	ResultData registered(TeamRegister team);
	
	ResultData teamPhotoadd(TeamPhotos photo);
	
	ResultData getdraftmobilephone(TeamRegister team);
	
	ResultData draft(TeamRegister team,String type);
}
