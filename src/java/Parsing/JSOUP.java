package Parsing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Response;

/**
 *
 * JSoup HTML parser
 *
 * @author BUDDHIMA
 */
public class JSOUP {

    static int count = 0;

    /**
     * @param args the command line arguments
     */
    public void goParse(String strName, String date, String category, Connection conn) throws IOException {
        CheckData();

        if (strName.equals("prothomAlo")) {
            int i = 1;
            //from here 
            while (i < 20) {
                try {
                    for (; i <= 20;) {
                        //
                        Document doc = Jsoup.connect("http://www.prothom-alo.com/archive/" + date + "?page=" + i)
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                                .referrer("http://www.google.com")
                                .get();
                        //

                        Elements links = doc.select("a[href]");
                        for (Element link : links) {
                            if (link.attr("abs:href").toString().contains(category + "/article")) {
                                System.out.println(link.attr("abs:href").toString());
                                new ReadArticle(strName, link, date, category, conn);
                            }
                        }
                        i += 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //till here
        } else if (strName.equals("ittefaq")) {
            String categoryOfThisPaper = "";
            if (category.equals("sports")) {
                categoryOfThisPaper = category;
            } else if (category.equals("politics")) {
                categoryOfThisPaper = category;
            } else if (category.equals("international")) {
                categoryOfThisPaper = "world-news";
            } else if (category.equals("entertainment")) {
                categoryOfThisPaper = category;
            } else if (category.equals("economy")) {
                categoryOfThisPaper = "trade";
            } else if (category.equals("technology")) {
                categoryOfThisPaper = "science-&-tech";
            } else if (category.equals("art-and-literature")) {
                categoryOfThisPaper = "culture";
            } else if (category.equals("life-style")) {
                categoryOfThisPaper = category;
            } else if (category.equals("education")) {
                categoryOfThisPaper = category;
            }
            String[] dateFragments = date.split("-");
            String convertedDate = "";
            for (int i = 0; i < dateFragments.length; i++) {
                convertedDate += dateFragments[i] + "/";
            }
            System.out.println("converted date " + convertedDate);
            int stopFlag = 0;
            for (int i = 1; i <= 20; ++i) {
                Document doc = Jsoup.connect("http://www.ittefaq.com.bd/" + categoryOfThisPaper + "/" + i)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .referrer("http://www.google.com")
                        .get();
                Elements links = doc.getElementsByAttributeValue("class", "headline");
                for (Element div : links) {
                    String link = div.select("a").attr("href").toString();
                    if (link.contains(categoryOfThisPaper + "/" + convertedDate)) {
                        String dateOfLink = link.substring(link.indexOf("20"), link.indexOf("20") + 10);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("link -- > " + link);
                            new ReadArticle(strName, div, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            stopFlag = 1;
                            System.out.println("date of break --> " + dateOfLink);
                            break;
                        }
                    }
                }
                if (stopFlag == 1) {
                    break;
                }
            }
        } else if (strName.equals("manabZamin")) {
            Document doc = null;
            String categoryOfThisPaper = null;
            if (date.startsWith("2012") || date.startsWith("2013")) {
                doc = Jsoup.connect("http://www.mzamin.com/archive-result.php?data=1370714400&date=" + date).get();
            } else if (date.startsWith("2014") || date.startsWith("2015")) {
                doc = Jsoup.connect("http://www.mzamin.com/archive-result.php?data=1399226400&date=" + date).get();
            } else if (date.startsWith("2016") || date.startsWith("2017")) {
                doc = Jsoup.connect("http://www.mzamin.com/archive-result.php?data=1485972000&date=" + date).get();
            }
            if (category.equals("sports")) {
                categoryOfThisPaper = "খেলা";
            } else if (category.equals("entertainment")) {
                categoryOfThisPaper = "বিনোদন";
            } else if (category.equals("international")) {
                categoryOfThisPaper = "বিশ্বজমিন";
            }

            Elements divs = doc.getElementsByAttributeValue("class", "details-list");
            int count = 0;
            for (Element l : divs) {
                String str = l.getElementsByAttributeValue("style", "color:#f00;").text();
                if (str.equals(categoryOfThisPaper)) {
                    new ReadArticle(strName, l.select("a").first(), date, category, conn);
                    ++count;
                }
            }
            System.out.println("cats size " + divs.size());
            System.out.println("news number " + count);
        } else if (strName.equals("banglanews24")) {
            String categoryOfThisPaper = null;
            Document doc = null;
            if (category.equals("sports")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 3063; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%96%E0%A7%87%E0%A6%B2%E0%A6%BE/5?page=" + i).timeout(0).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("economy")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 2172; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%85%E0%A6%B0%E0%A7%8D%E0%A6%A5%E0%A6%A8%E0%A7%80%E0%A6%A4%E0%A6%BF/3?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("politics")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 4242; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%B0%E0%A6%BE%E0%A6%9C%E0%A6%A8%E0%A7%80%E0%A6%A4%E0%A6%BF/2?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("international")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 2000; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%86%E0%A6%A8%E0%A7%8D%E0%A6%A4%E0%A6%B0%E0%A7%8D%E0%A6%9C%E0%A6%BE%E0%A6%A4%E0%A6%BF%E0%A6%95/4?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("life-style")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 402; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%B2%E0%A6%BE%E0%A6%87%E0%A6%AB%E0%A6%B8%E0%A7%8D%E0%A6%9F%E0%A6%BE%E0%A6%87%E0%A6%B2/12?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("entertainment")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 1599; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%AC%E0%A6%BF%E0%A6%A8%E0%A7%8B%E0%A6%A6%E0%A6%A8/6?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("accident")) {
                categoryOfThisPaper = category;
            } else if (category.equals("technology")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 645; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%A4%E0%A6%A5%E0%A7%8D%E0%A6%AF%E0%A6%AA%E0%A7%8D%E0%A6%B0%E0%A6%AF%E0%A7%81%E0%A6%95%E0%A7%8D%E0%A6%A4%E0%A6%BF/7?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("crime")) {
                categoryOfThisPaper = category;
            } else if (category.equals("education")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 778; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%B6%E0%A6%BF%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE/20?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("art-and-literature")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 418; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%B6%E0%A6%BF%E0%A6%B2%E0%A7%8D%E0%A6%AA-%E0%A6%B8%E0%A6%BE%E0%A6%B9%E0%A6%BF%E0%A6%A4%E0%A7%8D%E0%A6%AF/11?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            } else if (category.equals("opinion")) {
                String[] dateFragments = date.split("-");
                String convertedDate = "";
                for (int i = 0; i < dateFragments.length; i++) {
                    convertedDate += dateFragments[i] + "/";
                }
                System.out.println("converted date " + convertedDate);
                int endNow = 0;
                for (int i = 0; i < 176; ++i) {

                    doc = Jsoup.connect("http://www.banglanews24.com/category/%E0%A6%AE%E0%A7%81%E0%A6%95%E0%A7%8D%E0%A6%A4%E0%A6%AE%E0%A6%A4/16?page=" + i).get();
                    Elements paras = doc.getElementsByAttributeValue("class", "col-sm-8");
                    System.out.println("page " + i + " para size " + paras.size());
                    for (Element link : paras) {
                        String dateOfLink = link.select("span").text().toString().substring(0, 11);
                        if (dateChecker(dateOfLink, date) == 0) {
                            System.out.println("Crawling date " + dateOfLink);
                            new ReadArticle(strName, link, date, category, conn);
                        } else if (dateChecker(dateOfLink, date) == -1) {
                            System.out.println("Ending at date " + dateOfLink);
                            endNow = 1;
                            break;
                        }
                    }
                    if (endNow == 1) {
                        break;
                    }
                }
            }

        } else if (strName.equals("bdnews24")) {
            String categoryOfThisPaper = null;
            if (category.equals("sports")) {
                categoryOfThisPaper = "sport";
            } else if (category.equals("economy")) {
                categoryOfThisPaper = "business";
            } else if (category.equals("politics")) {
                categoryOfThisPaper = category;
            } else if (category.equals("international")) {
                categoryOfThisPaper = "world";
            } else if (category.equals("life-style")) {
                categoryOfThisPaper = "lifestyle";
            } else if (category.equals("entertainment")) {
                categoryOfThisPaper = "glitz";
            } else if (category.equals("accident")) {
                categoryOfThisPaper = category;
            } else if (category.equals("technology")) {
                categoryOfThisPaper = "tech";
            } else if (category.equals("crime")) {
                categoryOfThisPaper = category;
            } else if (category.equals("education")) {
                categoryOfThisPaper = category;
            }

            String[] dateFragments = date.split("-");
            String convertedDate = "";
            for (int i = 0; i < dateFragments.length; i++) {
                convertedDate += dateFragments[i] + "/";
            }
            System.out.println("converted date " + convertedDate);
            for (int j = 1; j <= 25; ++j) {
                Document doc = Jsoup.connect("http://bangla.bdnews24.com/search/simple.do;jsessionid=25AC34AE77AEB4F60D6179465D5DD017.pre13?destinationSectionId=80&publicationName=bangla&sortString=publishdate&sortOrder=desc&sectionId=68&articleTypes=news-bn+news-district+news-kidz&pageNumber=" + j + "&pageLength=20&searchString=" + categoryOfThisPaper).get();

                Elements links = doc.select("a[href]");
                Elements selectedLinks = null;

                if (category.equals("education") || category.equals("accident") || category.equals("crime")) {
                    Elements spanClass = doc.getElementsByAttributeValue("class", "resultTitle");
                    for (Element link : spanClass) {
                        if (link.select("a[href]").attr("href").toString().contains("bangladesh/article")) {
                            new ReadArticle(strName, link, date, category, conn);
                        }
                    }
                } else {
                    for (Element link : links) {
                        if ((link.attr("abs:href").toString().contains("/" + categoryOfThisPaper + "/article"))) {
                            new ReadArticle(strName, link, date, category, conn);
                        }
                    }
                }
            }
        } else if (strName.equals("kalerKantho")) {
            int flagPage = 0;
            int dontNeedToProceedMore = 0;
            String[] dateFragments = date.split("-");
            String convertedDate = "";
            for (int i = 0; i < dateFragments.length; i++) {
                convertedDate += dateFragments[i] + "/";
            }
            System.out.println("converted date " + convertedDate);
            for (int j = 0; j < 2500; ++j) {
                Document doc = null;
                System.out.println("this is j = " + j * 18);
                if (category.equals("sports")) {
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/" + category.substring(0, category.length() - 1) + "/" + (18 * j)).get();
                } else if (category.equals("economy")) {
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/business/" + 18 * j).get();
                } else if (category.equals("politics")) {
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/Politics/" + 18 * j).get();
                } else if (category.equals("international")) {
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/world/" + 18 * j).get();
                } else if (category.equals("art-and-literature")) {
                    System.out.println("inside art and literature");
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/sahitya/" + 18 * j).get();
                } else if (category.equals("life-style")) {
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/lifestyle/" + 18 * j).get();
                } else if (category.equals("entertainment")) {
                    System.out.println("inside entertainment");
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/entertainment/" + 18 * j).get();
                } else if (category.equals("opinion")) {
                    System.out.println("inside opinion");
                    doc = Jsoup.connect("http://www.kalerkantho.com/online/nagorik-montobbo/" + 18 * j).get();
                }
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    if (category.equals("sports")) {
                        if (link.attr("abs:href").toString().contains(category.substring(0, category.length() - 1) + "/" + "20")) {
                            System.out.println(link.attr("abs:href"));
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("economy")) {
                        if (link.attr("abs:href").toString().contains("business" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("politics")) {
                        if (link.attr("abs:href").toString().contains("Politics" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("international")) {
                        if (link.attr("abs:href").toString().contains("world" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("art-and-literature")) {
                        if (link.attr("abs:href").toString().contains("sahitya" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("life-style")) {
                        if (link.attr("abs:href").toString().contains("lifestyle" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                            //flagPage = 1;
                        }
                    } else if (category.equals("entertainment")) {
                        if (link.attr("abs:href").toString().contains("entertainment" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                        }
                    } else if (category.equals("opinion")) {
                        if (link.attr("abs:href").toString().contains("nagorik-montobbo" + "/" + "20")) {
                            if (dateChecker(link.attr("abs:href").toString(), date) == -1) {
                                flagPage = 1;
                                break;
                            } else if (dateChecker(link.attr("abs:href").toString(), date) == 0) {
                                System.out.println(link.attr("abs:href"));
                                new ReadArticle(strName, link, date, category, conn);
                            }
                        }
                    }
                }
                if (flagPage == 1) {
                    //++dontNeedToProceedMore;
                    break;
                }
//                if (dontNeedToProceedMore == 2) {
//                    break;
//                }
//                flagPage = 0;
            }
        }

        //Document doc = Jsoup.connect("http://www.kalerkantho.com/").get();
        //Elements links = doc.getElementsByClass("news_list").select("a[href]");  // kaler-konto    http://www.kalerkantho.com/
        //Document doc = Jsoup.connect("http://bangla.bdnews24.com/").get();
        //Elements links = doc.getElementsByClass("content").select("a[href]");  // bdnews24    http://bangla.bdnews24.com/
        //Document doc = Jsoup.connect("http://www.banglanews24.com/").get();
        //Elements links = doc.select("a[href]");  // banglanews24    http://www.banglanews24.com/
        //Document doc = Jsoup.connect("http://www.mzamin.com/").get();
        //Elements links = doc.getElementsByClass("col-sm-5").select("a[href]");//this is not ours above is ours
        //Elements links = doc.select("a[href]");//manobzomin http://www.mzamin.com/
        //Document doc = Jsoup.connect("http://www.dailyinqilab.com/").get();
        ///Elements links = doc.getElementsByClass("overview").select("a[href]");  // inquilab    http://www.dailyinqilab.com/
        //Elements links = doc.select("a[href]");
        //ajk inq,mzamin r kalerkontho hoi nai 10.2.2016
        //ekhna theke comment
        //if banglanews24
//        for (Element link : links) {
//            if (!set.contains(link.attr("abs:href").toString()) && link.attr("abs:href").toString().endsWith("details")) {
//                System.out.println(link.attr("abs:href"));
//                new ReadArticle(link);
//            }
//            set.add(link.attr("abs:href").toString());
//        }
        //if mzamin
//        for (Element link : links) {
//            if (!set.contains(link.attr("abs:href").toString()) && link.attr("abs:href").toString().contains("article.php?mzamin=")) {
//                System.out.println(link.attr("abs:href"));
//                new ReadArticle(link);
//            }
//            set.add(link.attr("abs:href").toString());
//        }
//        for (Element link : links) {
//            if (!set.contains(link.attr("abs:href").toString()) && link.attr("abs:href").toString().contains("https://www.dailyinqilab.com/article/")) {
//                System.out.println(link.attr("abs:href"));
//                new ReadArticle(link);
//            }
//            set.add(link.attr("abs:href").toString());
//        }
        WriteData();
    }

    private static void CheckData() throws FileNotFoundException, IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt", true));
        out.close();

        FileInputStream fstream = new FileInputStream("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        boolean read = false;
        while ((line = br.readLine()) != null) {
            read = true;
            count = Integer.parseInt(line) + 1;
        }
    }

    private static void WriteData() throws FileNotFoundException, IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt", false));
        out.write((count) + "");
        out.close();
    }

    public int dateChecker(String dateOfLink, String dateOfQuery) {

        try {
            System.out.println("error here " + dateOfLink);
            String[] splittedDateOfLink = null;
            if (dateOfLink.contains("২০")) {
                System.out.println("inside 20020");
                splittedDateOfLink = dateOfLink.split("-");
                splittedDateOfLink[2] = splittedDateOfLink[2].substring(0, splittedDateOfLink[2].length() - 1);
            } else {
                splittedDateOfLink = dateOfLink.substring(dateOfLink.indexOf("20"), dateOfLink.indexOf("20") + 10).split("/");
            }
            String[] splittedDateOfQuery = dateOfQuery.split("-");

            System.out.println("date of Link      date of Query");
            System.out.println(Integer.parseInt(splittedDateOfLink[0]) + " " + Integer.parseInt(splittedDateOfLink[1]) + " " + Integer.parseInt(splittedDateOfLink[2]));
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
            e.printStackTrace();
            return 1;
        }

    }
}
