package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("\n");
      out.write("    <head> \n");
      out.write("        <title>jQuery MultiSelect Demo</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/demo.css\"></link>\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/form-login.css\"></link>\n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"javascript/jquery.bgiframe.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"javascript/jquery.multiSelect.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("        <link href=\"assets/jquery.multiSelect.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("            $(document).ready(function () {\n");
      out.write("\n");
      out.write("                // Default options\n");
      out.write("                $(\"#control_1, #control_3, #control_4, #control_5\").multiSelect();\n");
      out.write("\n");
      out.write("                \n");
      out.write("\n");
      out.write("            });\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("            HTML {\n");
      out.write("                font-family: Arial, Helvetica, sans-serif;\n");
      out.write("                font-size: 12px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            H2 {\n");
      out.write("                font-size: 14px;\n");
      out.write("                font-weight: bold;\n");
      out.write("                margin: 1em 0em .25em 0em;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            P {\n");
      out.write("                margin: 1em 0em;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <h1>Bangla Corpus</h1>\n");
      out.write("            <a href=\"\">Download</a>\n");
      out.write("        </header>\n");
      out.write("        <form style=\"margin-left: 500px\" action=\"\" method=\"post\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"form-white-background\">\n");
      out.write("                <div class=\"form-title-row\" >\n");
      out.write("                    <h1>Select Newspapers</h1>\n");
      out.write("                </div>\n");
      out.write("                <span class=\"select2-selection__arrow\" role=\"presentation\"><b role=\"presentation\"></b></span>\n");
      out.write("                <select id=\"control_3\" class=\"ui-icon ui-icon-triangle-2-n-s\" name=\"newspaperName\" multiple=\"multiple\" size=\"5\">\n");
      out.write("                    \n");
      out.write("                    <option value=\"\" ></option>\n");
      out.write("                    <option value=\"prothomAlo\" > ProthomAlo</option>\n");
      out.write("                    <option value=\"kalerKantho\" > KalerKantho</option>\n");
      out.write("                    <option value=\"manabZamin\" > ManabZamin</option>\n");
      out.write("                    <option value=\"bdnews24\" > BdNews24</option>\n");
      out.write("                    <option value=\"banglanews24\" > BanglaNews24</option>\n");
      out.write("                    <option value=\"ittefaq\" > Ittefaq</option>\n");
      out.write("                </select>\n");
      out.write("                <div class=\"form-row form-last-row\">\n");
      out.write("                    <button type=\"submit\">Get Data</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
