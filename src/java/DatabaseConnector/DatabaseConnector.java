package DatabaseConnector;

import java.sql.*;

public class DatabaseConnector {

    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/corpus";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1234";

    public static Connection setConnectionWithMySql() {
        Connection conn = null;
        Statement stmt = null;
       try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
       }
       catch(Exception e){
           e.printStackTrace();
       }
        
        return conn;
    }//end try
    
    

}
