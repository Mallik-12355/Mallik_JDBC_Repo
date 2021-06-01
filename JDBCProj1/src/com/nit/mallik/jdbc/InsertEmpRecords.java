package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7925,'STEPHEN','MANAGER',4500)
public class InsertEmpRecords {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		
		try {
			scn=new Scanner(System.in);
			int empNo=0;
			String eName=null;
			String job=null;
			float sal=0.0f;
			if(scn!=null) {
				System.out.print("Enter Employee Number: ");
				empNo=scn.nextInt();scn.nextLine();

				System.out.print("Enter Employee Name: ");
				eName="'"+scn.nextLine().toUpperCase()+"'";
				
				System.out.print("Enter Employee Job: ");
				job="'"+scn.nextLine().toUpperCase()+"'";
				
				System.out.print("Enter Employee Salary: ");
				sal=scn.nextFloat();
			}//if
			
			//Establishing Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			//Create JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			
			//Preparing Query
			//INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7925,'STEPHEN','MANAGER',4500)
			String query="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(+"+empNo+","+eName+","+job+","+sal+")";
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No Records Found: ");
			else 
				System.out.println("Record Inserted Successfully");
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
