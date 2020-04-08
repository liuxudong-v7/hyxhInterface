package com.hyxh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.PersonalVip;
import com.hyxh.service.PersonalVIPService;
import com.hyxh.util.GetUuid;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@RestController
@RequestMapping("/personal")
public class PersonalVIPController {
	
	@Autowired
    private PersonalVIPService service;
	private ResultData resu;
	
	public PersonalVIPController() {
		resu = new ResultData();
	}
	
	/**
	 * 注册个人会员
	 * @return
	 */
    @RequestMapping(value = "/registered",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResultData registered(PersonalVip use) {
		use.setUserId(GetUuid.getUUID());
		use.setState(ResponseCode.STATENO.val());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        use.setRegisterDate(df.format(new Date()));
		resu = service.registered(use);
		
		return resu;
	}
    
    /**
	 * 注册个人会员草稿箱
	 * @return
	 */
    @RequestMapping(value = "/draft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResultData draft(PersonalVip use) {
		use.setState(ResponseCode.STATENO.val());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        use.setRegisterDate(df.format(new Date()));
		//查询手机号是否存在
		resu = service.getdraftmobilephone(use);
		if (resu.getCode().equals("500")) {//已存在，修改
			List list = (List) resu.getData();
			Map map = (Map) list.get(0);
			use.setUserId((String) map.get("user_id"));
			use.setMobilephone((String) map.get("mobilephone"));
			resu = service.draft(use,"1");
		}else {//不存在,保存草稿箱
			use.setUserId(GetUuid.getUUID());
			use.setMobilephone(use.getMobilephone());
			resu = service.draft(use,"0");
		}
		
		return resu;
	}
    
    /**
     * 	草稿表	查询手机号是否已注册
     */
    @RequestMapping(value = "/getmobilephonedraft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResultData getmobilephonedraft(String mobilephone) {
    	PersonalVip use = new PersonalVip();
    	use.setMobilephone(mobilephone);
    	resu = service.getdraftmobilephone(use);
    	return resu;
    }
    
    /**
     * 	正式表	查询手机号是否已注册
     */
    @RequestMapping(value = "/getmobilephone",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResultData getmobilephone(String mobilephone) {
    	PersonalVip use = new PersonalVip();
    	use.setMobilephone(mobilephone);
    	resu = service.getmobilephone(use);
    	return resu;
    }
    
    /**
     *	 发票
     */
    @RequestMapping(value = "/invoice",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultData invoice(Invoice invoice) {
    	invoice.setInvoiceId(GetUuid.getUUID());
    	resu = service.addinvoice(invoice);
    	return resu;
    }
}
