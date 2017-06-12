/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

/**
 *
 * @author DANA
 */
public class Starter extends Thread{
    
//    Thread t;
    String date,category;
    
    public Starter(String date,String category){
//        t = new Thread();
        this.date = date;
        this.category = category;
        start();
    }
    
    public void run(){
        try{
            JSOUP jsoup = new JSOUP();
        //jsoup.goParse(date, category);
        System.out.println("Exited Successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
