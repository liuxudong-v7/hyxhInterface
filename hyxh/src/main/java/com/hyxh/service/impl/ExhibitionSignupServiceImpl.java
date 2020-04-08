package com.hyxh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyxh.dao.ExhibitionSignupDao;
import com.hyxh.dao.PersonalVIPDao;
import com.hyxh.entity.ExhibitionSignup;
import com.hyxh.entity.Questionnaire;
import com.hyxh.service.ExhibitionSignupService;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@Service
public class ExhibitionSignupServiceImpl implements ExhibitionSignupService{
	@Autowired
    private ExhibitionSignupDao dao;
    
	/**
	 * @Transactional  事务回滚
	 */
	@Override
	@Transactional
	public ResultData registered(ExhibitionSignup exhibition) {
		try {
			int id = dao.registered(exhibition);
			if (id != 0) {
				return ResultData.success(exhibition);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "注册失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResultData draft(ExhibitionSignup exhibition,String type) {
		try {
			int userId ;
			if (type.equals("0")) {
				userId = dao.adddraft(exhibition);
			}else{
				userId = dao.updatedraft(exhibition);
			}
			
			if (userId != 0) {
				return ResultData.success(exhibition);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "添加草稿箱失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}


	@Override
	@Transactional
	public ResultData getdraftmobilephone(ExhibitionSignup exhibition) {
		try {
			List<?> list = dao.getdraftMobilephone(exhibition);
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
	public ResultData questionnaire(Questionnaire questionnaire) {
		try {
			int userId ;
			userId = dao.questionnaire(questionnaire);
			if (userId != 0) {
				return ResultData.success(questionnaire);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "添加问卷失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

}
