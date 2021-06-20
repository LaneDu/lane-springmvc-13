package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotation.MyAutowired;
import com.lagou.edu.mvcframework.annotation.MyController;
import com.lagou.edu.mvcframework.annotation.MyRequestMapping;
import com.lagou.edu.mvcframework.annotation.MySecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author lane
 * @date 2021年04月02日 下午2:28
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {
    @MyAutowired
    private IDemoService demoService;
    @MyRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response,String name){
      return demoService.get(name);

    }


}
