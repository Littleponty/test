package com.tools;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import gnu.io.*;

public class ContinueRead extends Thread implements SerialPortEventListener { // SerialPortEventListener
	// 监听器,我的理解是独立开辟一个线程监听串口数据
	static CommPortIdentifier portId; // 串口通信管理类
	static Enumeration<?> portList; // 有效连接上的端口的枚举
	InputStream inputStream; // 从串口来的输入流
	public static OutputStream outputStream;// 向串口输出的流
	static SerialPort serialPort; // 串口的引用
	// 堵塞队列用来存放读到的数据
	private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
	public static String st = "11";// 告诉硬件服务器已经准备好
	public static String jt = "准备接收";
	public static String com = "01";
	public static int i;
	public static int j = 0;
	static String chu_li[] = new String[50];
	static int num = 0;
	static ContinueRead cRead = new ContinueRead();

	@Override
	/**
	 * SerialPort EventListene 的方法,持续监听端口上是否有数据流
	 */
	public void serialEvent(SerialPortEvent event) {//

		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据
			byte[] readBuffer = new byte[100];
			try {
				int numBytes = -1;
				while (inputStream.available() > 0) {
					numBytes = inputStream.read(readBuffer);

					String a[] = null;
					String b = null;// 输出数据
					String qu = null;
					String gao = null;
					String di = null;
					int time;
					String gao_s = null;
					String di_s = null;
					byte[] c = null;// 数据处理
					StringBuffer sb = new StringBuffer();
					if (numBytes > 0) {
						jt = Choose.toString(readBuffer, numBytes);// 转成String16进制
						a = jt.split(" ");// 删除字符串之间的空格，并且存入数组
						time = a.length;
						if (time < 8) {// 处理硬件分批发送判断

							System.arraycopy(a, 0, chu_li, num, a.length);
							num = a.length + num;
							System.out.println("长度未等于标准值，数据将保存至缓冲区");
							if (num >= 8) {
								System.out.println("数据满足要求开始处理");
								time = num;
								// =========================处理算法 ==============================
								int in[] = new int[time - 2];
								gao_s = chu_li[time - 2].substring(0, 2).toLowerCase();// 取前两位并转大写转小写
								di_s = chu_li[time - 1].substring(0, 2).toLowerCase();

								for (int i = 0; i < time; i++) {
									sb.append(chu_li[i]);// 循环数组连接成字符串
								}
								for (int i = 0; i < time - 2; i++) {
									qu = chu_li[i].substring(0, 2);// 去数组前两位，防止split函数的影响
									in[i] = Integer.parseInt(qu, 16);// 将16进制数转成10进制方便存入byte
								}
								jt = sb.toString(); // 处理完的字符串保存如结果
								b = CRC.main(in);// CRC校验方法，返回结果存入b
								gao = b.substring(2, 4);// 取出crc结果前两位 ed
								di = b.substring(4, 6);// 去除crc结果后两位 8a
								msgQueue.add(new Date() + "真实收到的数据为：" + jt + "\n" + "CRC16校验结果" + b + "CRC高位" + gao
										+ "接收结果" + di_s + "CRC低位" + di + "接收结果" + gao_s);

								if (di_s.equals(gao) && gao_s.equals(di)) {// 判断校验结果
									System.out.println("校验通过");
									jt = Choose.Msg(chu_li[3].substring(0, 2), chu_li[4].substring(0, 2),
											chu_li[5].substring(0, 2));// 输出结果信息

								} else {
									jt = "校验失败，请重新发送指令";
									System.out.println("校验比较未通过");
								}
								chu_li = new String[50];// 重置初值
								num = 0;
							}
							readBuffer = new byte[300];// 重新构造缓冲对象，否则有可能会影响接下来接收的数据

						} else {
							// =========================处理算法 ==============================
							int in[] = new int[time - 2];
							gao_s = a[time - 2].substring(0, 2).toLowerCase();// 取前两位并转大写转小写
							di_s = a[time - 1].substring(0, 2).toLowerCase();

							for (int i = 0; i < time; i++) {
								sb.append(a[i]);// 循环数组连接成字符串
							}
							for (int i = 0; i < time - 2; i++) {
								qu = a[i].substring(0, 2);// 去数组前两位，防止split函数的影响
								in[i] = Integer.parseInt(qu, 16);// 将16进制数转成10进制方便存入byte
							}
							jt = sb.toString(); // 处理完的字符串保存如结果
							b = CRC.main(in);// CRC校验方法，返回结果存入b
							gao = b.substring(2, 4);// 取出crc结果前两位 ed
							di = b.substring(4, 6);// 去除crc结果后两位 8a
							msgQueue.add(new Date() + "真实收到的数据为：" + jt + "\n" + "CRC16校验结果" + b + "CRC高位" + gao + "接收结果"
									+ di_s + "CRC低位" + di + "接收结果" + gao_s);

							if (di_s.equals(gao) && gao_s.equals(di)) {// 判断校验结果
								System.out.println("校验通过");
								jt = Choose.Msg(a[3].substring(0, 2), a[4].substring(0, 2), a[5].substring(0, 2));// 输出结果信息

							} else {
								jt = "校验失败，请重新发送指令";
								System.out.println("校验比较未通过");
							}
							readBuffer = new byte[300];// 重新构造缓冲对象，否则有可能会影响接下来接收的数据
						}
					} else {
						msgQueue.add("额------没有读到数据");
					}
				}
			} catch (IOException e) {
			}
			break;
		}
	}

	/**
	 * 
	 * 通过程序打开COM4串口，设置监听器以及相关的参数
	 * 
	 * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
	 */
	public int startComPort() {
		// 通过串口通信管理类获得当前连接上的串口列表
		portList = CommPortIdentifier.getPortIdentifiers();

		while (portList.hasMoreElements()) {

			// 获取相应串口对象
			portId = (CommPortIdentifier) portList.nextElement();

			System.out.println("设备类型：--->" + portId.getPortType());
			System.out.println("设备名称：---->" + portId.getName());
			// 判断端口类型是否为串口
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				// 判断如果COM4串口存在，就打开该串口
				if (portId.getName().equals("COM5")) {
					try {
						// 打开串口名字为COM_4(名字任意),延迟为2毫秒
						serialPort = (SerialPort) portId.open("COM_4", 2000);

					} catch (PortInUseException e) {
						e.printStackTrace();
						return 0;
					}
					// 设置当前串口的输入输出流
					try {
						inputStream = serialPort.getInputStream();
						outputStream = serialPort.getOutputStream();
					} catch (IOException e) {
						e.printStackTrace();
						return 0;
					}
					// 给当前串口添加一个监听器
					try {
						serialPort.addEventListener(this);
					} catch (TooManyListenersException e) {
						e.printStackTrace();
						return 0;
					}
					// 设置监听器生效，即：当有数据时通知
					serialPort.notifyOnDataAvailable(true);

					// 设置串口的一些读写参数
					try {
						// 比特率、数据位、停止位、奇偶校验位
						serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
					} catch (UnsupportedCommOperationException e) {
						e.printStackTrace();
						return 0;
					}

					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("--------------任务处理线程运行了--------------");
			while (true) {
				// 如果堵塞队列中存在数据就将其输出
				if (msgQueue.size() > 0) {
					System.out.println(msgQueue.take());
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		if (i == 1) {
			// 启动线程来处理收到的数据
			try {
				System.out.println("发出字节数：" + st.getBytes("gbk").length);
				outputStream.write(st.getBytes("gbk"), 0, st.getBytes("gbk").length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			i = cRead.startComPort();
			cRead.start();
			try {
				System.out.println("发出字节数：" + st.getBytes("gbk").length);
				outputStream.write(st.getBytes("gbk"), 0, st.getBytes("gbk").length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}