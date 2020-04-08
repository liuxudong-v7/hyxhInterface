package com.hyxh.service;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.PersonalVip;
import com.hyxh.util.ResultData;

public interface PersonalVIPService {
	ResultData registered(PersonalVip use);

	ResultData getmobilephone(PersonalVip use );
	
	ResultData getdraftmobilephone(PersonalVip use );
	
	ResultData draft(PersonalVip use,String type);
	
	ResultData addinvoice(Invoice invoice);
}
