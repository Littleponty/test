package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DO.AjaxJson;
import com.entity.Msg;
import com.service.MsgService;
import com.tools.Choose;
import com.tools.ContinueRead;
import com.tools.Function;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private MsgService msgService;

	@ResponseBody
	@RequestMapping("/jt")
	public AjaxJson jt(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setData(ContinueRead.jt);
		ajaxJson.setSuccess(1);
		ajaxJson.setMsg("获取成功");
		return ajaxJson;

	}

	@ResponseBody
	@RequestMapping("/start")
	public AjaxJson start(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ContinueRead.main(null);
		ajaxJson.setSuccess(1);
		ajaxJson.setMsg("启动连接");
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("/set")
	public AjaxJson set(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		AjaxJson ajaxJson = new AjaxJson();
		String com = request.getParameter("COM");
		ContinueRead.com = com;
		ajaxJson.setData("当前药柜为" + com);
		ajaxJson.setSuccess(1);
		ajaxJson.setMsg("修改成功");
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("/tools")
	public AjaxJson tools(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		AjaxJson ajaxJson = new AjaxJson();
		String save = null;
		Msg m = new Msg();
		String tools = request.getParameter("tools");
		m = Function.tools(tools);
		save = msgService.addMsg(m);// 保存数据
		if (save.equals("1")) {
			ajaxJson.setSuccess(1);
			ajaxJson.setMsg("发送指令成功");
		} else {
			ajaxJson.setSuccess(0);
			ajaxJson.setMsg("发送指令失败");
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("/input1")
	public AjaxJson input1(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		AjaxJson ajaxJson = new AjaxJson();
		String save = null;
		String name = request.getParameter("name");
		String st = Choose.drug_name(name);
		byte[] res = Choose.Stringto16(st);
		String msg = "设置药名" + name;
		ContinueRead.outputStream.write(res, 0, res.length);
		Msg m = new Msg();
		m.setCom(ContinueRead.com);
		m.setCommand(st);
		m.setMsg(msg);
		m.setTime(Function.time());
		save = msgService.addMsg(m);// 保存数据
		if (save.equals("1")) {
			ajaxJson.setSuccess(1);
			ajaxJson.setMsg("发送指令成功");
		} else {
			ajaxJson.setSuccess(0);
			ajaxJson.setMsg("发送指令失败");
		}

		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("/input2")
	public AjaxJson input2(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		AjaxJson ajaxJson = new AjaxJson();
		String weight = request.getParameter("weight");
		String save = null;
		String st = Choose.drug_weight(weight);
		byte[] res = Choose.Stringto16(st);
		String msg = "设置药品重量" + weight;
		ContinueRead.outputStream.write(res, 0, res.length);
		Msg m = new Msg();
		m.setCom(ContinueRead.com);
		m.setCommand(st);
		m.setMsg(msg);
		m.setTime(Function.time());
		save = msgService.addMsg(m);// 保存数据
		if (save.equals("1")) {
			ajaxJson.setSuccess(1);
			ajaxJson.setMsg("发送指令成功");
		} else {
			ajaxJson.setSuccess(0);
			ajaxJson.setMsg("发送指令失败");
		}
		return ajaxJson;
	}

}
