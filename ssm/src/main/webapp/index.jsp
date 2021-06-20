<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC 测试页</title>


    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <style>
        div{
            padding:10px 10px 0 10px;
        }
    </style>
</head>
<body>
<%--<div>
    <h2>Spring MVC 请求参数绑定</h2>
    <fieldset>
        <p>测试用例：SpringMVC 对原生servlet api的支持</p>
        <a href="/demo/handle02?id=1">点击测试</a>
    </fieldset>
    <fieldset>
        <p>测试用例：SpringMVC 接收简单数据类型参数</p>
        <a href="/demo/handle03?id=1">点击测试</a>
    </fieldset>
    <fieldset>
        <p>测试用例：SpringMVC 使用@RequestParam 接收简单数据类型参数(形参名和参数名不一致)</p>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收pojo类型参数</p>
        <a href="/demo/handle04?id=1&name=zhangsan">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收pojo包装类型参数</p>
        <a href="/demo/handle05?user.id=1&user.name=zhangsan">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收日期类型参数</p>
        <a href="/demo/handle06?birthday=2019-10-08">点击测试</a>
    </fieldset>
</div>

<div>
    <h2>SpringMVC对Restful风格url的支持</h2>
    <fieldset>
        <p>测试用例：SpringMVC对Restful风格url的支持</p>

        <a href="/demo/handle/15">rest_get测试</a><br/>
        <a href="/demo/handle/7">rest_get测试7</a>

        <form method="post" action="/demo/handle">
            <input type="text" name="username"/>
            <input type="submit" value="提交rest_post请求"/>
        </form>


        <form method="post" action="/demo/handle/15/lisi">
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="提交rest_put请求"/>
        </form>
        <form method="post" action="/demo/handle/7/lisi">
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="提交restful put请求">
        </form>
        <form method="post" action="/demo/handle/8/wangwu">
            <input type="hidden" name="_method" value="delete"/>
            <input type="submit" value="提交restful delete请求">
        </form>
        <form method="post" action="/demo/handle/15">
            <input type="hidden" name="_method" value="delete"/>
            <input type="submit" value="提交rest_delete请求"/>
        </form>
    </fieldset>
</div>--%>



<div>
    <h3>ajax json交互</h3>
    <fieldset>
        <input type="button" id="ajaxbtn2" value="ajax提交对象"/>
    </fieldset>
</div>
<div>
    <h3>ajax json交互</h3>
    <fieldset>
        <input type="button" id="ajaxbtn3" value="ajax提交json字符串"/>
    </fieldset>
</div>


</body>
<script>
    jQuery(function () {
        $("#ajaxbtn2").bind("click",function () {
            $.ajax({
                url:'/account/queryTest1',
                type:'POST',
                data:{"id":1,"username":"lisi"},
               // dataType: 'json',//指定返回数据类型为json
                success:function (data) {

                    alert(data);
                //    var jsonStr = JSON.stringify(data);       //转为JSON字符串
                //    console.log(jsonStr);
                //    alert(jsonStr);
                },
                error:function () {
                    alert("error")

                }
            })
        })
    })

    jQuery(function () {
        $("#ajaxbtn3").bind("click",function () {

            $.ajax({
                url:'/account/queryTest2',
                type:'POST',
                data:'{"id":"2","username":"zhangsan"}',
                contentType:'application/json;charset=utf-8',//指定发送数据类型
                dataType: 'json',//指定返回数据类型为json
                success:function (data) {
                    alert(data.username);
                }
            })

        });
    })
</script>
</html>
