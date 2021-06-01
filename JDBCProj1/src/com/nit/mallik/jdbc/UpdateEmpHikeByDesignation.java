package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//UPDATE EMP SET SAL=SAL+(SAL*(10/100)) WHERE JOB IN('CLERK','MANAGER','ANALYST')
public class UpdateEmpHikeByDesignation {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			String desg1=null;
			String desg2=null;
			String desg3=null;
			int hikePercentage=0;
			
			if(scn!=null) {
				System.out.print("Enter the 1st Employee Disignation: ");
				desg1="'"+scn.next().toUpperCase()+"'";
			
				System.out.print("Enter the 2nd Employee Disignation: ");
				desg2="'"+scn.next().toUpperCase()+"'";
				
				System.out.print("Enter the 3rd Employee Disignation: ");
				desg3="'"+scn.next().toUpperCase()+"'";

				System.out.print("Enter the Percentage to Hike the Employee Salary: ");
				hikePercentage=scn.nextInt();
			}//if
			
			//Establishing The Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing Query
			//UPDATE EMP SET SAL=SAL+(SAL*(10/100)) WHERE JOB IN('CLERK','MANAGER','ANALYST')
			String query="UPDATE EMP SET SAL=SAL+(SAL*("+hikePercentage+"/100)) WHERE JOB IN("+desg1+","+desg2+","+desg3+")";
			System.out.println(query);
			
			int count=0;
			//Processing Result
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found: ");
			else
				System.out.println(count+" Records are effected: ");
		}//try
		
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<999)
				System.out.println("Invalid Column Names or Table Name or SQL Keywords: ");
			if(se.getErrorCode()==12899)
				System.out.println("Do not Insert More than Col size");
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
			}
			catch(Exception e) {
				e.printStackTrace();
			}//catch
			
		}//finally
		
	}//main

}//class
