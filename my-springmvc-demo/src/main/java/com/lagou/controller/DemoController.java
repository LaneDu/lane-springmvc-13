package com.lagou.controller;

import com.lagou.pojo.MyFile;
import com.lagou.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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

    /**
     * 处理文件上传的方法
     * @author lane
     * @date 2021/4/2 上午9:42
     * @param uplaodFile
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "/upload")
    public ModelAndView   upload (MultipartFile uplaodFile, HttpSession session) throws IOException {
        //获取文件名称
        String originalFilename = uplaodFile.getOriginalFilename();
        //获取扩展名
        String ext =   originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        //获取新名字
        String newName = UUID.randomUUID().toString()+"."+ext;

        //获取根本路径webapp，在此建一个文件夹，/uploads/yyyy-MM-dd这种形式存储
        String realPath = session.getServletContext().getRealPath("/uploads");
        Date  date = new Date();
        SimpleDateFormat sm =  new  SimpleDateFormat("yyyy-MM-dd");
        //获取文件夹名
        String datePath = sm.format(date);
        File folder = new File(realPath+"/"+datePath);
        if (!folder.exists()){
            folder.mkdirs();
        }
        //将上传的文件以一个新的名字放在新建的文件夹下
        uplaodFile.transferTo(new File(folder,newName));
        //TODO 上传到数据库
        MyFile myFile = new MyFile();
        myFile.setFileName(originalFilename);
        myFile.setNewFileName(newName);
        myFile.setFilePath(realPath+"/"+datePath);
        System.out.println("存储文件信息到数据库");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView ;
    }
    @RequestMapping(value = "/handle09",method = RequestMethod.GET)
    public void handle09(){

        int c = 1/0;
    }

    @GetMapping(value = "/handle10")
    public String handle10(@ModelAttribute("date") String date){
        System.out.println(date);
         return "success";
    }
    //转发原始版
    @GetMapping("/dispatcher01")
    public void dispatcher01(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("date", new Date());
        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);

    }
    //转发mvc版
    @GetMapping("/dispatcher02")
    public String dispatcher02(String date) {

        return "forward:handle10";

    }
    //重定向mvc版--值丢失
    @GetMapping("/redirect01")
    public String redirect01(String date) {

        return "redirect:handle10";

    }
    //重定向mvc版--值拼接
    @GetMapping("/redirect02")
    public String redirect02(String date) {

        return "redirect:handle10?date="+date;

    }
    //重定向mvc版--值闪存
    @GetMapping("/redirect03")
    public String redirect03(String date, RedirectAttributes redirectAttributes) {
        //闪现存储，到页面值会自动销毁
        redirectAttributes.addFlashAttribute("date",date);
        //存储，到页面值不会自动销毁
        redirectAttributes.addAttribute("date",date);
        return "redirect:handle10";

    }
}





