package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//select * from dept where deptNo=40
public class SelectDeptWthDeptNo {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			scn=new Scanner(System.in);
			int deptNo=0;
			if(scn!=null) {
				System.out.print("Enter the DeptNo: ");
				deptNo=scn.nextInt();
			}//if
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null) {
				st=con.createStatement();
			}//if
			
			//Preparing SQL Query
			
			String query="SELECT DEPTNO, DNAME, LOC FROM DEPT WHERE DEPTNO="+deptNo;
			
			if(st!=null) {
				rs=st.executeQuery(query);
			}//if
			
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				else
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
			
			try {
				if(scn!=null)
					scn.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}//catch
			
		}//finally
		
	}//main

}//class
