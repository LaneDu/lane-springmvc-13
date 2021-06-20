package com.lagou.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * 全局的异常处理
 * @author lane
 * @date 2021年04月02日 上午9:53
 */
//@ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,
// 可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
@ControllerAdvice
public class GlobalException {
    /*
     * 指定捕获的异常处理，形参异常范围要大于等于指定的异常类型才可以具体处理
     * @author lane
     * @date 2021/4/2 上午9:56
     * @param arithmeticException
     * @param httpResponse
     */
    @ExceptionHandler(ArithmeticException.class)// 指定某一种异常
    public ModelAndView handleException(ArithmeticException arithmeticException,
                                        HttpServletResponse response) throws IOException {

        //可以直接输出出去
        //response.getWriter().write(arithmeticException.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",arithmeticException.getMessage());
        modelAndView.setViewName("error");

        return  modelAndView;
    }



}
