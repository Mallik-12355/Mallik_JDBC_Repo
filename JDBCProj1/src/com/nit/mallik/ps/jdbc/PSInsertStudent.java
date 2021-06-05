package com.nit.mallik.ps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//INSERT INTO STUDENT VALUES(115,'PRASAD','KERALA',57.64,58)
public class PSInsertStudent {
	
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?,?)";
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
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null)
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			if(ps!=null&&scn!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter "+i+" Student Details: ");
					
					System.out.print("Enter Student Number: ");
					int sno=scn.nextInt();scn.nextLine();
					
					System.out.print("Enter Student Name: ");
					String sname=scn.nextLine();

					System.out.print("Enter Student City: ");
					String city=scn.nextLine();
					
					System.out.print("Enter Student Average: ");
					float avg=scn.nextFloat();
					
					System.out.print("Enter Student Marks: ");
					int marks=scn.nextInt();
					
					ps.setInt(1, sno);
					ps.setString(2, sname);
					ps.setString(3, city);
					ps.setFloat(4, avg);
					ps.setInt(5, marks);
					
					int result=ps.executeUpdate();
					
					if(result==0)
						System.out.println(i+"Student Details not Inserted: ");
					else
						System.out.println(i+" Student Details are Inserted Successfully: ");
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
