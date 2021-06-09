package com.nit.mallik.ps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//INSERT INTO STUDENT VALUES(118,'UDAY','PUNE',57.23,58)
public class PSInsertStudentWthSurrogatePK {

	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(SNO_SEQ1.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			
			scn=new Scanner(System.in);
			int count=0;
			if(scn!=null) {
				System.out.print("Enter Number of Students to Insert: ");
				count=scn.nextInt();
			}//if
			
//Connection Establishment			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
//Preparing Pre compiled sql Query
			if(con!=null)
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);

//Read the input values from enduser and set them to query paramater values and execute the pre compiled sql query for multiple times
			if(scn!=null&&ps!=null) {
				for(int i=1;i<=count;i++) {
					System.out.print("Enter the Student Name: ");
					String sname=scn.next();
					
					System.out.print("Enter the Student City: ");
					String city=scn.next();
					
					System.out.print("Enter the Student Average: ");
					float avg=scn.nextFloat();
					
					System.out.print("Enter the Student Marks: ");
					int marks=scn.nextInt();
					
					ps.setString(1, sname);
					ps.setString(2, city);
					ps.setFloat(3, avg);
					ps.setInt(4, marks);
					
					int result=ps.executeUpdate();
					if(result==0)
						System.out.println(i+" Student Details Not Inserted");
					else
						System.out.println(i+" Student Details are Inserted Successfully");
				}//for
				
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
