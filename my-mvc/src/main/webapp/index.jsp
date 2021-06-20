<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>某 Hello World网站</h2>
<h4>用户信息</h4>
普通用户：李四<br/>
vip用户：张三<br/>
<h4>访问信息</h4>

<a href="/access/free?username=zhangsan">张三访问免费用户内容</a><br/>
<a href="/access/free?username=lisi">李四用户免费用户内容</a><br/>
<a href="/access/vip?username=zhangsan">张三访问vip内容</a><br/>
<a href="/access/vip?username=lisi">李四访问vip内容</a><br/>

</body>
</html>
