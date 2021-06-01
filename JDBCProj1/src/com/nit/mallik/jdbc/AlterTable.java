package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//ALTER TABLE TEMP_STUDENT ADD(MARKS NUMBER(10))
public class AlterTable {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			String query="ALTER TABLE TEMP_STUDENT ADD(MARKS NUMBER(10))";
			
			if(con!=null)
				st=con.createStatement();
			
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("Table is not Altered: ");
			else
				System.out.println("Table is Altered Successfully");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
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
