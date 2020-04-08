package com.hyxh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hyxh.entity.Colleague;
import com.hyxh.entity.ExhibitionSignup;
import com.hyxh.entity.PersonalVip;
import com.hyxh.entity.Questionnaire;
import com.hyxh.service.ExhibitionSignupService;
import com.hyxh.service.PersonalVIPService;
import com.hyxh.util.GetUuid;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionSignupController {
	@Autowired
    private ExhibitionSignupService service;
	private ResultData resu;
	
	public ExhibitionSignupController() {
		resu = new ResultData();
	}
	
	/**
	 * 观展报名
	 * @return
	 */
    @RequestMapping(value = "/registered",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
	public ResultData registered(ExhibitionSignup exhibition) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	exhibition.setExhibitionId(GetUuid.getUUID());
    	exhibition.setSignupDate(df.format(new Date()));
    	exhibition.setState(ResponseCode.STATENO.val());
    	resu = service.registered(exhibition);
    	
		return resu;
	}
    /**
	 * 观展问卷填写
	 * @return
	 */
    @RequestMapping(value = "/questionnaire",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
	public ResultData questionnaire(Questionnaire questionnaire,String exhibitionId) {
    	questionnaire.setId(GetUuid.getUUID());
    	questionnaire.setExhibitionId(exhibitionId);
    	resu = service.questionnaire(questionnaire);
		return resu;
	}
    
    /**
      * 观展同行人填写 
     *
    */
    @RequestMapping(value = "/colleague",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultData colleague(List<Colleague> list) {
    	System.out.println(list);
    	return resu;
    }
    
    /**
	 * 观展报名草稿
	 * @return
	 */
    @RequestMapping(value = "/draft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
	public ResultData draft(ExhibitionSignup exhibition) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	exhibition.setExhibitionId(GetUuid.getUUID());
    	exhibition.setSignupDate(df.format(new Date()));
    	exhibition.setState(ResponseCode.STATENO.val());
    	//查询手机号是否存在
    			resu = service.getdraftmobilephone(exhibition);
    			if (resu.getCode().equals("500")) {//已存在，修改
    				List list = (List) resu.getData();
    				Map map = (Map) list.get(0);
    				exhibition.setExhibitionId((String) map.get("user_id"));
    				exhibition.setSignupMobile((String) map.get("mobilephone"));
    				resu = service.draft(exhibition,"1");
    			}else {//不存在,保存草稿箱
    				exhibition.setExhibitionId(GetUuid.getUUID());
    				resu = service.draft(exhibition,"0");
    			}
    			return resu;
	}
}
