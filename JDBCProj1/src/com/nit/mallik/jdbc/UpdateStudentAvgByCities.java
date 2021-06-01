package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//UPDATE STUDENT SET AVG=AVG+((10/100)*MARKS) WHERE SADD IN('HYDERABAD','VIZAG','MUMBAI')
public class UpdateStudentAvgByCities {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			String city1=null;
			String city2=null;
			String city3=null;
			int percentage=0;
			
			if(scn!=null) {
				System.out.print("Enter 1st City Name: ");
				city1="'"+scn.nextLine().toUpperCase()+"'";
				System.out.print("Enter 2nd City Name: ");
				city2="'"+scn.nextLine().toUpperCase()+"'";
				System.out.print("Enter 3rd City Name: ");
				city3="'"+scn.nextLine().toUpperCase()+"'";
				System.out.print("Enter Percentage: ");
				percentage=scn.nextInt();
			}//if
			
			//Establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing SQL Query
			//UPDATE STUDENT SET AVG=AVG+(MARKS*(10/100)) WHERE SADD IN('HYDERABAD','VIZAG','MUMBAI')
			String query="UPDATE STUDENT SET AVG=AVG+(MARKS*("+percentage+"/100)) WHERE SADD IN("+city1+","+city2+","+city3+")";
			//UPDATE STUDENT SET AVG=AVG+(MARKS*(20/100)) WHERE SADD IN('HYDERABAD','MUMBAI','VIZAG')

			System.out.println(query);
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found");
			else 
				System.out.println("The number of Records that are effected are: "+count);
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
