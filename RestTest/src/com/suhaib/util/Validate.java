package com.suhaib.util;

import javax.servlet.http.HttpServletRequest;

public class Validate {

	public static boolean validate (String [][] validate,HttpServletRequest request){
		
		for (int i = 0; i < validate.length; i++) {
			String data =request.getParameter(validate[i][0]);
			if (data==null||!data.matches(validate[i][1])) {
				return false;
			}
		}
		return true;
	}
}
