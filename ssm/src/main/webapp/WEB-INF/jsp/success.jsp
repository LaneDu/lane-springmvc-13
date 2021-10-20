<%@ page import="com.lagou.edu.pojo.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    List<String> list = new ArrayList<String>();
    list.add("简单是可靠的先决条件");
    list.add("兴趣是最好的老师");
    list.add("知识上的投资总能得到最好的回报");
    request.setAttribute("list", list);
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Insert title here</title>
</head>
<body>
跳转成功！服务器时间：${date}

<c:forEach items="${accountList}" var="account" varStatus="id">
    ${id.index} ${account.name}<hr>
    ${account}


</c:forEach>

<hr>
<c:forEach items="${requestScope.list}" var="keyword" varStatus="id">
    ${id.index} ${keyword}<br>
</c:forEach>

</body>
</html>