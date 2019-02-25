package com.tools;

public class Choose {
	public static String Msg(String a, String b, String c) {
		String msg = null;// 名称

		if (a.equals("22")) {

			msg = "当前温度为" + Integer.parseInt(b, 16) + "湿度为" + Integer.parseInt(c, 16);
		} else if (a.equals("44")) {
			if (b.toLowerCase().equals("f0")) {
				msg = "当前抽屉已开锁";
			} else {
				msg = "当前抽屉已上锁";
			}
		} else if (a.equals("66")) {
			msg=b+c;//连接字符串成为重量
			msg = "当前总重量为" + Integer.parseInt(msg, 16) + "克";//将16进制转换成10进制
		} else if (a.equals("88")) {

			if (b.toLowerCase().equals("f0") && c.toLowerCase().equals("00")) {
				msg = "当前指示灯一号已打开，二号已关闭";
			} else if (c.toLowerCase().equals("f0") && b.toLowerCase().equals("00")) {
				msg = "当前指示灯一号已关闭，二号已打开";
			} else if (b.toLowerCase().equals("f0") && c.toLowerCase().equals("f0")) {
				msg = "当前指示灯全亮";
			} else {
				msg = "当前指示灯全灭";
			}
		} else {

			msg = "接收到未知数据";
		}

		return msg;

	}

	public static String drug_name(String a) {
		int time;
		int time2;
		String com = ContinueRead.com;
		String result;
		String CRCres;
		String resultfinal = "";

		String b = "1501" + com + "55";
		a = Chinato16(a);// 中文转字符串
		a = a.substring(1, a.length());// 删除首字空格
		System.out.println("删除空格" + a);
		a = b + a;// 拼接字符串
		System.out.println("拼接结果" + a);
		time = a.length() / 2;
		int in[] = new int[time];
		String a_array[] = new String[time];
		String b_array[] = new String[time];
		for (int i = 0; i < time; i++) {
			a_array[i] = a.substring(2 * i, i * 2 + 2);
		}
		for (int j = 0; j < time; j++) {
			in[j] = Integer.parseInt(a_array[j], 16);// 将16进制数转成10进制方便存入byte
		}
		result = CRC.main(in);
		CRCres = result.substring(4, 6).toUpperCase();// 取出crc结果后两位
		a = a + CRCres;// 连接字符串
		CRCres = result.substring(2, 4).toUpperCase();// 取出crc结果前两位
		a = a + CRCres;// 连接字符串
		time2 = a.length() / 2;
		String c_array[] = new String[time2];
		for (int i = 0; i < time2; i++) {
			c_array[i] = a.substring(2 * i, i * 2 + 2);
			resultfinal += " " + c_array[i];
		}
		resultfinal = resultfinal.substring(1, resultfinal.length());// 删除首字空格
		System.out.println("转换结果" + resultfinal);
		return resultfinal;

	}

	public static String drug_weight(String a) {
		int time;
		int time2;
		String CRCres;
		Integer value;
		String com = ContinueRead.com;
		String result;
		String hex;
		String resultfinal = "";
		value=Integer.parseInt(a);//String 转 int
		hex=value.toHexString(value);//10进制转16进制
		String b = "1501" + com + "77";
		if(hex.length()==1) {
		result="000"+hex;
		}
		else if(hex.length()==2) {
			result="00"+hex;
			}
		else if(hex.length()==3) {
			result="0"+hex;
			}
		else if(hex.length()==4) {
			result=hex;
			}
		else {
			result="0000";
		}
		a=b+result;//拼接结果
		System.out.println("拼接结果" + a);
		time = a.length() / 2;
		int in[] = new int[time];
		String a_array[] = new String[time];
		String b_array[] = new String[time];
		for (int i = 0; i < time; i++) {
			a_array[i] = a.substring(2 * i, i * 2 + 2);
		}
		for (int j = 0; j < time; j++) {
			in[j] = Integer.parseInt(a_array[j], 16);// 将16进制数转成10进制方便存入byte
		}
		result = CRC.main(in);
		CRCres = result.substring(4, 6).toUpperCase();// 取出crc结果后两位
		a = a + CRCres;// 连接字符串
		CRCres = result.substring(2, 4).toUpperCase();// 取出crc结果前两位
		a = a + CRCres;// 连接字符串
		time2 = a.length() / 2;
		String c_array[] = new String[time2];
		for (int i = 0; i < time2; i++) {
			c_array[i] = a.substring(2 * i, i * 2 + 2);
			resultfinal += " " + c_array[i];
		}
		resultfinal = resultfinal.substring(1, resultfinal.length());// 删除首字空格
		System.out.println("转换结果" + resultfinal);
		return resultfinal;

	}

	public static String Chinato16(String args) {
		// TODO Auto-generated method stub
		String s = args;
		String reslut = null;
		try {
			byte[] b = s.getBytes();
			String str = " ";
			for (int i = 0; i < b.length; i++) {
				Integer I = new Integer(b[i]);
				String strTmp = I.toHexString(b[i]);
				if (strTmp.length() > 2)
					strTmp = strTmp.substring(strTmp.length() - 2);
				str = str + strTmp;
			}
			System.out.println("中文转换结果" + str.toUpperCase());
			reslut = str.toUpperCase();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reslut;
	}

	public static byte[] Stringto16(String a) {
		String b[] = new String[200];
		String qu = null;
		int in[] = new int[200];
		b = a.split(" ");
		for (int i = 0; i < b.length; i++) {
			qu = b[i].substring(0, 2);// 取数组前两位，防止split函数的影响,String 16进制结果
			in[i] = Integer.parseInt(qu, 16);// 将16进制数转成10进制方便存入byte
		}

		byte[] CRC666 = new byte[b.length];
		for (int i = 0; i < b.length; i++) {
			CRC666[i] = (byte) in[i];

		}
		return CRC666;

	}

	public static String toString(byte[] a, int c) {

		int in[] = new int[200];
		String b[] = new String[200];
		String vs[] = new String[200];
		String str = "";
		StringBuffer sb = new StringBuffer();
		String reslut = null;
		for (int i = 0; i < c; i++) {
			in[i] = a[i];
		}

		for (int i = 0; i < c; i++) {
			b[i] = Integer.toHexString(in[i]);
			vs[i] = b[i];
			if (b[i].length() > 4) {
				b[i] = b[i].substring(6, 8);
			} else if (b[i].length() == 1) {
				b[i] = "0" + b[i];

			}
			str += " " + b[i];

		}
		str = str.substring(1, str.length());// 删除首字空格
		for (int i = 0; i < c; i++) {
			sb.append(vs[i]);// 循环数组连接成字符串
		}
		reslut = sb.toString();
		System.out.println("转换结果" + reslut);
		System.out.println("输出结果" + str);
		return str;

	}

}
