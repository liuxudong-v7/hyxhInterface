package com.hyxh.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hyxh.entity.Invoice;
import com.hyxh.entity.TeamPhotos;
import com.hyxh.entity.TeamRegister;
import com.hyxh.entity.PersonalVip;
import com.hyxh.service.PersonalVIPService;
import com.hyxh.service.TeamVIPService;
import com.hyxh.service.impl.PersonalVIPServiceImpl;
import com.hyxh.util.FileUtil;
import com.hyxh.util.GetUuid;
import com.hyxh.util.ResponseCode;
import com.hyxh.util.ResultData;

@RestController
@RequestMapping("/team")
public class TeamVIPController {
	@Autowired
    private TeamVIPService service;
	private ResultData resu;
	 /**
	  *   下载团体会员注册表模板
	  * @param filename
	  * @throws IOException
	  */
	 @RequestMapping(value = "/download")
	 public void download(@RequestParam("fileName") String filename) throws IOException {
	        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletResponse response = requestAttributes.getResponse();
	        // 设置信息给客户端不解析
	        String type = new MimetypesFileTypeMap().getContentType(filename);
	        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
	        response.setHeader("application/msword",type);
	        // 设置编码
	        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
	        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
	        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
	        FileUtil.download(filename, response);
	    }
	 
	 /**
	  * 上传团体会员注册表
	  * @param file
	  * @return
	  */
	 @PostMapping("/upload")
	 @ResponseBody
	 public ResultData upload(@RequestParam("file") MultipartFile file,String mobilephone) {
		 
	        String fileName = file.getOriginalFilename();
	        int size = (int) file.getSize();
	        int length=fileName.length();
	        String str = "" ;
	        if(length>=3){
	        	str =fileName.substring(length-4,length);
	        }
	        String photosName = mobilephone+str;
	        System.out.println(fileName + "-->" + size);
	        
	        String path = "D:/hyxhshangchuan" ;
	        File dest = new File(path + "/" + fileName);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
	            dest.getParentFile().mkdir();
	        }
	        try {
	            file.transferTo(dest); //保存文件
	            TeamPhotos photo = new TeamPhotos();
	            photo.setTeamId(GetUuid.getUUID());
	            photo.setMobilephone(mobilephone);
	            photo.setPhotosName(photosName);
	            photo.setFileName(fileName);
	            resu = service.teamPhotoadd(photo);
	            return resu;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return ResultData.fail(ResponseCode.ERROR.val(), "上传出现异常");
	        }
	 }
	 
	 /**
	  *  团体会员注册 
	  * @param file 
	  * @return
	  */
	 @RequestMapping(value = "/registered",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 @ResponseBody
	 public ResultData registered(TeamRegister team) {
		 team.setTeamId(GetUuid.getUUID());
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 team.setRegisterDate(df.format(new Date()));
		 team.setState(ResponseCode.STATENO.val());
		 resu = service.registered(team);
		return resu;
	 }
	 /**
	  *  团体会员注册 草稿
	  * @param file draft
	  * @return
	  */
	 @RequestMapping(value = "/draft",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 @ResponseBody
	 public ResultData draft(TeamRegister team) {
		 team.setState(ResponseCode.STATENO.val());
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 team.setRegisterDate(df.format(new Date()));
		 resu = getmobilephonedraft(team.getMobilephone());
		 if (resu.getCode().equals("500")) {//已存在，修改
			List list = (List) resu.getData();
			Map map = (Map) list.get(0);
			team.setTeamId((String) map.get("team_id"));
			team.setMobilephone((String) map.get("mobilephone"));
			resu = service.draft(team,"1");
		 }else {
			 team.setTeamId(GetUuid.getUUID());
			 resu = service.draft(team,"0");
		 }
		return resu;
	 }
	 
	 /**
	 * 	草稿表查询手机号是否已注册
	 */
	 @RequestMapping("/getmobilephonedraft")
	 public ResultData getmobilephonedraft(String mobilephone) {
		 TeamRegister team = new TeamRegister();
		 team.setMobilephone(mobilephone);
		 resu = service.getdraftmobilephone(team);
		 
		 return resu;
	 }
	 
	 /**
	     *	 发票
	     */
	    @RequestMapping(value = "/invoice",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public ResultData invoice(Invoice invoice) {
	    	invoice.setInvoiceId(GetUuid.getUUID());
	    	PersonalVIPService ser = new PersonalVIPServiceImpl();
	    	resu = ser.addinvoice(invoice);
	    	return resu;
	    }
	    
}
