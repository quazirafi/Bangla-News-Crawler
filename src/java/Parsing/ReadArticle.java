package Parsing;

import Dao.*;
import java.io.BufferedWriter;
import java.io.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;

public class ReadArticle {

    NewspaperCategoryDao newspaperCategoryDao;

    public ReadArticle(String strName, Element link, String date, String category, Connection conn) throws IOException {
        ProthomAlo(strName, link, date, category, conn);
        //kalerkonto(strName, link, date, category, conn);
        //bdnews24(strName, link, date, category, conn);
        //banglanews24(strName, link, date, category, conn);
        //System.out.println("this is the link --> "+link.attr("abs:href").toString());
        //mzamin(strName, link, date, category, conn);
        //ittefaq(strName, link, date, category, conn);

    }

    public void ittefaq(String strName, Element link, String date, String category, Connection conn) {
        try {
            Document news = Jsoup.connect(link.select("a").attr("href"))
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();
            String title = news.getElementsByAttributeValue("class", "headline2").text();
            Elements paras = news.getElementsByAttributeValue("class", "details").select("div");
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {
                System.out.println("insdie inside inside");
                String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                File createFile = new File(newFileStr);
                if (!createFile.exists()) {
                    createFile.mkdirs();
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                out.write("ইত্তেফাক");
                out.write(System.getProperty("line.separator"));
                out.write(date);
                out.write(System.getProperty("line.separator"));
                out.write(category);
                out.write(System.getProperty("line.separator"));
                out.write(title);
                out.write(System.getProperty("line.separator"));
                int flag = 0;
                for (Element para : paras) {
                    if (flag == 0) {
                        flag = 1;
                        continue;
                    }

//                    Elements divs = para.select("div");
//                    for (Element div : divs) {
                    String tempText = para.text();//div
                    String finalText = "";
                    for (int i = 0; i < tempText.length(); ++i) {
                        if (((int) tempText.charAt(i) >= 2432 && (int) tempText.charAt(i) <= 2559) || (tempText.charAt(i) == ' ') || (tempText.charAt(i) == ',') || (tempText.charAt(i) == '।')) {
                            finalText += tempText.charAt(i);
                        }
                    }
                    System.out.println(finalText);
                    if (finalText.length() > 10) {
                        out.write(finalText);
                        out.write(System.getProperty("line.separator"));
                    }
                    //}
                }
                out.close();
                JSOUP.count += 1;
                newspaperCategoryDao.insertNews(strName, category, date, title, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ProthomAlo(String strName, Element link, String date, String category, Connection conn) {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").get();
            String title = news.getElementsByClass("right_title").select("h1").text();
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {
                if (category.equals("crime")||category.equals("accident")||category.equals("politics")) {
                    String categoryOfThisPaper = "";
                    if (category.equals("crime"))
                        categoryOfThisPaper = "অপরাধ";
                    else if (category.equals("accident"))
                        categoryOfThisPaper = "দুর্ঘটনা";
                    else if (category.equals("politics"))
                        categoryOfThisPaper = "রাজনীতি";
                    System.out.println(categoryOfThisPaper);
                    Elements crimeWordContainingElements = news.getElementsByAttributeValue("class", "topic_list");
                    for (Element e : crimeWordContainingElements) {
                        String crimeWord = e.select("a[href]").toString();
                        if (crimeWord.contains(categoryOfThisPaper)) {
                            System.out.println("inside topic list");
                            if (title.length() > 10000) {
                                title = title.substring(0, 9999);
                            }

                            System.out.println(title);
                            Elements additionalInfo = news.getElementsByClass("fl additional_info");
                            Elements paragraphs = news.select("article").first().select("p");
                            String body = "";
                            String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                            File createFile = new File(newFileStr);
                            if (!createFile.exists()) {
                                createFile.mkdirs();
                            }
                            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                            out.write("<source> প্রথম আলো"
                                    + "" + " </source> ");
                            out.write(System.getProperty("line.separator"));
                            //out.write(news.select("span[itemprop=\"datePublished\"]").text());
                            out.write("<date> " + date + " </date> ");
                            out.write(System.getProperty("line.separator"));
                            out.write("<category> " + category + " </category> ");
                            out.write(System.getProperty("line.separator"));
                            out.write("<title> " + title + " </title> ");
                            out.write(System.getProperty("line.separator"));

                            ArrayList<String> paras = new ArrayList<String>();
                            for (Element p : paragraphs) {
                                StringBuilder sb = new StringBuilder(p.html());
                                String ss = "";
                                for (int i = 0; i < sb.length(); ++i) {
                                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                                        while (sb.charAt(i) != '>') {
                                            ++i;
                                        }
                                        ++i;
                                    }
                                    ss += sb.charAt(i);
                                }
                                StringBuilder s3 = new StringBuilder(ss.toString());
                                ss = "";
                                String[] brokenStr = s3.toString().split("</br>|<br>|</p>|<p>");
                                for (int i = 0; i < brokenStr.length; ++i) {
                                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                                    brokenStr[i] = "";
                                    for (int j = 0; j < brokenStr2.length; ++j) {
                                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                                        for (int k = 0; k < stb.length(); ++k) {
                                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                                    || stb.charAt(k) == '&') {
                                                continue;
                                            } else {
                                                brokenStr[i] += stb.charAt(k);
                                            }
                                        }
                                    }
                                }
                                for (int i = 0; i < brokenStr.length; ++i) {
                                    if (brokenStr[i].length() > 2) {
                                        //from here 
                                        String finalText = "";
                                        finalText += "<p> ";
                                        for (int j = 0; j < brokenStr[i].length(); ++j) {
                                            if (((int) brokenStr[i].charAt(j) >= 2432 && (int) brokenStr[i].charAt(j) <= 2559) || (brokenStr[i].charAt(j) == ' ') || (brokenStr[i].charAt(j) == ',') || (brokenStr[i].charAt(j) == '।')) {
                                                finalText += brokenStr[i].charAt(j);
                                            }
                                        }
                                        //to here
                                        finalText += " </p> ";
                                        System.out.println(finalText);
                                        if (finalText.length() >= 18) {
                                            out.write(finalText.toString());
                                            out.write(System.getProperty("line.separator"));
                                        }

                                    }
                                }
                            }
                            out.close();
                            JSOUP.count += 1;
                            newspaperCategoryDao.insertNews(strName, category, date, title, conn);
                        }
                        break;
                    }

                } else if (category.equals("accident")) {

                } else {
                    if (title.length() > 10000) {
                        title = title.substring(0, 9999);
                    }

                    System.out.println(title);
                    Elements additionalInfo = news.getElementsByClass("fl additional_info");
                    Elements paragraphs = news.select("article").first().select("p");
                    String body = "";
                    String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                    File createFile = new File(newFileStr);
                    if (!createFile.exists()) {
                        createFile.mkdirs();
                    }
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                    out.write("<source> প্রথম আলো"
                            + "" + " </source> ");
                    out.write(System.getProperty("line.separator"));
                    //out.write(news.select("span[itemprop=\"datePublished\"]").text());
                    out.write("<date> " + date + " </date> ");
                    out.write(System.getProperty("line.separator"));
                    out.write("<category> " + category + " </category> ");
                    out.write(System.getProperty("line.separator"));
                    out.write("<title> " + title + " </title> ");
                    out.write(System.getProperty("line.separator"));

                    ArrayList<String> paras = new ArrayList<String>();
                    for (Element p : paragraphs) {
                        StringBuilder sb = new StringBuilder(p.html());
                        String ss = "";
                        for (int i = 0; i < sb.length(); ++i) {
                            if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                                while (sb.charAt(i) != '>') {
                                    ++i;
                                }
                                ++i;
                            }
                            ss += sb.charAt(i);
                        }
                        StringBuilder s3 = new StringBuilder(ss.toString());
                        ss = "";
                        String[] brokenStr = s3.toString().split("</br>|<br>|</p>|<p>");
                        for (int i = 0; i < brokenStr.length; ++i) {
                            String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                            brokenStr[i] = "";
                            for (int j = 0; j < brokenStr2.length; ++j) {
                                StringBuilder stb = new StringBuilder(brokenStr2[j]);
                                for (int k = 0; k < stb.length(); ++k) {
                                    if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                            || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                            || stb.charAt(k) == '&') {
                                        continue;
                                    } else {
                                        brokenStr[i] += stb.charAt(k);
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < brokenStr.length; ++i) {
                            if (brokenStr[i].length() > 2) {
                                //from here 
                                String finalText = "";
                                finalText += "<p> ";
                                for (int j = 0; j < brokenStr[i].length(); ++j) {
                                    if (((int) brokenStr[i].charAt(j) >= 2432 && (int) brokenStr[i].charAt(j) <= 2559) || (brokenStr[i].charAt(j) == ' ') || (brokenStr[i].charAt(j) == ',') || (brokenStr[i].charAt(j) == '।')) {
                                        finalText += brokenStr[i].charAt(j);
                                    }
                                }
                                //to here
                                finalText += " </p> ";
                                System.out.println(finalText);
                                if (finalText.length() >= 18) {
                                    out.write(finalText.toString());
                                    out.write(System.getProperty("line.separator"));
                                }

                            }
                        }
                    }
                    out.close();
                    JSOUP.count += 1;
                    newspaperCategoryDao.insertNews(strName, category, date, title, conn);
                }

            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void kalerkonto(String strName, Element link, String date, String category, Connection conn) {
        System.out.println(JSOUP.count);
        try {

            Document news = Jsoup.connect(link.attr("abs:href")).get();
            String title = news.select("h2").first().text();
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {
                if (title.length() > 10000) {
                    title = title.substring(0, 9999);
                }

                System.out.println(title);

                Elements paragraphs = news.getElementsByClass("some-class-name2");
                String body = "";

                String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                File createFile = new File(newFileStr);
                if (!createFile.exists()) {
                    createFile.mkdirs();
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));

                out.write("কালের কণ্ঠ");
                out.write(System.getProperty("line.separator"));
                out.write(date);
                out.write(System.getProperty("line.separator"));
                out.write(category);
                out.write(System.getProperty("line.separator"));
                out.write(title);
                out.write(System.getProperty("line.separator"));

                for (Element p : paragraphs) {
                    StringBuilder sb = new StringBuilder(p.html());
                    String ss = "";
                    for (int i = 0; i < sb.length(); ++i) {
                        if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                            while (sb.charAt(i) != '>') {
                                ++i;
                            }
                            ++i;
                        }
                        ss += sb.charAt(i);
                    }
                    StringBuilder s3 = new StringBuilder(ss.toString());
                    ss = "";
                    String[] brokenStr = s3.toString().split("</br>|<br>|</p>|<p>");
                    for (int i = 0; i < brokenStr.length; ++i) {
                        String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                        brokenStr[i] = "";
                        for (int j = 0; j < brokenStr2.length; ++j) {
                            StringBuilder stb = new StringBuilder(brokenStr2[j]);
                            for (int k = 0; k < stb.length(); ++k) {
                                if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                        || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                        || stb.charAt(k) == '&') {
                                    continue;
                                } else {
                                    brokenStr[i] += stb.charAt(k);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < brokenStr.length; ++i) {
                        if (brokenStr[i].length() > 2) {
                            System.out.println(brokenStr[i]);
                            out.write(brokenStr[i].toString());
                            out.write(System.getProperty("line.separator"));

                        }
                    }
                }
                JSOUP.count += 1;
                out.close();
                newspaperCategoryDao.insertNews(strName, category, date, title, conn);
            }

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void bdnews24(String strName, Element link, String date, String category, Connection conn) {
        try {
            Document news = null;
            if (category.equals("education") || category.equals("accident") || category.equals("crime")) {
                news = Jsoup.connect(link.select("a[href]").attr("href").toString()).get();
            } else {
                news = Jsoup.connect(link.attr("abs:href")).get();
            }
            String title = news.select("h1").first().text();
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {

                Elements datePrintOnly = news.getElementsByAttributeValueContaining("class",
                        "dateline print-only");
                String newsDate = "";
                for (Element l : datePrintOnly) {
                    newsDate = l.text().substring(l.text().indexOf("20"), l.text().indexOf("20") + 10);
                    break;
                }
                if (dateChecker(newsDate, date) == 0 || dateChecker(newsDate, date) == 1 || dateChecker(newsDate, date) == -1) {
                    System.out.println("this is news date " + newsDate);
                    String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                    File createFile = new File(newFileStr);
                    if (!createFile.exists()) {
                        createFile.mkdirs();
                    }
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                    out.write("বিডিনিউজ24");
                    out.write(System.getProperty("line.separator"));
                    out.write(title);
                    out.write(System.getProperty("line.separator"));
                    out.write(newsDate);
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
                            out.write(finalText);
                            out.write(System.getProperty("line.separator"));
                        }

                    }
                    out.close();
                    JSOUP.count += 1;
                    newspaperCategoryDao.insertNews(strName, category, date, title, conn);
                } else if (dateChecker(newsDate, date) == -1) {
                    NullPointerException e = new NullPointerException();
                    throw e;
                }

            }

        } catch (NullPointerException err) {
            throw err;
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void banglanews24(String strName, Element link, String date, String category, Connection conn) {
        try {
            Document news = Jsoup.connect(link.select("h3").select("a[href]").attr("href").toString()).get();

            String title = news.select("h1").text();
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {
                System.out.println(title);
                String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                File createFile = new File(newFileStr);
                if (!createFile.exists()) {
                    createFile.mkdirs();
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                out.write("বাংলানিউজটোয়েন্টিফোর");
                out.write(System.getProperty("line.separator"));
                out.write(title);
                out.write(System.getProperty("line.separator"));
                Elements paragraphs = news.select("p");
                String body = "";
                ArrayList<String> finalDocs = new ArrayList<String>();
                for (Element p : paragraphs) {
                    if (p.text().startsWith("বাংলাদেশ সময়:")) {
                        //date = p.text().substring(13);
                        break;
                    }
                    finalDocs.add(p.text());
                }
                out.write(date);
                out.write(System.getProperty("line.separator"));
                for (String finalDoc : finalDocs) {
                    if (finalDoc.length() > 6) {
                        out.write(finalDoc);
                        out.write(System.getProperty("line.separator"));
                    }
                }
                System.out.println(body);
                System.out.println();

                out.close();
                JSOUP.count += 1;
                newspaperCategoryDao.insertNews(strName, category, date, title, conn);
            }

        } catch (Exception err) {
        }
    }

    public void mzamin(String strName, Element link, String date, String category, Connection conn) throws IOException {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.select("h1").text();

            System.out.println(title);
            newspaperCategoryDao = new NewspaperCategoryDao();
            if (newspaperCategoryDao.titlePresent(strName, category, title, conn) != 1) {
                Elements paragraphs = news.getElementsByClass("details-text");
                String body = "";
                String newFileStr = "F:\\Corpus\\" + category + "\\" + strName + "\\" + date + "\\";
                File createFile = new File(newFileStr);
                if (!createFile.exists()) {
                    createFile.mkdirs();
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileStr + "new" + +JSOUP.count + ".txt"), "UTF-8"));
                out.write("মানবজমিন");
                out.write(System.getProperty("line.separator"));
                out.write(title);
                out.write(System.getProperty("line.separator"));
                out.write(date);
                out.write(System.getProperty("line.separator"));
                for (Element p : paragraphs) {

                    StringBuilder sb = new StringBuilder(p.html());
                    String ss = "";
                    for (int i = 0; i < sb.length(); ++i) {
                        if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                            while (sb.charAt(i) != '>') {
                                ++i;
                            }
                            ++i;
                        }
                        ss += sb.charAt(i);
                    }

                    String ss2 = "";
                    for (int i = 0; i < ss.length(); ++i) {
                        if (ss.charAt(i) == '<' && ss.charAt(i + 1) == 'n' && ss.charAt(i + 2) == 'o') {
                            break;
                        }
                        ss2 += ss.charAt(i);
                    }

                    StringBuilder s3 = new StringBuilder(ss2.toString());
                    ss = "";
                    String[] brokenStr = s3.toString().split("<noscript [^<>]*>.*</noscript>|<script [^<>]*>.*</script>|</br>|<br>|</p>|<p>");
                    for (int i = 0; i < brokenStr.length; ++i) {
                        String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                        brokenStr[i] = "";
                        for (int j = 0; j < brokenStr2.length; ++j) {
                            StringBuilder stb = new StringBuilder(brokenStr2[j]);
                            for (int k = 0; k < stb.length(); ++k) {
                                if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                        || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                        || stb.charAt(k) == '&') {
                                    continue;
                                } else {
                                    brokenStr[i] += stb.charAt(k);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < brokenStr.length - 1; ++i) {
//                    if (brokenStr[i].length() > 2) {
//                        System.out.println(brokenStr[i]);
//                        out.write(brokenStr[i].toString());
//                        out.write(System.getProperty("line.separator"));
//
//                    }
                        if (brokenStr[i].length() > 2) {
                            String finalText = "";
                            for (int j = 0; j < brokenStr[i].length(); ++j) {

                                //if (((int)tempText.charAt(j)>='a' && tempText.charAt(j)<='z') || (tempText.charAt(j)>='A' && tempText.charAt(j)<='Z') || (tempText.charAt(j)>='0' && tempText.charAt(j)<='9')){
                                if (((int) brokenStr[i].charAt(j) >= 2432 && (int) brokenStr[i].charAt(j) <= 2559) || (brokenStr[i].charAt(j) == ' ') || (brokenStr[i].charAt(j) == ',') || (brokenStr[i].charAt(j) == '।')) {
                                    finalText += brokenStr[i].charAt(j);
                                }
                            }
                            out.write(finalText);
                            out.write(System.getProperty("line.separator"));
                        }
                    }
                }
                out.close();
                JSOUP.count += 1;
                newspaperCategoryDao.insertNews(strName, category, date, title, conn);
            }
            //}
        } catch (Exception err) {
        }
    }

    public void inqilab(Element link) throws IOException {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = link.attr("abs:href");
            int lastIndex = title.lastIndexOf("/");
            title = title.substring(lastIndex + 1);

            String date = news.getElementsByClass("date").text();
            System.out.println("title " + title);
            System.out.println("date " + date);
            Element p = news.select("p").first();
            StringBuilder sb = new StringBuilder(p.html());
            String ss = "";
            for (int i = 0; i < sb.length(); ++i) {
                if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                    while (sb.charAt(i) != '>') {
                        ++i;
                    }
                    ++i;
                }
                ss += sb.charAt(i);
            }
            StringBuilder s3 = new StringBuilder(ss.toString());
            ss = "";
            String[] brokenStr = s3.toString().split("<style [^<>]+>[^>]+</style>|</br>|<br>|</p>|<p>");
            for (int i = 0; i < brokenStr.length; ++i) {
                String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                brokenStr[i] = "";
                for (int j = 0; j < brokenStr2.length; ++j) {
                    StringBuilder stb = new StringBuilder(brokenStr2[j]);
                    for (int k = 0; k < stb.length(); ++k) {
                        if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                || stb.charAt(k) == '&') {
                            continue;
                        } else {
                            brokenStr[i] += stb.charAt(k);
                        }
                    }
                }
            }
            if (brokenStr.length > 0) {
                BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
                out.write("দৈনিক ইনকিলাব");
                out.write(System.getProperty("line.separator"));
                out.write(title);
                out.write(System.getProperty("line.separator"));
                out.write(date);
                out.write(System.getProperty("line.separator"));
                for (int i = 0; i < brokenStr.length; ++i) {
                    if (brokenStr[i].length() > 12) {
                        System.out.println(brokenStr[i]);
                        out.write(brokenStr[i].toString());
                        out.write(System.getProperty("line.separator"));
                    }
                }
                out.close();
            }

            JSOUP.count += 1;

        } catch (Exception err) {
        }
    }

    public int dateChecker(String dateOfLink, String dateOfQuery) {

        try {
            String[] splittedDateOfLink = dateOfLink.split("-");
            String[] splittedDateOfQuery = dateOfQuery.split("-");
            System.out.println("Date OF Link       Date OF Query");
            System.out.println(splittedDateOfLink[0] + " " + splittedDateOfLink[1] + " " + splittedDateOfLink[2]);
            System.out.println(splittedDateOfQuery[0] + " " + splittedDateOfQuery[1] + " " + splittedDateOfQuery[2]);
            if (Integer.parseInt(splittedDateOfLink[0]) < Integer.parseInt(splittedDateOfQuery[0])) {
                return -1;
            } else if ((Integer.parseInt(splittedDateOfLink[0]) == Integer.parseInt(splittedDateOfQuery[0]))
                    && (Integer.parseInt(splittedDateOfLink[1]) < Integer.parseInt(splittedDateOfQuery[1]))) {
                return -1;
            } else if ((Integer.parseInt(splittedDateOfLink[0]) == Integer.parseInt(splittedDateOfQuery[0]))
                    && (Integer.parseInt(splittedDateOfLink[1]) == Integer.parseInt(splittedDateOfQuery[1]))
                    && Integer.parseInt(splittedDateOfLink[2]) < Integer.parseInt(splittedDateOfQuery[2])) {
                return -1;
            } else if ((Integer.parseInt(splittedDateOfLink[0]) == Integer.parseInt(splittedDateOfQuery[0]))
                    && (Integer.parseInt(splittedDateOfLink[1]) == Integer.parseInt(splittedDateOfQuery[1]))
                    && (Integer.parseInt(splittedDateOfLink[2]) == Integer.parseInt(splittedDateOfQuery[2]))) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }

    }

}
