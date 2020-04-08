package com.hyxh.util;

public enum ResponseCode {
	/**当前审核状态 0 待审核*/
	STATENO("0","待审核"),
	/**当前审核状态 1  以审核*/
	STATEYES("1","以审核"),
	
	 /** 成功 */
    SUCCESS("200", "成功"),

    /** 操作失败 */
    ERROR("500", "操作失败");
	
	 private String val;
	 private String msg;

    private ResponseCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

   
}
