package com.lagou.edu.controller;


import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lane
 * @date 2021年04月07日 下午6:16
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     *  spring容器和Springmvc容器是有层次的俗称父子容器
     *  Spring容器父子service+dao层
     *  spring mvc 容器负责controller层 其内部有一个Spring容器的引用
     *
     */

    @Autowired
    private AccountService accountService;
    /**
     * 不引入json的jar会500 list无法自动转换json
     * 这个返回是空白页面打印内容
     * @author lane
     * @date 2021/4/7
     * @return java.util.List<com.lagou.edu.pojo.Account>
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Account> queryAll() throws Exception {

        List<Account> accountList = accountService.queryAccountList();
        return accountList;

    }


    @RequestMapping(value = "/queryTest",method = RequestMethod.GET)
    public ModelAndView queryTest(ModelAndView modelAndView) throws Exception {
        List<Account> accountList = accountService.queryAccountList();
        modelAndView.addObject("date",new Date());
        modelAndView.addObject("accountList",accountList);
        modelAndView.setViewName("success");

        return modelAndView;

    }
    /**
     * ajax传类型为json对象，content type 为www-form,返回值不是json就不要写dataType:json
     * @author lane
     * @date 2021/4/7 下午7:28
     * @param id
     * @param username
     * @return java.lang.String
     */
    @RequestMapping(value = "/queryTest1",method = RequestMethod.POST)
    @ResponseBody
    public String queryTest1(Integer id, String username) {

        String str = id + username;
        System.out.println(str);

        return str;


    }
    /**
     * ajax传递类型为json字符串content type 为json 必须加@RequestBody
     * @author lane
     * @date 2021/4/7 下午7:29
     * @param str
     * @return java.lang.String
     */
    @RequestMapping(value = "/queryTest2",method = RequestMethod.POST)
    @ResponseBody
    public String queryTest2(@RequestBody String str)  {
        System.out.println(str);

        return str;

    }

}
