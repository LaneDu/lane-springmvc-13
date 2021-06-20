package com.lagou.edu.mvcframework.pojo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * uri和方法映射需要多个参数封装才行
 * @author lane
 * @date 2021年04月06日 上午11:13
 */
public class Handler {
    //对象
    private Object controller;
    //方法
    private Method method;
    //url 可以存字符串，pattern是正则类型
    private Pattern pattern;
    //参数顺序，参数绑定，key是参数名value 是参数的顺序
    private Map<String,Integer> paramIndexMapping;
    //添加访问权限用户数组（作业内容）
    private String[] authUser;
    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        this.paramIndexMapping = new HashMap<>();
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }

    public String[] getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String[] authUser) {
        this.authUser = authUser;
    }
}
