/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-04-07 11:47:21 UTC
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
      out.write("    <style>\n");
      out.write("        div{\n");
      out.write("            padding:10px 10px 0 10px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <h3>ajax json交互</h3>\n");
      out.write("    <fieldset>\n");
      out.write("        <input type=\"button\" id=\"ajaxbtn2\" value=\"ajax提交对象\"/>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("    <h3>ajax json交互</h3>\n");
      out.write("    <fieldset>\n");
      out.write("        <input type=\"button\" id=\"ajaxbtn3\" value=\"ajax提交json字符串\"/>\n");
      out.write("    </fieldset>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("<script>\n");
      out.write("    jQuery(function () {\n");
      out.write("        $(\"#ajaxbtn2\").bind(\"click\",function () {\n");
      out.write("            $.ajax({\n");
      out.write("                url:'/account/queryTest1',\n");
      out.write("                type:'POST',\n");
      out.write("                data:{\"id\":1,\"username\":\"lisi\"},\n");
      out.write("               // dataType: 'json',//指定返回数据类型为json\n");
      out.write("                success:function (data) {\n");
      out.write("\n");
      out.write("                    alert(data);\n");
      out.write("                //    var jsonStr = JSON.stringify(data);       //转为JSON字符串\n");
      out.write("                //    console.log(jsonStr);\n");
      out.write("                //    alert(jsonStr);\n");
      out.write("                },\n");
      out.write("                error:function () {\n");
      out.write("                    alert(\"error\")\n");
      out.write("\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("        })\n");
      out.write("    })\n");
      out.write("\n");
      out.write("    jQuery(function () {\n");
      out.write("        $(\"#ajaxbtn3\").bind(\"click\",function () {\n");
      out.write("\n");
      out.write("            $.ajax({\n");
      out.write("                url:'/account/queryTest2',\n");
      out.write("                type:'POST',\n");
      out.write("                data:'{\"id\":\"2\",\"username\":\"zhangsan\"}',\n");
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
