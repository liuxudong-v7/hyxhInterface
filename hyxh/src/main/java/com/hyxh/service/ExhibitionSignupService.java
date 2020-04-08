package com.hyxh.service;

import com.hyxh.entity.ExhibitionSignup;
import com.hyxh.entity.Questionnaire;
import com.hyxh.util.ResultData;

public interface ExhibitionSignupService {
	ResultData registered(ExhibitionSignup exhibition);
	
	ResultData draft(ExhibitionSignup exhibition,String type);
	
	ResultData getdraftmobilephone(ExhibitionSignup exhibition );
	
	ResultData questionnaire(Questionnaire questionnaire );
}
