package com.hyxh.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.PersonalVip;

@Mapper
public interface PersonalVIPDao  {
	int registered(PersonalVip use);
	List<?> getMobilephone(PersonalVip use);
	List<?> queryAll();
	int adddraft(PersonalVip use);
	int updatedraft(PersonalVip use);
	List<?> getdraftMobilephone(PersonalVip use);
	int addinvoice(Invoice invoice);
}
