package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEmpMaxSal {
//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//Establishing the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Create JDBC Statement Object
			if(con!=null) {
				st=con.createStatement();
			}//if
			
			//Preparing SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			
			if(st!=null) {
				rs=st.executeQuery(query);
			}//if
			
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
 				}//while
				if(flag==false)
					System.out.println("No Records found:");
			}//if

		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
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
