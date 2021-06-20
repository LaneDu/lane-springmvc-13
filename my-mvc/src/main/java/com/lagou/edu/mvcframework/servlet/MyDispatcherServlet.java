package com.lagou.edu.mvcframework.servlet;

import com.lagou.edu.mvcframework.annotation.*;
import com.lagou.edu.mvcframework.pojo.Handler;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lane
 * @date 2021年04月02日 上午11:35
 */
public class MyDispatcherServlet extends HttpServlet {
    //配置文件信息
    private Properties properties = new Properties();
    //缓存扫描到的类的全限定类名
    private List<String> classNames = new ArrayList<>();
    //ioc容器
    private Map<String, Object> ioc = new HashMap<>();
    //handlerMapping映射器
    //private Map<String,Method> handlerMapping = new HashMap<>();
    //添加新的handlerMapping
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、 加载配置文件 springmvc.properties
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        System.out.println(contextConfigLocation);
        doLoadConfig(contextConfigLocation);

        //2、 扫描相关的类相关的注解
        doScan(properties.getProperty("scanPackage"));
        //3、 初始化bean对象（ioc注解实现）
        doInstance();
        //4、 实现依赖注入 di
        doAutowired();
        //5、 构造handlerMapping处理器映射器，建立url和方法的对应关系
        initHandlerMapping();
        System.out.println("spring mvc 初始化完成～～～～～～～～");
        //6、 等待请求进入

    }
    /**
     * 构建handlerMapping，建立url和method之间到关系
     * @author lane
     * @date 2021/4/2 下午4:26
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty())return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> aClass = entry.getValue().getClass();
            if (!aClass.isAnnotationPresent(MyController.class)){continue;}

            String baseUrl = "";
            //是否有注解@MyRequestMapping
            if (aClass.isAnnotationPresent(MyRequestMapping.class)){
                String value = aClass.getAnnotation(MyRequestMapping.class).value();
                baseUrl = value;
            }
            //作业内容开始
            //类上用户
            String[] authUserClass = {};
            String[] authUserMerge = {};
            //判断类是否存在权限注解用户若是则放在authUserClass数组中
            if(aClass.isAnnotationPresent(MySecurity.class)){
                MySecurity mySecurity = aClass.getAnnotation(MySecurity.class);
                authUserClass = new String[mySecurity.value().length];
                System.arraycopy(mySecurity.value(), 0, authUserClass, 0, mySecurity.value().length);
                System.out.println(authUserClass);

            }
            //作业内容结束
            //获取方法
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                MyRequestMapping methodAnnotation = method.getAnnotation(MyRequestMapping.class);
                //作业内容
                //判断是否该方法权限用户,若是则放在authUser数组中
                if(method.isAnnotationPresent(MySecurity.class)){
                 String[] authUserMethod = method.getAnnotation(MySecurity.class).value();
                  //合并数组
                  authUserMerge = new String[authUserClass.length+authUserMethod.length];
                    for (int j = 0; j <authUserClass.length+authUserMethod.length ; j++) {
                        if (j<authUserClass.length){
                            authUserMerge[j] = authUserClass[j];
                        }else{
                            authUserMerge[j] = authUserMethod[j-authUserClass.length];
                        }
                    }
                }
                System.out.println(authUserMerge);
                //作业内容结束
                String methodValue = methodAnnotation.value();
                String newBaseUrl = baseUrl+methodValue;
                //handlerMapping.put(baseUrl,method);
                //把方法信息封装成一个handler对象
                Handler handler = new Handler(entry.getValue(),method, Pattern.compile(newBaseUrl));
                //放入权限用户（作业内容）
                handler.setAuthUser(authUserMerge);
                //获取参数信息
                Parameter[] parameters = method.getParameters();
                //绑定参数顺序
                for (int j = 0; j < parameters.length; j++) {
                    Parameter parameter = parameters[j];
                    if(parameter.getType()==HttpServletRequest.class||parameter.getType()==HttpServletResponse.class){
                        //如果参数为request或response那么就是获取简单的名字为HttpServletRequest或HttpServletResponse
                        handler.getParamIndexMapping().put(parameter.getType().getSimpleName(),j);
                    }else {
                        //<name,2>
                        handler.getParamIndexMapping().put(parameter.getName(),j);
                    }
                    
                }
                //缓存起来url和method之间的关系
                handlerMapping.add(handler);
            }

        }


    }

    //di
    private void doAutowired() {
        if (ioc.isEmpty()) return;
        // 遍历容器获取里面的对象属性
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            //遍历属性
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                //判断是否存在@Myautowired注解
                if (declaredField.isAnnotationPresent(MyAutowired.class)) {
                    MyAutowired annotation = declaredField.getAnnotation(MyAutowired.class);
                    declaredField.setAccessible(true);
                    String value = annotation.value();
                    //判断是否注解存在值，注入属性对象到遍历对象中
                    try {
                        if (!"".equals(value.trim())) {
                            //如果注解值不为空，就以注解中的value为key获取对象进行di
                            declaredField.set(entry.getValue(), ioc.get(value));

                        } else {
                            //如果注解中的值为空，就获取接口的全线定名为key获取对象进行注入
                            declaredField.set(entry.getValue(), ioc.get(declaredField.getType().getName()));

                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


    }

    //ioc容器
    private void doInstance() {
        if (classNames.size() == 0) return;
        try {
            for (int i = 0; i < classNames.size(); i++) {
                String className = classNames.get(i);
                Class<?> aClass = Class.forName(className);
                //如果有controller注解
                if (aClass.isAnnotationPresent(MyController.class)) {
                    //获取controller类名小写作为bean的ID,就不自定义beanid了
                    String aClassName = aClass.getSimpleName();
                    //首字母小写
                    String beanNameLower = lowerFirst(aClassName);
                    ioc.put(beanNameLower, aClass.newInstance());

                } else if (aClass.isAnnotationPresent(MyService.class)) {

                    MyService annotation = aClass.getAnnotation(MyService.class);
                    //获取注解的value值
                    String value = annotation.value();
                    //判断是否指定value，若指定就按指定的为key，否则就类名首字母小写
                    if (!"".equals(value.trim())) {
                        ioc.put(value, aClass.newInstance());
                    } else {
                        ioc.put(lowerFirst(aClass.getSimpleName()), aClass.newInstance());
                    }
                    //service层一般是有接口的在放一份接口为id对象到ioc中，便于接口依赖注入
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (int j = 0; j < interfaces.length; j++) {
                        //接口的全线定名作为id
                        ioc.put(interfaces[j].getName(), aClass.newInstance());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //首字母小写方法
    public String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] = (char) (chars[0] + 32);
        }
        return String.valueOf(chars);

    }

    //扫描类
    private void doScan(String scanPackage) {
        // 获取路径
        String scanPagePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + scanPackage.replaceAll("\\.", "/");
        System.out.println(scanPagePath);
        File pack = new File(scanPagePath);
        File[] files = pack.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //递归扫描 com.lagou.edu.controller
                doScan(scanPackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = scanPackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        //获取文件流
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 处理请求，获取uri
        Handler handler = getHandler(req);
        if (handler==null){
          //  resp.getWriter().write("404 not found!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        // 参数绑定
        // 获取我们要传入的参数数组类型，因而获取其长度
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        // 传入参数的数组，以便反射调用方法执行
        Object[] paramValues = new Object[parameterTypes.length];
        // 获取请求中的参数集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 遍历所有的参数，填充除了request 和response
        for (Map.Entry<String,String[]> parameter : parameterMap.entrySet() ) {
            //多个同类型的参数值改成,拼接形式
            String value = StringUtils.join(parameter.getValue(), ",");
            //判断参数是否在我们的handler对象的参数集合中
            if (!handler.getParamIndexMapping().containsKey(parameter.getKey())){continue;}
            //如果存在则获取index
            Integer index = handler.getParamIndexMapping().get(parameter.getKey());
            //放入要传的参数有序数组中
            paramValues[index] = value;
        }
        //最后放入req和resp
        Integer reqIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getSimpleName());
        Integer respIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getSimpleName());
        paramValues[reqIndex] = req;
        paramValues[respIndex] = resp;
        //作业内容
        //获取访问用户信息
        String username = req.getParameter("username");
        //获取权限用户信息
        String[] authUser = handler.getAuthUser();
        //判断是否在权限用户内
       if(!Arrays.asList(authUser).contains(username)){
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("无权限访问vip内容，请开通vip");
            return;
        }

        //调用handler的method方法执行
        try {
            Object invoke = handler.getMethod().invoke(handler.getController(), paramValues);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(invoke.toString());
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    // 获取handler对象
    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()){return null;}
        String url = req.getRequestURI();
        System.out.println(url);
        for (Handler handler:handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()){continue;}
                return handler;

        }
        return null;
    }

}
