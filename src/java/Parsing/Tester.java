/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import Dao.NewspaperCategoryDao;
import DatabaseConnector.DatabaseConnector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author DANA
 */
public class Tester {

    static NewspaperCategoryDao newspaperCategoryDao;

    static Connection conn = null;

    //public void bdnews24(String newsName, Connection conn) {
    public static void main(String args[]) {
        newspaperCategoryDao = new NewspaperCategoryDao();

        Tester tester = new Tester();
        conn = DatabaseConnector.setConnectionWithMySql();
        int i = 1;
        while (i < 500) {
            try {
                for (; i < 500; ++i) {
                    //Document news = Jsoup.connect("http://bangla.bdnews24.com/search/simple.do;jsessionid=66721851C0F0D9BE346EEAC78FD8EFBA.pre11?articleTypes=news-bn+news-district+news-kidz&sectionId=68&searchString=লাইফস্টাইল&sortString=publishdate&sortOrder=desc&destinationSectionId=80&pageLength=20&publicationName=bangla&pageNumber=" + i)
                    Document news = Jsoup.connect("http://opinion.bdnews24.com/bangla/")
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                            .referrer("http://www.google.com")
                            .get();
                    Elements divs = news.getElementsByAttributeValue("class", "article ");
                    for (Element l : divs) {
                        if (!l.select("a[href]").attr("href").toString().contains("glitz") && !l.select("a[href]").attr("href").toString().contains("sports")) {
                            System.out.println(l.select("a[href]").attr("href").toString());
                            tester.bdnews242("bdnews24", l.select("a[href]").attr("href").toString(), conn);
                        }
                    }
                    System.out.println("i = " + i);
                }

            } catch (Exception e) {
                System.out.println("here main method");
                i += 1;
                e.printStackTrace();
            }
        }

    }

    public void bdnews242(String strName, String link, Connection conn) {
        try {
            Document news = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();
            String title = news.select("h1").first().text();
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, "opinion", title, conn) != 1) {

                Elements datePrintOnly = news.getElementsByAttributeValueContaining("class",
                        "dateline print-only");
                String newsDate = "";
                for (Element l : datePrintOnly) {
                    newsDate = l.text().substring(l.text().indexOf("20"), l.text().indexOf("20") + 10);
                    break;
                }

                System.out.println("this is news date " + newsDate);
                String newFileStr = "F:\\Corpus\\" + "opinion" + "\\" + strName + "\\" + newsDate + "\\";
                File createFile = new File(newFileStr);
                if (!createFile.exists()) {
                    createFile.mkdirs();
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                out.write("<source> " + "বিডিনিউজ24" + " </source>");
                out.write(System.getProperty("line.separator"));
                out.write("<date> " + newsDate + " </date>");
                out.write(System.getProperty("line.separator"));
                out.write("<category> opinion </category>");
                out.write(System.getProperty("line.separator"));
                out.write("<title> " + title + " </title>");
                out.write(System.getProperty("line.separator"));
                Elements paragraphs = news.select("p");

                for (Element p : paragraphs) {
                    System.out.println("inside para");
                    String tempText = p.text();
                    String finalText = "";
                    int emptyLine = 1;
                    for (int j = 0; j < tempText.length(); ++j) {
                        //if (((int)tempText.charAt(j)>='a' && tempText.charAt(j)<='z') || (tempText.charAt(j)>='A' && tempText.charAt(j)<='Z') || (tempText.charAt(j)>='0' && tempText.charAt(j)<='9')){
                        if (((int) tempText.charAt(j) >= 2432 && (int) tempText.charAt(j) <= 2559) || (tempText.charAt(j) == ' ') || (tempText.charAt(j) == ',') || (tempText.charAt(j) == '।')) {
                            finalText += tempText.charAt(j);
                        }
                        if ((int) tempText.charAt(j) >= 2432 && (int) tempText.charAt(j) <= 2559) {
                            emptyLine = 0;
                        }

                    }
                    if (emptyLine == 0) {
                        out.write("<p> " + finalText + " </p>");
                        out.write(System.getProperty("line.separator"));
                    }

                }
                out.close();
                JSOUP.count += 1;
                newspaperCategoryDao.insertNews(strName, "opinion", newsDate, title, conn);

            }

        } catch (IOException err) {
            System.out.println("here 242");
            err.printStackTrace();
        } catch (NullPointerException err) {
            throw err;
        } catch (Exception err) {
            System.out.println("here 242 exception");
            err.printStackTrace();
        }
    }
}
