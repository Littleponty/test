package com.test.fly.controller;

import com.test.fly.common.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class TextController {

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ServerResponse test(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("COM");
        System.out.println("让我看看是啥玩意"+username);
        return null;
    }

}
