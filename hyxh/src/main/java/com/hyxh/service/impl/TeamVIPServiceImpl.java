package com.hyxh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyxh.dao.PersonalVIPDao;
import com.hyxh.dao.TeamVIPDao;
import com.hyxh.entity.TeamPhotos;
import com.hyxh.entity.TeamRegister;
import com.hyxh.service.TeamVIPService;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@Service
public class TeamVIPServiceImpl implements TeamVIPService{
	@Autowired
    private TeamVIPDao dao;
    
	/**
	 * @Transactional  事务回滚
	 */
	@Override
	@Transactional
	public ResultData registered(TeamRegister team) {
		try {
			int id = dao.registered(team);
			if (id != 0) {
				return ResultData.success(team);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "注册失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResultData teamPhotoadd(TeamPhotos photo) {
		
		try {
			int id = dao.teamphotoadd(photo);
			if (id != 0) {
				return ResultData.success(photo);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "存储文件失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	public ResultData getdraftmobilephone(TeamRegister team) {
		
		try {
			List list = dao.getdraftMobilephone(team);
			if (list.size()!=0) {
				return ResultData.fail(ResponseCode.ERROR.val(), "手机号已存在", list);
			}else {
				return ResultData.success(list,"手机号不存在");
			}
			
		} catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	public ResultData draft(TeamRegister team, String type) {
		try {
			int id ;
			if (type.equals("0")) {
				id = dao.adddraft(team);
			}else{
				id = dao.updatedraft(team);
			}
			
			if (id != 0) {
				return ResultData.success(team);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "添加草稿箱失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

}
