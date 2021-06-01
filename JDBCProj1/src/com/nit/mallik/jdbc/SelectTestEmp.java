package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestEmp {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			scn=new Scanner(System.in);
			String chars=null;			
			if(scn!=null) { 
				System.out.print("Enter Initial Characters: ");
				chars="'"+scn.next().toUpperCase()+"%'";
			}
			
			//Connection Establishment
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Statement Creation
			if(con!=null) {
				st=con.createStatement();
			}
			
			//Preparing Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE A%
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+chars;
			System.out.println(query);
			
			//Sending the Query			
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			boolean flag=false;
			while(rs.next()) {
				flag=true;
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));				
			}
			
			if(flag==false)
				System.out.println("No Records Found:");
				
		}
		
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<=999)
				System.out.println("Invalid Col Names or Syntax Error: ");
			se.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
