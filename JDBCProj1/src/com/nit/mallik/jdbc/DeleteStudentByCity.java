package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//DELETE STUDENT WHERE SADD='VIZAG'
public class DeleteStudentByCity {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			String city=null;
			if(scn!=null) {
				System.out.print("Enter The City Name: ");
				city="'"+scn.nextLine().toUpperCase()+"'";
			}//if
			
			//Establishing The Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Create JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing Query
			//DELETE STUDENT WHERE SADD='VIZAG'
			String query="DELETE STUDENT WHERE SADD="+city;
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No Records Found:");
			else
				System.out.println("Number of records that are effected: "+count);
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>900&&se.getErrorCode()<=999)
				System.out.println("Invalid Col names or Table name or SQL KeyWords:");
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
