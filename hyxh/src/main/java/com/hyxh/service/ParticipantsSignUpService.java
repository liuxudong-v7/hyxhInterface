package com.hyxh.service;

import java.util.List;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.ParticipantsSignUp;
import com.hyxh.util.ResultData;

public interface ParticipantsSignUpService {
	ResultData registered(ParticipantsSignUp partic);
	ResultData getMobilephone(ParticipantsSignUp partic);
	ResultData queryAll();
	ResultData adddraft(ParticipantsSignUp partic,String type);
	ResultData getdraftMobilephone(ParticipantsSignUp partic);
	ResultData addinvoice(Invoice invoice);
}
