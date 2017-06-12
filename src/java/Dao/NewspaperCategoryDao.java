
package Dao;

import java.sql.Connection;
import java.sql.*;

public class NewspaperCategoryDao {
    public int titlePresent(String name,String category,String title,Connection conn){
        int titlePresent = 0;
        int count = 0;
        PreparedStatement ps;
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from newspaper_category where newspaper_name=? and category_name=? and title=?");
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                titlePresent = 1;
            }
            stmt.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
        return titlePresent;
    }
    
    public void insertNews(String strName, String category, String date, String title, Connection conn){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("insert into newspaper_category(newspaper_name,category_name,date,title) values(?,?,?,?)");
            stmt.setString(1, strName);
            stmt.setString(2, category);
            stmt.setString(3, date);
            stmt.setString(4, title);
            stmt.execute();
            stmt.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
    
}