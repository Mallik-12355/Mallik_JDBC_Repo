package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7925,'STEPHEN','MANAGER',4500)
public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			scn=new Scanner(System.in);
			int no=0;
			String query=null;
			if(scn!=null) {
				System.out.print("Enter Query (Select Query or Non Select Query): ");
				query=scn.nextLine();
			}//if
			
			//Establishing Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Create JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			if(st!=null) {
				boolean flag=st.execute(query);
				System.out.println(flag);
				if(flag==true) {
					System.out.println("SQL Select Query is Executed: ");
					rs=st.getResultSet();
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
						}//while
					}//if
				}//if
				else {
					System.out.println("Non Select Query is Executed: ");
					int count=st.getUpdateCount();
					System.out.println("Number of Records that are effected are: "+count);
				}//else
			}//if
		}//try
		
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("Duplicat Data is not Allowed in Primary Key Column: ");
			if(se.getErrorCode()==12899)
				System.out.println("Don't Enter Column Value More than the Size of the Column: ");
			if(se.getErrorCode()==984)
				System.out.println("Don't Enter String Value. Please Enter the Average in Number Format: ");
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
