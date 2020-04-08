package com.hyxh.util;

import java.util.UUID;

public class GetUuid {
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
