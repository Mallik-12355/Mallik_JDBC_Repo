package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//select count(*) from student
public class SelectStudentCount {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating JDBC Statement Object
			if(con!=null) {
				st=con.createStatement();
			}//if
			
			//Preparing SQL Query
			//SELECT COUNT(*) FROM STUDENT
			String query="SELECT COUNT(*) FROM STUDENT";
			if(st!=null) {
				rs=st.executeQuery(query);
			}//if
			
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				System.out.print("The Record Count is: "+count);
			}//if
		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
		}//finally

	}//main

}//class
