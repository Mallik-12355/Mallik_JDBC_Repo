package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SELECT EMPNO,ENAME,JOB,SAL FROM (SELECT ENAME,SAL,DENSE_RANK()OVER(ORDER BY SAL DESC)R FROM EMP)WHERE R=&N
public class SelectEmpNthHighestSal {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			//Establishing the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing SQL Query
			// SELECT EMPNO,ENAME,JOB,SAL FROM (SELECT ENAME,SAL,DENSE_RANK()OVER(ORDER BY SAL DESC)R FROM EMP)WHERE R=&N			
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM (SELECT ENAME,SAL,DENSE_RANK()OVER(ORDER BY SAL DESC)R FROM EMP)WHERE R=&N";
			
			//Send and Execute Query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Processing ResultSet Object
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				if(flag==false)
					System.out.println("No Records Found:");
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
