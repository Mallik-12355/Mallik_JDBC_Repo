package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)
public class SelectEmpMinSal {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing SQL Query
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			
			//Sending and Executing the Query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Processing ResultSet
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				if(flag==false)
					System.out.println("No Records Found:");
			}
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
			}
		}
	}
}
