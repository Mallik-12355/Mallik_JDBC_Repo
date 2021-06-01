package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//UPDATE STUDENT SET SNAME='BALU',SADD='VIZAG',AVG=75.8 WHERE SNO=103
public class UpdateStudentSnameSaddAvg {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			String newName=null;
			String newCity=null;
			float newAvg=0.0f;
			int sno=0;
			if(scn!=null) {
				System.out.print("Enter New Name of the Student: ");
				newName="'"+scn.nextLine().toUpperCase()+"'";
				System.out.print("Enter New City of the Student: ");
				newCity="'"+scn.nextLine().toUpperCase()+"'";
				System.out.print("Enter New Average of the Student: ");
				newAvg=scn.nextFloat();
				System.out.print("Enter Student No:");
				sno=scn.nextInt();
			}
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");

			//JDBC Object Creation
			if(con!=null)
				st=con.createStatement();
			
			//Preparing SQL Query:
			//UPDATE STUDENT SET SNAME='BALU',SADD='VIZAG',AVG=75.8 WHERE SNO=103
			String query="UPDATE STUDENT SET SNAME="+newName+",SADD="+newCity+",AVG="+newAvg+" WHERE SNO="+sno;
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found: ");
			else
				System.out.println("The "+sno+" Record is Updated Successfully:");
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
