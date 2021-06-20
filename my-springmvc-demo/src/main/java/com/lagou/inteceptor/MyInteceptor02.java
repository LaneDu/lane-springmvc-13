package com.lagou.inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器spingmvc所有的
 * @author lane
 * @date 2021年04月01日 上午11:50
 */
public class MyInteceptor02 implements HandlerInterceptor {
    /*
     * handle方法执行之前拦截
     * 一般用来做权限的校验
     * 较为常用
     * 返回值为true是放行方法，false的时候方法中止
     * @author lane
     * @date 2021/4/1 上午11:55
     * @param request
     * @param response
     * @param handler 
     * @return boolean
     */
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        System.out.println("我是myinteceptor02的preHandle方法");
        return true;
    }

    /*
     * handle方法执行之后，视图渲染之前，未跳转页面
     * 可以在这里对视图和返回值进行修改处理
     * @author lane
     * @date 2021/4/1 上午11:58
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("我是myinteceptor02的postHandle方法");
    }
    /*
     * 视图渲染之后进行拦截
     * 可以用来捕获异常等
     * @author lane
     * @date 2021/4/1 下午12:00
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("我是myinteceptor02的afterCompletion方法");
    }
}
