package com.lagou.controller;

import com.lagou.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author lane
 * @date 2021年03月30日 下午4:06
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/handle01")
    public ModelAndView handle01(){
        Date date = new Date();
        //封装页面和数据信息
        ModelAndView modelAndView = new ModelAndView();
        //向请求域中添加数据 request.addAttribute()
        modelAndView.addObject("date",date);
        //视图信息，封装页面信息,跳转逻辑视图名
        //modelAndView.setViewName("WEB-INFO/jsp/success.jsp");
        System.out.println(date);
        modelAndView.setViewName("success");
        return modelAndView;

    }

    @RequestMapping("/handle02")
    public String  handle02(ModelMap modelMap){
        Date date = new Date();
        modelMap.addAttribute("date",date);
        System.out.println(date);
        return "success";

    }
    //直接声明model
    @RequestMapping("/handle03")
    public String  handle02(Map<String,Object> map){
        Date date = new Date();
        map.put("date",date);
        System.out.println(date);
        return "success";
    }
    // 获取前台传递的时间类型参数
    //需要自定义类型转换扩展接口和注册实现类
    @RequestMapping("/handle06")
    public String  handle06 (Date birthday){

        System.out.println(birthday);
        return "success" ;

    }
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.GET})
    public String handle7(@PathVariable("id") Integer id){

        System.out.println(id);
        return "success";
    }

    @RequestMapping(value = "/handle",method = {RequestMethod.POST})
    public String handle8(@RequestParam("username") String name){

        System.out.println(name);
        return "success";
    }
    @RequestMapping(value = "handle/{id}/{username}",method = {RequestMethod.PUT})
    public String handle9(@PathVariable("id") Integer id,@PathVariable("username") String username){

        System.out.println(id +""+username);

        return "success";

    }
    @RequestMapping(value = "handle/{id}/{username}",method = {RequestMethod.DELETE})
    public String handle10(@PathVariable("id") Integer id,@PathVariable("username") String username){

        System.out.println(id +""+username);

        return "success";

    }


    @RequestMapping(value = "/handle07",method = RequestMethod.POST)
    public String  handle07 (@RequestBody User user){

        System.out.println(user);
        return "success" ;

    }

    @RequestMapping(value = "/handle08",method = RequestMethod.POST)
    @ResponseBody //指定返回值不经过视图解析而是放入response中
    public User  handle08 (@RequestBody User user){
        user.setUsername("lane");
        System.out.println(user);
        return user ;
    }









}





