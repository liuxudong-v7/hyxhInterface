package com.hyxh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyxh.dao.ParticipantsSignUpDao;
import com.hyxh.dao.PersonalVIPDao;
import com.hyxh.entity.Invoice;
import com.hyxh.entity.ParticipantsSignUp;
import com.hyxh.service.ParticipantsSignUpService;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@Service
public class ParticipantsSignUpServiceImpl implements ParticipantsSignUpService{
	@Autowired
    private ParticipantsSignUpDao dao;
    
	/**
	 * @Transactional  事务回滚
	 */
	@Override
	@Transactional
	public ResultData registered(ParticipantsSignUp partic) {
		try {
			int userId = dao.registered(partic);
			if (userId != 0) {
				return ResultData.success(partic);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "注册失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResultData getMobilephone(ParticipantsSignUp partic) {
		try {
			List<?> list = dao.getMobilephone(partic);
			if (list.size()!=0) {
				return ResultData.fail(ResponseCode.ERROR.val(), "手机号已被注册，请直接登录", list);
			}else {
				return ResultData.success(list,"该手机号可以注册");
			}
			
		} catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResultData queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResultData adddraft(ParticipantsSignUp partic,String type) {

		try {
			int userId ;
			if (type.equals("0")) {
				userId = dao.adddraft(partic);
			}else{
				userId = dao.updatedraft(partic);
			}
			
			if (userId != 0) {
				return ResultData.success(partic);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "添加草稿箱失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
		
	}


	@Override
	@Transactional
	public ResultData getdraftMobilephone(ParticipantsSignUp partic) {
		try {
			List<?> list = dao.getdraftMobilephone(partic);
			if (list.size()!=0) {
				return ResultData.fail(ResponseCode.ERROR.val(), "手机号已存在", list);
			}else {
				return ResultData.success(list,"手机号不存在");
			}
			
		} catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}
	
	/**
	 * 发票保存
	 */
	@Override
	@Transactional
	public ResultData addinvoice(Invoice invoice) {
		try {
			int invoiceId = dao.addinvoice(invoice);
			if (invoiceId != 0) {
				return ResultData.success(invoice);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "注册失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}



}
