package com.hyxh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyxh.entity.ExhibitionSignup;
import com.hyxh.entity.Invoice;
import com.hyxh.entity.ParticipantsSignUp;
import com.hyxh.entity.PersonalVip;
import com.hyxh.service.ExhibitionSignupService;
import com.hyxh.service.ParticipantsSignUpService;
import com.hyxh.util.GetUuid;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

/**
 * 参会
 * @author Hi
 *
 */
@RestController
@RequestMapping("/participants")
public class ParticipantsController {
	@Autowired
	private ParticipantsSignUpService service;
	private ResultData resu;
	
	public ParticipantsController() {
		resu = new ResultData();
	}
	
	/**
	 * 参会报名
	 * @return
	 */
    @RequestMapping(value = "/registered",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
	public ResultData registered(ParticipantsSignUp participan) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	participan.setParticipantsId(GetUuid.getUUID());
    	participan.setState(ResponseCode.STATENO.val());
    	participan.setRegisterDate(df.format(new Date()));
    	resu = service.registered(participan);
    	
		return resu;
	}
    
    /**
   	 * 参会报名草稿箱
   	 * @return
   	 */
    @RequestMapping(value = "/draft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
   	public ResultData draft(ParticipantsSignUp participan) {
    	participan.setState(ResponseCode.STATENO.val());
   		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
   		participan.setRegisterDate(df.format(new Date()));
   		//查询手机号是否存在
   		resu = service.getdraftMobilephone(participan);
   		if (resu.getCode().equals("500")) {//已存在，修改
   			List list = (List) resu.getData();
   			Map map = (Map) list.get(0);
   			participan.setParticipantsId((String) map.get("participants_id"));
   			participan.setSignupMobile((String) map.get("signupMobile"));
   			resu = service.adddraft(participan,"1");
   		}else {//不存在,保存草稿箱
   			participan.setParticipantsId(GetUuid.getUUID());
   			resu = service.adddraft(participan,"0");
   		}
   		
   		
   		return resu;
   	}
       
       /**
        * 	草稿表	查询手机号是否已注册
        */
       @RequestMapping(value = "/getmobilephonedraft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
   	public ResultData getmobilephonedraft(String mobilephone) {
    	ParticipantsSignUp participan = new ParticipantsSignUp();
    	participan.setSignupMobile(mobilephone);
       	resu = service.getdraftMobilephone(participan);
       	return resu;
       }
       
       /**
        * 	正式表	查询手机号是否已注册
        */
     @RequestMapping(value = "/getmobilephone",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
       public ResultData getmobilephone(String mobilephone) {
    	ParticipantsSignUp participan = new ParticipantsSignUp();
     	participan.setSignupMobile(mobilephone);
       	resu = service.getMobilephone(participan);
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
