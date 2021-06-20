package com.lagou.demo.service.impl;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotation.MyService;

import java.util.Date;

/**
 * @author lane
 * @date 2021年04月02日 下午2:31
 */
@MyService
public class DemoServiceImpl implements IDemoService {
    @Override
    public String get(String name) {
        System.out.println("service 实现类中的name为："+name);
        return name;
    }

    @Override
    public String accessInfo(String name) {
        String msg = "用户："+name+" 成功访问服务器 "+new Date();
        System.out.println(msg);
        return msg;
    }


}
