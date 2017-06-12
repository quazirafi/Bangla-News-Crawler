package Parsing;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Downloader {

    public int readWrite(int downloadCount, String newspaperName, String date, String category) throws FileNotFoundException, IOException {
        
        String filesPath = "F:\\Corpus\\" + category + "\\" + newspaperName + "\\" + date + "\\";
        String rootPath = "F:\\Download\\Corpus" + downloadCount + "\\" + category + "\\" + newspaperName + "\\" + date + "\\";
        File directoryOfNewFile = new File(rootPath);
        if (!directoryOfNewFile.exists()) {
            directoryOfNewFile.mkdirs();
        }
        getAllFiles(new File(filesPath), rootPath);
        ZipperClass2 zipperClass = new ZipperClass2();
        try{
            zipperClass.goZip("F:\\Download\\Corpus" + downloadCount , downloadCount);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return downloadCount;
    }

    public static void getAllFiles(File dir, String rootPath) {
        //List<File> fileList = new ArrayList<File>();
        BufferedWriter out = null;
        try {

            File[] files = dir.listFiles();
            for (File file : files) {
                //fileList.add(file);
                if (!file.isDirectory()) {
                    //
                    BufferedReader fr = null;
                    try {
                        fr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rootPath + file.getName()), "UTF-8"));
                        String count;
                        while ((count = fr.readLine()) != null) {
                            out.write(count);
                            out.write(System.getProperty("line.separator"));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        out.close();
                    }
                    //
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return fileList;
    }
    
    
}
