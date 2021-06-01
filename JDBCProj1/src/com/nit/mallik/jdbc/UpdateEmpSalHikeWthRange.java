package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//UPDATE EMP SET SAL=SAL+(SAL*(10/100)) WHERE SAL BETWEEN 800 AND 1100
public class UpdateEmpSalHikeWthRange {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			int startingRange=0;
			int endingRange=0;
			int hikePercentage=0;
			
			if(scn!=null) {
				System.out.print("Enter Starting Range of the Sal: ");
				startingRange=scn.nextInt();
				System.out.print("Enter Ending Range of the Sal: ");
				endingRange=scn.nextInt();
				System.out.print("Enter Hike Percentage: ");
				hikePercentage=scn.nextInt();
			}//if
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null)
				st=con.createStatement();
			//Preparing SQL Query
			//UPDATE EMP SET SAL=SAL+(SAL*(10/100)) WHERE SAL BETWEEN 800 AND 1100
			String query="UPDATE EMP SET SAL=SAL+(SAL*("+hikePercentage+"/100)) WHERE SAL BETWEEN "+startingRange+" AND "+endingRange;
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found: ");
			else
				System.out.println("The Number of Records that are effected are: "+count);
		}//try
		
		catch(SQLException se) {
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
			catch(SQLException se){
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
