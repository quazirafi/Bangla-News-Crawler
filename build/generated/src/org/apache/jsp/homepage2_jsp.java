package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homepage2_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("        <title>Corpus Home</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/demo.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/form-login.css\">\n");
      out.write("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<!--\t\t<script src=\"javascript/jquery.bgiframe.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\t\t<script src=\"javascript/jquery.multiSelect.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("\t\t<link href=\"assets/jquery.multiSelect.css\" rel=\"stylesheet\" type=\"text/css\" />-->\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t$(document).ready( function() {\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t// Default options\n");
      out.write("\t\t\t\t$(\"#control_1, #control_3, #control_4, #control_5\").multiSelect();\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t// With callback\n");
      out.write("\t\t\t\t$(\"#control_6\").multiSelect( null, function(el) {\n");
      out.write("\t\t\t\t\t$(\"#callbackResult\").show().fadeOut();\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t// Options displayed in comma-separated list\n");
      out.write("\t\t\t\t$(\"#control_7\").multiSelect({ oneOrMoreSelected: '*' });\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t// 'Select All' text changed\n");
      out.write("\t\t\t\t$(\"#control_8\").multiSelect({ selectAllText: 'Pick &lsquo;em all!' });\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t// Show test data\n");
      out.write("\t\t\t\t$(\"FORM\").submit( function() {\n");
      out.write("\t\t\t\t\t$.post('result.php', $(this).serialize(), function(r) {\n");
      out.write("\t\t\t\t\t\talert(r);\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t\treturn false;\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t\n");
      out.write("\t\t</script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("    <header>\n");
      out.write("        <h1>Bangla Corpus</h1>\n");
      out.write("        <a href=\"\">Download</a>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"main-content\">\n");
      out.write("\n");
      out.write("        <!-- You only need this form and the form-login.css -->\n");
      out.write("\n");
      out.write("        <form class=\"form-login\" method=\"post\" action=\"UpdateCorpus\">\n");
      out.write("\n");
      out.write("            <div class=\"form-log-in-with-email\">\n");
      out.write("\n");
      out.write("                <div class=\"form-white-background\">\n");
      out.write("\n");
      out.write("                    <div class=\"form-title-row\">\n");
      out.write("                        <h1>Select Newspapers</h1>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"form-row\">\n");
      out.write("                        <label>\n");
      out.write("                            <span>Prothom Alo</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"prothomAlo\" name=\"newspaperName\" >\n");
      out.write("                        </label>   \n");
      out.write("                        <label>\n");
      out.write("                            <span>Kaler Kantho</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"kalerKantho\" name=\"newspaperName\">\n");
      out.write("                        </label>\n");
      out.write("                        <label>\n");
      out.write("                            <span>Manab Zamin</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"manabZamin\" name=\"newspaperName\">\n");
      out.write("                        </label>   \n");
      out.write("                        <label>\n");
      out.write("                            <span>Bdnews24</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"bdnews24\" name=\"newspaperName\">\n");
      out.write("                        </label>\n");
      out.write("                        <label>\n");
      out.write("                            <span>Banglanews24</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"banglanews24\" name=\"newspaperName\">\n");
      out.write("                        </label>   \n");
      out.write("                        <label>\n");
      out.write("                            <span>Ittefaq</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"ittefaq\" name=\"newspaperName\">\n");
      out.write("                        </label>\n");
      out.write("                        <label>\n");
      out.write("                            <span>BdNews24</span>\n");
      out.write("                            <input type=\"checkbox\" value=\"bdnews24\" name=\"newspaperName\">\n");
      out.write("                        </label>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"form-title-row\">\n");
      out.write("                        <h1>Select Categories</h1>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <div class=\"form-row\">\n");
      out.write("                        <select id=\"control_3\" multiple=\"multiple\" size=\"1\" name=\"category\">\n");
      out.write("                                <option value=\"sports\" >Sports</option>\n");
      out.write("                                <option value=\"entertainment\" >Entertainment</option>\n");
      out.write("                                <option value=\"art-and-literature\" >Literature</option>\n");
      out.write("                                <option value=\"international\" >International</option>\n");
      out.write("                                <option value=\"education\" >Education</option>\n");
      out.write("                                <option value=\"opinion\" >Opinion</option>\n");
      out.write("                                <option value=\"politics\" >Politics</option>\n");
      out.write("                                <option value=\"technology\" >Technology</option>\n");
      out.write("                                <option value=\"crime\" >Crime</option>\n");
      out.write("                                <option value=\"accident\" >Accident</option>\n");
      out.write("                                <option value=\"economy\" >Economy</option>\n");
      out.write("                                <option value=\"life-style\" >LifeStyle</option>\n");
      out.write("                            </select>\n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-title-row\">\n");
      out.write("                        <h1>Select Date Interval</h1>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-row\">\n");
      out.write("                        <div class=\"form-row\">\n");
      out.write("                            <input type=\"text\" name=\"dateFrom\" placeholder=\"Date From\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-row\">\n");
      out.write("                            <input type=\"text\" name=\"dateTo\" placeholder=\"Date To\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <div class=\"form-row form-last-row\">\n");
      out.write("                    <button type=\"submit\">Get Data</button>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
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
