package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotation.MyAutowired;
import com.lagou.edu.mvcframework.annotation.MyController;
import com.lagou.edu.mvcframework.annotation.MyRequestMapping;
import com.lagou.edu.mvcframework.annotation.MySecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户user才可以访问，顾客customer不可以访问
 * @author lane
 * @date 2021年04月10日 上午11:34
 */
@MyController
@MyRequestMapping("/access")
@MySecurity("zhangsan")
public class AccessController {

    @MyAutowired
    private IDemoService demoService;
    /**
     * 免费的部分都可以访问
     * @author lane
     * @date 2021/4/10 上午11:38
     * @param request
     * @param response
     * @param username
     * @return java.lang.String
     */
    @MyRequestMapping("/free")
    @MySecurity({"wangwu","lisi"})
    public String freePage(HttpServletRequest request, HttpServletResponse response, String username){
        return  demoService.accessInfo(username);

    }
    /**
     * vip用户才能访问
     * @author lane
     * @date 2021/4/10 上午11:39
     * @param request
     * @param response
     * @param username
     * @return java.lang.String
     */
    @MyRequestMapping("/vip")
    @MySecurity
    public String vipPage(HttpServletRequest request, HttpServletResponse response, String username){
        return "vip"+  demoService.accessInfo(username);

    }

}
