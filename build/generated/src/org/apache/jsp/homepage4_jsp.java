package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homepage4_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Bootstrap-select test page</title>\n");
      out.write("\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/bootstrap-select.css\">\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                padding-top: 70px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"javascript/bootstrap-select.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <a class=\"navbar-brand\" href=\"#\">Bangla News Corpus</a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"container\" style=\"text-align: center\">\n");
      out.write("            <!--  <nav class=\"navbar navbar-default\" role=\"navigation\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <div class=\"navbar-header\" style=\"float: right\" >\n");
      out.write("                    <a class=\"navbar-brand\" href=\"#\">Admin Mode</a>\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("                 .container-fluid \n");
      out.write("              </nav>-->\n");
      out.write("            <hr>\n");
      out.write("            \n");
      out.write("            <form method=\"post\" action=\"UpdateCorpus\" >\n");
      out.write("                <label>Select Newspaper</label><br>\n");
      out.write("                <select id=\"first-disabled2\" name=\"newspaperName\" class=\"selectpicker\" multiple data-hide-disabled=\"true\" data-size=\"5\">\n");
      out.write("                    <option value=\"prothomAlo\" >Prothom Alo</option>\n");
      out.write("                    <option value=\"kalerKantho\" >Kaler Kantho</option>\n");
      out.write("                    <option value=\"manabZamin\" >Manab Zamin</option>\n");
      out.write("                    <option value=\"bdnews24\" >Bdnews24</option>\n");
      out.write("                    <option value=\"banglanews24\" >Banglanews24</option>\n");
      out.write("                    <option value=\"ittefaq\" >Ittefaq</option>\n");
      out.write("                </select>\n");
      out.write("                <hr>\n");
      out.write("                <label>Select Category</label><br>\n");
      out.write("                <select id=\"first-disabled2\" name=\"category\" class=\"selectpicker\" multiple data-hide-disabled=\"true\" data-size=\"5\">\n");
      out.write("                    <option value=\"sports\" >Sports</option>\n");
      out.write("                    <option value=\"entertainment\" >Entertainment</option>\n");
      out.write("                    <option value=\"art-and-literature\" >Literature</option>\n");
      out.write("                    <option value=\"international\" >International</option>\n");
      out.write("                    <option value=\"education\" >Education</option>\n");
      out.write("                    <option value=\"opinion\" >Opinion</option>\n");
      out.write("                    <option value=\"politics\" >Politics</option>\n");
      out.write("                    <option value=\"technology\" >Technology</option>\n");
      out.write("                    <option value=\"crime\" >Crime</option>\n");
      out.write("                    <option value=\"accident\" >Accident</option>\n");
      out.write("                    <option value=\"economy\" >Economy </option>\n");
      out.write("                    <option value=\"life-style\" > Lifestyle </option>\n");
      out.write("                </select>\n");
      out.write("                <hr>\n");
      out.write("                <label >Select Action</label ><br>\n");
      out.write("                <select id=\"first-disabled2\" name=\"action\" class=\"selectpicker\" multiple data-hide-disabled=\"true\" data-size=\"5\">\n");
      out.write("                    <option value=\"download\" > Download </option>\n");
      out.write("                    <option value=\"update\" > Update </option>\n");
      out.write("                </select>\n");
      out.write("                <hr>\n");
      out.write("                <p>From <input id=\"datefield1\" name=\"dateFrom\" type='date' min='1899-01-01' onclick=\"setDateToToday()\">&ensp;</input> To \n");
      out.write("                    <input id=\"datefield2\" name=\"dateTo\" type='date' min='1899-01-01' onclick=\"setDateToToday()\"></input></p><br>\n");
      out.write("                <button type=\"submit\" class=\"btn btn-success\">GO</button>\n");
      out.write("            </form>\n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                var mySelect = $('#first-disabled2');\n");
      out.write("\n");
      out.write("                $('#special').on('click', function () {\n");
      out.write("                    mySelect.find('option:selected').prop('disabled', true);\n");
      out.write("                    mySelect.selectpicker('refresh');\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                $('#special2').on('click', function () {\n");
      out.write("                    mySelect.find('option:disabled').prop('disabled', false);\n");
      out.write("                    mySelect.selectpicker('refresh');\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                $('#basic2').selectpicker({\n");
      out.write("                    liveSearch: true,\n");
      out.write("                    maxOptions: 1\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("            function setDateToToday() {\n");
      out.write("                var today = new Date();\n");
      out.write("                var dd = today.getDate();\n");
      out.write("                var mm = today.getMonth() + 1; //January is 0!\n");
      out.write("                var yyyy = today.getFullYear();\n");
      out.write("                if (dd < 10) {\n");
      out.write("                    dd = '0' + dd\n");
      out.write("                }\n");
      out.write("                if (mm < 10) {\n");
      out.write("                    mm = '0' + mm\n");
      out.write("                }\n");
      out.write("                today = yyyy + '-' + mm + '-' + dd;\n");
      out.write("                document.getElementById(\"datefield1\").setAttribute(\"max\", today);\n");
      out.write("                document.getElementById(\"datefield2\").setAttribute(\"max\", today);\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
