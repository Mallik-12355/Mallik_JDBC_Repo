package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//INSERT INTO STUDENT VALUES(106,'FANI','PUNE',65.61,66);
public class InsertStudentRecords {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			int sno=0;
			String sName=null;
			String city=null;
			float avg=0.0f;
			int marks=0;
			
			if(scn!=null) {
				System.out.print("Enter Sno: ");
				sno=scn.nextInt();scn.nextLine();
				
				System.out.print("Enter Sname: ");
				sName="'"+scn.nextLine().toUpperCase()+"'";
				
				System.out.print("Enter City: ");
				city="'"+scn.nextLine().toUpperCase()+"'";
				
				System.out.print("Enter Avg: ");
				avg=scn.nextFloat();
				
				System.out.print("Enter Marks: ");
				marks=scn.nextInt();
			}//if
			
			//Establishing the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Creating  JDBC Statement Obj
			if(con!=null)
				st=con.createStatement();
			
			//Preparing Query
			//INSERT INTO STUDENT VALUES(106,'FANI','PUNE',65.61,66)
			String query="INSERT INTO STUDENT VALUES("+sno+","+sName+","+city+","+avg+","+marks+")";
			System.out.println(query);
			System.out.println();
			int count=0;
			
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("Record Not Inserted: ");
			else
				System.out.println("Record Was Successfully Inserted: ");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1) {
				System.out.println("Duplicate Data is not allowed in Primary Key Columns: ");
				System.out.println();
			}//if
			if(se.getErrorCode()==12899) {
				System.out.println("Don't Enter Column Value More than the Size of the Column: ");
				System.out.println();
			}//if
			if(se.getErrorCode()==984) {
				System.out.println("Don't Enter String Value. Please Enter the Average in Number Format: ");
				System.out.println();
			}
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
			//closing jdbc objects
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
