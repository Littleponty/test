package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringUtil {

	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

 
	
	/**
	 * 根据起始和结束时间获得时间区间
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date[] parseDateByMonth(String startDateStr, String endDateStr) throws ParseException{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date endDate = new Date();
		if (startDateStr != null && !startDateStr.equals("")) {
			startDate = df.parse(startDateStr+" 00:00:00");
		}
		if (endDateStr != null && !endDateStr.equals("")) {
			endDate = df.parse(endDateStr+ " 23:59:59");
			
		}
		Date[] period = new Date[2];
		period[0] = startDate;
		period[1] = endDate;
		return period;
	}
	/**
	 * 根据季度获取时间区间
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date[] parseDateByQuarter(String year, String quarter) {
		//初始化为起始时间2016�?1月，截止时间为当前年�?
		int sy = 2016;
		int sm = 1;
		Calendar cal = new GregorianCalendar();
		int ny = cal.get(Calendar.YEAR);
		int nm = cal.get(Calendar.MONTH) + 1;
		
		if(year!=null){
			sy = Integer.parseInt(year);
			ny = Integer.parseInt(year);
		}
		if(quarter!=null){
			int q = Integer.parseInt(quarter);
			switch (q) {
				case 1: {
					sm = 0;
					nm = 2;
					break;
				}
				case 2: {
					sm = 3;
					nm = 5;
					break;
				}
				case 3: {
					sm = 6;
					nm = 8;
					break;
				}
				case 4: {
					sm = 9;
					nm = 11;
					break;
				}
			}
		}
		
		Date[] period = new Date[2];
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.set(sy, sm, 1, 0, 0, 0);
		Date d1 = c1.getTime();
		period[0] = d1;

		c2.set(ny, nm + 1, 1, 0, 0, 0);
		c2.set(Calendar.SECOND, c2.get(Calendar.SECOND) - 1);
		Date d2 = c2.getTime();
		period[1] = d2;
		return period;
	}
	
	public static String generateSignature(String str1,String str2,String str3){
		return getMD5Str(str1+str2+str3);
	}
	
	public static void main(String args[]) throws ParseException {
	 
	}
}
