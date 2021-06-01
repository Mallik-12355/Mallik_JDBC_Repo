package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//DELETE STUDENT WHERE SNO=104
public class DeleteStudentBySno {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			int sno=0;
			if(scn!=null) {
				System.out.print("Enter Sno: ");
				sno=scn.nextInt();
			}//if
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null)
				st=con.createStatement();
			//Preparing Query
			String query="DELETE STUDENT WHERE SNO="+sno;
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found");
			else
				System.out.println("Number of Recoreds that are effected are "+count);
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<=999)
				System.out.println("Invalid Col Names or Table Name or SQL KeyWords: ");
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
