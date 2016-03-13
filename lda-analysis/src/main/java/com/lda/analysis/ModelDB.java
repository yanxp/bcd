package com.lda.analysis;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ModelDB {
	
	public void Save2DB (Map<String, Double>[] result) {
		String driver = "com.mysql.jdbc.Driver";
	    // URL指向要访问的数据库名scutcs          
		String url = "jdbc:mysql://127.0.0.1:3306/welarn";
	    // MySQL配置时的用户名         
		String user = "root";              // MySQL配置时的密码     
		String password = "2418";
		 java.sql.PreparedStatement pst = null;  
		 String  vocabulary ="";
	    try {             // 加载驱动程序         
	    	Class.forName(driver);
	     // 连续数据库         
	    	java.sql.Connection conn = DriverManager.getConnection(url+"?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=utf-8");
	     if(!conn.isClosed())       
	    	 System.out.println("Succeeded connecting to the Database!");
	    // statement用来执行SQL语句        
	     java.sql.Statement statement = conn.createStatement();
	     // 要执行的SQL语句         
	    	   for (Map<String, Double> topicMap : result)
	           {
	         	  
	    		   for (Map.Entry<String, Double> entry : topicMap.entrySet())
	    	          {
	    			    vocabulary+=entry.getKey()+" ";
	    	          }
	           }
	    	       String sql = "select * from citys";
	    		   String insertsql= "insert into citys(id,cityname,vocabulary) values(?,?,?) ";  
	    		   pst = conn.prepareStatement(insertsql,Statement.RETURN_GENERATED_KEYS);  
	    		   pst.setInt(1,1 );  
	    		   pst.setString(2,"菏泽"); 
	    		   pst.setString(3, vocabulary);
	    		   pst.executeUpdate();  
	     conn.close();
	    } catch(ClassNotFoundException e) {
	     System.out.println("Sorry,can`t find the Driver!");             e.printStackTrace();
	   } catch(SQLException e) {
	     e.printStackTrace();
	    } catch(Exception e) {
	   e.printStackTrace();
	  }   
	}
	public void QueryfromDB() {
		String driver = "com.mysql.jdbc.Driver";
	    // URL指向要访问的数据库名scutcs          
		 String url = "jdbc:mysql://127.0.0.1:3306/welarn";
	    // MySQL配置时的用户名         
		  String user = "root";              // MySQL配置时的密码     
		String password = "2418";
		 java.sql.PreparedStatement pst = null;  
		 String  vocabulary ="";
	    try {             // 加载驱动程序         
	    	Class.forName(driver);
	     // 连续数据库         
	    	java.sql.Connection conn = DriverManager.getConnection(url+"?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=utf-8");
	     if(!conn.isClosed())       
	    	 System.out.println("Succeeded connecting to the Database!");
	    // statement用来执行SQL语句        
	     java.sql.Statement statement = conn.createStatement();
	     // 要执行的SQL语句         
	     ResultSet rs=statement.executeQuery("select * from citys");
	     while (rs.next()) {
	    	    if (rs.getInt("id")==1) {
		    	      String cityname=rs.getString("cityname");
		             String vocabu=rs.getString("vocabulary");
		             System.out.println(cityname);
		             System.out.println(vocabu);
			}
		}
	     conn.close();
	    } catch(ClassNotFoundException e) {
	     System.out.println("Sorry,can`t find the Driver!"); 
	     e.printStackTrace();
	   } catch(SQLException e) {
	     e.printStackTrace();
	    } catch(Exception e) {
	   e.printStackTrace();
	  } 
	}

}
	

