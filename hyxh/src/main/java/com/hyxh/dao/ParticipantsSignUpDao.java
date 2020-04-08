package com.hyxh.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.ParticipantsSignUp;
import com.hyxh.entity.PersonalVip;
import com.hyxh.util.ResultData;

@Mapper
public interface ParticipantsSignUpDao  {
	int registered(ParticipantsSignUp partic);
	List<?> getMobilephone(ParticipantsSignUp partic);
	List<?> queryAll();
	int adddraft(ParticipantsSignUp partic);
	int updatedraft(ParticipantsSignUp partic);
	List<?> getdraftMobilephone(ParticipantsSignUp partic);
	int addinvoice(Invoice invoice);
}
