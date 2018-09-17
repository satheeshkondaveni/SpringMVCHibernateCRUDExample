package com.ksbs.sys.util;

public class Validation {
public static final String[] restrict_arr = { "--", "=", ";--", ";", "/*", "*/", "@@", "@","&","script" ,

			 "nchar", "varchar", "nvarchar", "alter", "begin", "cast", "create", "cursor",     "declare", "delete", "drop", "exec", "execute", "fetch", "insert", "kill", "open", "select", "sys", "sysobjects", "syscolumns", "table","update", "<script", "<script>", ".js",".exe", ".sql","xp_","DBCC"};




public static boolean numbersOnly(String a) {
	////String mesg = new String();
	boolean flag = true;
	try {
		if ((a.trim()).equals("")) {
			return false;
		}
		
		int len = a.length();
		
		for (int j = 0; j < restrict_arr.length; j++) {
			int index = (a.toLowerCase()).indexOf(restrict_arr[j]);
			if (index != -1) {
				return false;
			}
		}
		for (int i = 0; i < len; i++) {
			if ( (((int) a.charAt(i)) > 47 && ((int) a.charAt(i)) <= 57) 	) {
				flag = true;
			} else {
				return false;
			}
		}
		
		
		
		
	} catch (Exception e) {
		String err = e.toString();
		if (err.indexOf("NullPointerException") > 0)
			flag = false;
		if (err.indexOf("IOException") > 0)
			flag = false;
		if (err.indexOf("NumberFormatException") > 0)
			flag = false;
	}
	return flag;
}


public static boolean namesOnly(String a) {
	
	boolean flag = true;
	try {
		if ((a.trim()).equals("")) {
			return false;
		}
		
		int len = a.length();
		if(a.toLowerCase().contains(("--").toLowerCase())||(a.trim()).equals("-")){
			return false;
		}
		for (int j = 0; j < restrict_arr.length; j++) {
			int index = (a.toLowerCase()).indexOf(restrict_arr[j]);
			if (index != -1) {
				return false;
			}
		}
		for (int i = 0; i < len; i++) {
			if ((((int) a.charAt(i)) >= 65 && ((int) a.charAt(i)) <= 90)
					|| (((int) a.charAt(i)) >= 97 && ((int) a.charAt(i)) <= 122)
					|| (((int) a.charAt(i)) > 47 && ((int) a.charAt(i)) <= 57) 
					|| (((int) a.charAt(i)) == 45 )|| (((int) a.charAt(i)) == 91 )
					|| (((int) a.charAt(i)) == 93 )|| (((int) a.charAt(i)) == 95 )
					|| (((int) a.charAt(i)) == 32 )
					) {
				flag = true;
			} else {
				return false;
			}
		}
		
		
	} catch (Exception e) {
		String err = e.toString();
		if (err.indexOf("NullPointerException") > 0)
			flag = false;
		if (err.indexOf("IOException") > 0)
			flag = false;
		if (err.indexOf("NumberFormatException") > 0)
			flag = false;
	}
	return flag;
}



}
