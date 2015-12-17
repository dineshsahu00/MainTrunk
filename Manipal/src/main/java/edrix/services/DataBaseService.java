package edrix.services;
import java.sql.*;
public class DataBaseService {

	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/edrix";

	   //  Database credentials
	   static final String USER = "admin";
	   static final String PASS = "admin";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT USER_NUM, USER_ID, FIRST_NAME, LAST_NAME, ROLE, PASSWORD FROM edrix_users";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int USER_NUM  = rs.getInt("USER_NUM");
	         String USER_ID = rs.getString("USER_ID");
	         String FIRST_NAME = rs.getString("FIRST_NAME");
	         String LAST_NAME = rs.getString("LAST_NAME");
	         String ROLE = rs.getString("ROLE");
	         String PASSWORD = rs.getString("PASSWORD");

	         //Display values
	         System.out.print(" USER_NUM: " + USER_NUM);
	         System.out.print(" USER_ID: " + USER_ID);
	         System.out.print(" FIRST_NAME: " + FIRST_NAME);
	         System.out.print(" LAST_NAME: " + LAST_NAME);
	         System.out.print(" ROLE: " + ROLE);
	         System.out.print(" PASSWORD: " + PASSWORD);
	         System.out.println();
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}
}
