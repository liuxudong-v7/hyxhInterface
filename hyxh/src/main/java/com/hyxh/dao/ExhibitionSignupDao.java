package com.hyxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyxh.entity.ExhibitionSignup;
import com.hyxh.entity.PersonalVip;
import com.hyxh.entity.Questionnaire;

@Mapper
public interface ExhibitionSignupDao {
	int registered(ExhibitionSignup exhibition);
	
	int adddraft(ExhibitionSignup exhibition);
	
	int questionnaire(Questionnaire questionnaire);
	
	List<?> getdraftMobilephone(ExhibitionSignup exhibition);
	
	int updatedraft(ExhibitionSignup exhibition);
}
