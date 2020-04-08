package com.hyxh.util;


import lombok.Data;

@Data
public class ResultData {

	/**
	 * 
	 * @Date 2020/4/2
	 * @Version V1.0
	 * @Description: 返回样式封装
	  * 状态码code，提示消息msg以及返回的数据data
	 */
	

	    private String code;
	    
	    private String msg;

	    private Object data;

	    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

		
	    //成功
	    public static ResultData success(Object data) {
	       return resultData(ResponseCode.SUCCESS.val(), ResponseCode.SUCCESS.msg(), data);
	    }

	    public static ResultData success(Object data, String msg) {
	        return resultData(ResponseCode.SUCCESS.val(), msg, data);
	    }
	    //失败
	    public static ResultData fail(String code, String msg) {
	        return resultData(code, msg, null);
	    }

	    public static ResultData fail(String code, String msg, Object data) {
	        return resultData(code, msg, data);
	    }

	    private static ResultData resultData(String code, String msg, Object data) {
	        ResultData resultData = new ResultData();
	        resultData.setCode(code);
	        resultData.setMsg(msg);
	        resultData.setData(data);
	        return resultData;
	    }
}
