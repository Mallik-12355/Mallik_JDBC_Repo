package com.nit.mallik.ps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME='MALLIK'AND PWD='MALLIK@123'
public class PSLoginAppSlvdSQLInjctnPrblm {

	private static final String LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			scn=new Scanner(System.in);
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null)
				ps=con.prepareStatement(LOGIN_QUERY);
			
			if(ps!=null&&scn!=null) {
				System.out.print("Enter Login UserName: ");
				String user=scn.next();
				System.out.print("Enter Login Password: ");
				String pass=scn.next();
				
				ps.setString(1, user);
				ps.setString(2, pass);
				
				rs=ps.executeQuery();
				
				if(rs!=null)
					rs.next();
				
				int count=rs.getInt(1);
				
				if(count==0)
					System.out.println("Invalid Credentials: ");
				else
					System.out.println("Valid Credentials: ");
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
				if(ps!=null)
					ps.close();
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
