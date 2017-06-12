/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Dao.*;
import DatabaseConnector.DatabaseConnector;
import Parsing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DANA
 */
public class UpdateCorpus extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCorpus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCorpus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("inside updatecorpus -->");
        String[] newspaperNames = request.getParameterValues("newspaperName");
        String[] categories = request.getParameterValues("category");
        String[] action = request.getParameterValues("action");
        for(String newsname : newspaperNames){
            System.out.println("newsname --> "+newsname);
        }
        System.out.println("action "+action[0]);
        String dateFrom = request.getParameter("dateFrom").toString();
        String dateTo = request.getParameter("dateTo").toString();
        System.out.println(dateFrom + " " + dateTo);
        HttpSession session = request.getSession();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = (Connection) databaseConnector.setConnectionWithMySql();
        session.setAttribute("connection", connection);
        NewspaperCategoryDao newspaperCategoryDao = new NewspaperCategoryDao();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (request.getParameter("action").equals("update")) {
            try {
                Date startPoint = df.parse(dateFrom);
                Date endPoint = df.parse(dateTo);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startPoint);
                for (String strName : newspaperNames) {
                    System.out.println("strname " + strName);
//                    if (strName.equals("bdnews24")){
//                        Tester tester = new Tester();
//                        tester.bdnews242(strName, connection);
//                        break;
//                    }
                    for (String strCat : categories) {
                        System.out.println("now starting category " + strCat);
                        calendar.setTime(startPoint);
                        Date checkerDate = calendar.getTime();
                        while (checkerDate.getTime() <= endPoint.getTime()) {
                            try {
                                System.out.println("timestamp " + df.format(checkerDate));
                                JSOUP jsoup = new JSOUP();
                                jsoup.goParse(strName, df.format(checkerDate), strCat, connection);
                                calendar.add(Calendar.DAY_OF_MONTH, 1);
                                checkerDate = calendar.getTime();
                            } catch (NullPointerException e) {
                                System.out.println("next date");
                                calendar.add(Calendar.DAY_OF_MONTH, 1);
                                checkerDate = calendar.getTime();
                            }
                        }
                    }
                }
                System.out.println("___________________FINISHED_______________");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("action").equals("download")) {
            try {
                int count = 0;
                Date startPoint = df.parse(dateFrom);
                Date endPoint = df.parse(dateTo);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startPoint);
                int c, downloadCount = 0;
                try {
                    File f = new File("F:\\Download\\New Text Document.txt");
                    Scanner scanner = new Scanner(f);
                    int i = 0;
                    while (scanner.hasNext()) {
                        downloadCount = Integer.parseInt(scanner.next());
                    }
                    System.out.println("this is it " + downloadCount);
                    scanner.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("ERROR: Level data not found " + ex.getMessage());
                }
                BufferedWriter outputWriter = null;
                try {
                    outputWriter = new BufferedWriter(new FileWriter("F:\\Download\\New Text Document.txt"));
                    if (downloadCount == 50) {
                        outputWriter.write(Integer.toString(0));
                    } else {
                        outputWriter.write(Integer.toString(downloadCount + 1));
                    }
                    outputWriter.flush();
                    outputWriter.close();
                } catch (Exception e) {
                }
                for (String strName : newspaperNames) {
                    System.out.println("strname " + strName);
                    for (String strCat : categories) {
                        System.out.println("now starting category " + strCat);
                        calendar.setTime(startPoint);
                        Date checkerDate = calendar.getTime();
                        while (checkerDate.getTime() <= endPoint.getTime()) {
                            try {
                                System.out.println("timestamp " + df.format(checkerDate));
                                Downloader downloader = new Downloader();
                                count = downloader.readWrite(downloadCount, strName, df.format(checkerDate), strCat);
                                calendar.add(Calendar.DAY_OF_MONTH, 1);
                                checkerDate = calendar.getTime();
                            } catch (NullPointerException e) {
                                System.out.println("next date");
                                calendar.add(Calendar.DAY_OF_MONTH, 1);
                                checkerDate = calendar.getTime();
                            }
                        }
                    }
                }
                //start here 
                String filePath = "F:\\C" + count + ".zip";
                File file = new File(filePath);
                int length = 0;
                ServletOutputStream outStream = response.getOutputStream();
                response.setContentType("text/html");
                response.setContentLength((int) file.length());
                String fileName = (new File(filePath)).getName();
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + fileName + "\"");

                byte[] byteBuffer = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
                    outStream.write(byteBuffer, 0, length);
                }

                in.close();
                outStream.close();
                //ends here 
                System.out.println("___________________FINISHED_______________");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
