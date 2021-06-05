package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME='MALLIK'-- AND PWD='MALLIK@123'
public class LoginAppTryWthResourse {

	public static void main(String[] args) {
		ResultSet rs=null;
		try(
				Scanner scn=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
				Statement st=con.createStatement();
				) {			
			String user=null;
			String pass=null;
			if(scn!=null) {
				System.out.print("Enter Login UserName: ");
				user="'"+scn.next()+"'";
				
				System.out.print("Enter Login Password: ");
				pass="'"+scn.next()+"'";
				
			}//if

			//Preparing SQL Query
			//SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME='MALLIK'-- AND PWD='MALLIK@123'
			String query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			if(st!=null)
				rs=st.executeQuery(query);
			if(rs!=null) {
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
		
	}//main

}//class
