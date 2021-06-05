package com.nit.mallik.ps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//INSERT INTO CUSTOMER VALUES(222,'UMA','KARIMNAGAR',9636458778)
public class PSInsertCustomer {
	
	private static final String CUSTOMER_INSERT_QUERY = "INSERT INTO CUSTOMER VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			scn=new Scanner(System.in);
			int count=0;
			if(scn!=null) {
				System.out.print("Enter Number of Customers to Insert: ");
				count = scn.nextInt();
			}//if
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
	
			if(con!=null)
				ps = con.prepareStatement(CUSTOMER_INSERT_QUERY);
			
			if(ps!=null&&scn!=null) {
				for(int i=1;i<=count;++i) {
					System.out.print("Enter "+i+" Customer Details: ");
					
					System.out.print("\nEnter Customer No: ");
					int cNo=scn.nextInt();
					
					System.out.print("Enter Customer Name: ");
					String cName=scn.next().toUpperCase();
					
					System.out.print("Enter Customer Area: ");
					String cArea=scn.next().toUpperCase();
					
					System.out.print("Enter Customer Mobile: ");
					long cMob=scn.nextLong();
					
					ps.setInt(1, cNo);
					ps.setString(2, cName);
					ps.setString(3, cArea);
					ps.setLong(4, cMob);
					
					int result=ps.executeUpdate();
					
					if(result==0)
						System.out.println(i+" Customer Details Not Inserted: ");
					else
						System.out.println(i+" Customer Details were Inserted Successfully: ");
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
