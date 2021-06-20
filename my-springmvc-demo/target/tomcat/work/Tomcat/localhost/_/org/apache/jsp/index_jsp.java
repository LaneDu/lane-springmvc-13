/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-04-02 03:03:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>SpringMVC 测试页</title>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        div{\n");
      out.write("            padding:10px 10px 0 10px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div>\n");
      out.write("    <h2>Spring MVC 请求参数绑定</h2>\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC 对原生servlet api的支持</p>\n");
      out.write("        <a href=\"/demo/handle02?id=1\">点击测试</a>\n");
      out.write("    </fieldset>\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC 接收简单数据类型参数</p>\n");
      out.write("        <a href=\"/demo/handle03?id=1\">点击测试</a>\n");
      out.write("    </fieldset>\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC 使用@RequestParam 接收简单数据类型参数(形参名和参数名不一致)</p>\n");
      out.write("    </fieldset>\n");
      out.write("\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC接收pojo类型参数</p>\n");
      out.write("        <a href=\"/demo/handle04?id=1&name=zhangsan\">点击测试</a>\n");
      out.write("    </fieldset>\n");
      out.write("\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC接收pojo包装类型参数</p>\n");
      out.write("        <a href=\"/demo/handle05?user.id=1&user.name=zhangsan\">点击测试</a>\n");
      out.write("    </fieldset>\n");
      out.write("\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC接收日期类型参数</p>\n");
      out.write("        <a href=\"/demo/handle06?birthday=2019-10-08\">点击测试</a>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <h2>SpringMVC对Restful风格url的支持</h2>\n");
      out.write("    <fieldset>\n");
      out.write("        <p>测试用例：SpringMVC对Restful风格url的支持</p>\n");
      out.write("\n");
      out.write("        <a href=\"/demo/handle/15\">rest_get测试</a><br/>\n");
      out.write("        <a href=\"/demo/handle/7\">rest_get测试7</a>\n");
      out.write("\n");
      out.write("        <form method=\"post\" action=\"/demo/handle\">\n");
      out.write("            <input type=\"text\" name=\"username\"/>\n");
      out.write("            <input type=\"submit\" value=\"提交rest_post请求\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <form method=\"post\" action=\"/demo/handle/15/lisi\">\n");
      out.write("            <input type=\"hidden\" name=\"_method\" value=\"put\"/>\n");
      out.write("            <input type=\"submit\" value=\"提交rest_put请求\"/>\n");
      out.write("        </form>\n");
      out.write("        <form method=\"post\" action=\"/demo/handle/7/lisi\">\n");
      out.write("            <input type=\"hidden\" name=\"_method\" value=\"put\"/>\n");
      out.write("            <input type=\"submit\" value=\"提交restful put请求\">\n");
      out.write("        </form>\n");
      out.write("        <form method=\"post\" action=\"/demo/handle/8/wangwu\">\n");
      out.write("            <input type=\"hidden\" name=\"_method\" value=\"delete\"/>\n");
      out.write("            <input type=\"submit\" value=\"提交restful delete请求\">\n");
      out.write("        </form>\n");
      out.write("        <form method=\"post\" action=\"/demo/handle/15\">\n");
      out.write("            <input type=\"hidden\" name=\"_method\" value=\"delete\"/>\n");
      out.write("            <input type=\"submit\" value=\"提交rest_delete请求\"/>\n");
      out.write("        </form>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <h2>Ajax json交互</h2>\n");
      out.write("    <fieldset>\n");
      out.write("        <input type=\"button\" id=\"ajaxBtn\" value=\"ajax提交\"/>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("    <h3>ajax json交互</h3>\n");
      out.write("    <fieldset>\n");
      out.write("        <input type=\"button\" id=\"ajaxbtn2\" value=\"ajax提交2\"/>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("    <h3>ajax json交互</h3>\n");
      out.write("    <fieldset>\n");
      out.write("        <input type=\"button\" id=\"ajaxbtn3\" value=\"ajax提交3\"/>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <fieldset>\n");
      out.write("        ");
      out.write("\n");
      out.write("        <form method=\"post\" action=\"/demo/upload\" enctype=\"multipart/form-data\">\n");
      out.write("            <input type=\"file\" name=\"uplaodFile\">\n");
      out.write("            <input type=\"submit\" value=\"文件上传\">\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div >\n");
      out.write("    <a href=\"/demo/handle09\">会出现异常</a>\n");
      out.write("    <a href=\"/demo/dispatcher01\">转发01</a>\n");
      out.write("    <a href=\"/demo/dispatcher02?date=2021-4-2\">转发02</a>\n");
      out.write("    <a href=\"/demo/redirect01?date=2021-4-2\">重定向01</a>\n");
      out.write("    <a href=\"/demo/redirect02?date=2021-4-2\">重定向02</a>\n");
      out.write("    <a href=\"/demo/redirect03?date=2021-4-2\">重定向03</a>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("<script>\n");
      out.write("    jQuery(function () {\n");
      out.write("        $(\"#ajaxbtn2\").bind(\"click\",function () {\n");
      out.write("            $.ajax({\n");
      out.write("                url:'/demo/handle07',\n");
      out.write("                type:'POST',\n");
      out.write("                // data:{\"id\":1,\"username\":\"lisi\"},\n");
      out.write("                data:'{\"id\":\"1\",\"username\":\"lisi\"}',\n");
      out.write("                contentType:'application/json;charset=utf-8',\n");
      out.write("                dataType: 'json',\n");
      out.write("                success:function (data) {\n");
      out.write("                    alter(data);\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("        })\n");
      out.write("    })\n");
      out.write("\n");
      out.write("    jQuery(function () {\n");
      out.write("        $(\"#ajaxbtn3\").bind(\"click\",function () {\n");
      out.write("\n");
      out.write("            $.ajax({\n");
      out.write("                url:'/demo/handle08',\n");
      out.write("                type:'POST',\n");
      out.write("                data:'{\"id\":\"1\",\"username\":\"lisi\"}',\n");
      out.write("                contentType:'application/json;charset=utf-8',//指定发送数据类型\n");
      out.write("                dataType: 'json',//指定返回数据类型为json\n");
      out.write("                success:function (data) {\n");
      out.write("                    alert(data.username);\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("\n");
      out.write("        });\n");
      out.write("    })\n");
      out.write("</script>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}