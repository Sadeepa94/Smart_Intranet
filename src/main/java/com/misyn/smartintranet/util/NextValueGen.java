package com.misyn.smartintranet.util;

public class NextValueGen {
	
	public static String getNextValue(String lastvalu)
	{
		String[] part = lastvalu.split("(?<=\\D)(?=\\d)");
		String code=part[0];
		String num=part[1];
		String formattedNum=String.format("%04d", Integer.parseInt(num)+1);
		return code+formattedNum;
		
	}

}
