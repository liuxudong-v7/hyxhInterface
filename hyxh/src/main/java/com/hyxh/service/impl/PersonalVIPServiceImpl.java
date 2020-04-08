package com.hyxh.service.impl;


import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyxh.dao.PersonalVIPDao;
import com.hyxh.entity.Invoice;
import com.hyxh.entity.PersonalVip;
import com.hyxh.service.PersonalVIPService;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@Service
public class PersonalVIPServiceImpl implements PersonalVIPService{
	@Autowired
    private PersonalVIPDao dao;
    
	/**
	 * @Transactional  事务回滚
	 */
	@Override
	@Transactional
	public ResultData registered(PersonalVip use) {
		try {
			int userId = dao.registered(use);
			if (userId != 0) {
				return ResultData.success(use);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "注册失败");
			}
		}catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}

	
	@Override
	@Transactional
	public ResultData getmobilephone(PersonalVip use) {
		try {
			List<?> list = dao.getMobilephone(use);
			if (list.size()!=0) {
				return ResultData.fail(ResponseCode.ERROR.val(), "手机号已被注册，请直接登录", list);
			}else {
				return ResultData.success(list,"该手机号可以注册");
			}
			
		} catch (Exception e) {
			return ResultData.fail(ResponseCode.ERROR.val(), "异常："+e.getMessage());
		}
	}
	
	/**
	 * 查询草稿箱手机号是否存在
	 */
	@Override
	@Transactional
	public ResultData getdraftmobilephone(PersonalVip use) {
		try {
			List<?> list = dao.getdraftMobilephone(use);
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
	@Transactional
	public ResultData draft(PersonalVip use,String type) {
		
		try {
			int userId ;
			if (type.equals("0")) {
				userId = dao.adddraft(use);
			}else{
				userId = dao.updatedraft(use);
			}
			
			if (userId != 0) {
				return ResultData.success(use);
			}else {
				return ResultData.fail(ResponseCode.ERROR.val(), "添加草稿箱失败");
			}
		}catch (Exception e) {
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
