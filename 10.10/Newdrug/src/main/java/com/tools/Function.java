package com.tools;

import java.io.IOException;
import com.entity.Msg;


public class Function {
	public static String time() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		java.util.Date currentTime = new java.util.Date();// 得到当前系统时间
		String time = formatter.format(currentTime); // 将日期时间格式化
		return time;
	}
	
	public static String CRCsend(String st) {
		String[] a = null;// 输入数据
		int in[] = new int[6];	
		String qu = null;
		String b = null;
		String gao=null;
		String di=null;
		String res=null;
		
		a = st.split(" ");
		for (int i = 0; i < 6; i++) {
			qu = a[i].substring(0, 2);// 去数组前两位，防止split函数的影响
			in[i] = Integer.parseInt(qu, 16);// 将16进制数转成10进制方便存入byte
		}
		 b= CRC.main(in);// CRC校验方法，返回结果
		 gao = b.substring(2, 4).toUpperCase();// 取出crc结果前两位 ed
		 di = b.substring(4, 6).toUpperCase();// 去除crc结果后两位 8a
		 res=st.substring(0, 18)+di+" "+gao;
		 return res;
	}

	public static Msg tools(String a) throws IOException {
		String com = ContinueRead.com;
		String st = null;
		String msg = null;
		if (a.equals("01")) {
			st = "15 01 " + com + " 22 00 00 CE ED";// 读取温湿度信息
			st=CRCsend(st);
			msg = "读取温湿度信息";
		} else if (a.equals("02")) {
			st = "15 01 " + com + " 44 00 00 7E F7";// 读取抽屉状态
			st=CRCsend(st);
			msg = "读取抽屉状态";

		} else if (a.equals("03")) {
			st = "15 01 " + com + " 66 00 00 DE FD";// 读取总重量
			st=CRCsend(st);
			msg = "读取总重量";

		} else if (a.equals("04")) {
			st = "15 01 " + com + " 88 00 00 BE C8";// 读取指示灯状态
			st=CRCsend(st);
			msg = "读取指示灯状态";

		} else if (a.equals("05")) {
			st = "15 01 " + com + " 99 F0 00 AA CD";// 点亮一号灯
			st=CRCsend(st);
			msg = "点亮一号灯";

		} else if (a.equals("06")) {
			st = "15 01 " + com + " 99 00 F0 EE 89";// 点亮二号灯
			st=CRCsend(st);
			msg = "点亮二号灯";

		} else if (a.equals("07")) {
			st = "15 01 " + com + " 99 F0 F0 AA 89";// 点亮所有灯
			st=CRCsend(st);
			msg = "点亮所有灯";

		}
		else if (a.equals("08")) {
			st = "15 01 " + com + " 33 00 00 AA 89";// 上锁
			st=CRCsend(st);
			msg = "打开抽屉";

		}
		else if (a.equals("09")) {
			st = "15 01 " + com + " 33 F0 00 AA 89";// 开锁
			st=CRCsend(st);
			msg = "关闭抽屉";

		}
		byte[] res = Choose.Stringto16(st);
		ContinueRead.outputStream.write(res, 0, res.length);
		Msg m = new Msg();
		m.setCom(com);
		m.setCommand(st);
		m.setMsg(msg);
		m.setTime(Function.time());
		return m;
	}

}
