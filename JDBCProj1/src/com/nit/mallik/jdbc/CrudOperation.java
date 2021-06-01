package com.nit.mallik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//SELECT * FROM STUDENT
//INSERT INTO STUDENT VALUES(109,'ILESH','WARANGAL',66.74,67)
//UPDATE STUDENT SET SNAME='FATHIMA' WHERE SNO=106
//DELETE FROM STUDENT WHERE SNO=109
public class CrudOperation {

	public static void main(String[] args) {
		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			scn=new Scanner(System.in);
			
			String tableName=null;
			String sName=null;
			String sCity=null;
			int sNo=0;
			int sMarks=0;
			float avg=0.0f;
			
			String selectQuery=null;
			String insertQuery=null;
			String updateQuery=null;
			String deleteQuery=null;
			
			int count=0;
			

			if(scn!=null) {
				System.out.print("Enter Table Name: ");
				tableName=scn.next().toUpperCase();

				System.out.print("Enter Student Name: ");
				sName="'"+scn.next().toUpperCase()+"'";

				System.out.print("Enter Student City: ");
				sCity="'"+scn.next().toUpperCase()+"'";

				System.out.print("Enter Student No: ");
				sNo=scn.nextInt();
				
				System.out.print("Enter Student Marks: ");
				sMarks=scn.nextInt();

				System.out.print("Enter Student Average: ");
				avg=scn.nextFloat();
			}//if
			
			
			//Preparing Queries	

			//SELECT SNO,SNAME,SADD,AVG,MARKS FROM STUDENT
			selectQuery="SELECT SNO,SNAME,SADD,AVG,MARKS FROM "+tableName;
			
			//INSERT INTO STUDENT VALUES(109,'ILESH','WARANGAL',66.74,67)
			insertQuery="INSERT INTO "+tableName+" VALUES("+sNo+","+sName+","+sCity+","+avg+","+sMarks+")";
			
			//UPDATE STUDENT SET SNAME='FATHIMA' WHERE SNO=106
			updateQuery="UPDATE "+tableName+" SET SNAME="+sName+" WHERE SNO="+sNo;
			
			//DELETE FROM STUDENT WHERE SNO=109
			deleteQuery="DELETE FROM "+tableName+" WHERE SNO="+sNo;
			
			System.out.println(selectQuery);
			System.out.println();

			System.out.println(insertQuery);
			System.out.println();
			
			System.out.println(updateQuery);
			System.out.println();
			
			System.out.println(deleteQuery);
			System.out.println();
//======================================================================================================================================

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sys");
			
			if(con!=null)
				st=con.createStatement();
			
			if(scn!=null) {
				System.out.print("Enter Table Name for Selecting: ");
				tableName=scn.next().toUpperCase();
			}//if
			
			//Preparing Query	
			//SELECT SNO,SNAME,SADD,AVG,MARKS FROM STUDENT
			selectQuery="SELECT SNO,SNAME,SADD,AVG,MARKS FROM "+tableName;
			if(st!=null) 
				rs=st.executeQuery(selectQuery);
				
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				System.out.println();
			}//while
//======================================================================================================================================
				
			if(scn!=null) {
				System.out.print("Enter Table Name for Inserting the Record: ");
				tableName=scn.next().toUpperCase();
					
				System.out.print("Enter Student No: ");
				sNo=scn.nextInt();scn.nextLine();
	
				System.out.print("Enter Student Name: ");
				sName="'"+scn.next().toUpperCase()+"'";
					
				System.out.print("Enter Student City: ");
				sCity="'"+scn.next().toUpperCase()+"'";
					
				System.out.print("Enter Student Average: ");
				avg=scn.nextFloat();
					
				System.out.print("Enter Student Marks: ");
				sMarks=scn.nextInt();
			}//if
				
				//Preparing SQL Query	
				//INSERT INTO STUDENT VALUES(109,'ILESH','WARANGAL',66.74,67)
				insertQuery="INSERT INTO "+tableName+" VALUES("+sNo+","+sName+","+sCity+","+avg+","+sMarks+")";
				System.out.println(insertQuery);
				
				count=0;
				if(st!=null)
					count=st.executeUpdate(insertQuery);
				
				if(count==0) {
					System.out.println("Record was Not Inserted: ");
					System.out.println();
				}//if
				else {
					System.out.println("Record was Successfully Inserted: ");
					System.out.println();
				}//else
//======================================================================================================================================
			
			if(scn!=null) {
				System.out.print("Enter Table Name for Updating the Records: ");
				tableName=scn.next().toUpperCase();
						
				System.out.print("Enter Student New Name for Update: ");
				sName="'"+scn.next().toUpperCase()+"'";
						
				System.out.print("Enter Student No. Where to Update: ");
				sNo=scn.nextInt();
			}//if
					
			//Preparing SQLQuery
			//UPDATE STUDENT SET SNAME='FATHIMA' WHERE SNO=106
			updateQuery="UPDATE "+tableName+" SET SNAME="+sName+" WHERE SNO="+sNo;
			System.out.println(updateQuery);
					
			count=0;
			if(st!=null)
				count=st.executeUpdate(updateQuery);
					
			if(count==0) {
				System.out.println("No Records Effected: ");
				System.out.println();
			}//if
			else {
				System.out.println(count+" Record is Effected: ");
				System.out.println();
			}//else
//======================================================================================================================================
					
			if(scn!=null) {
				System.out.print("Enter Table Name for Deleting a Record: ");
				tableName=scn.next().toUpperCase();
					
				System.out.print("Enter Student No to Delete: ");
				sNo=scn.nextInt();
			}//if

			//Preparing SQL Query	
			//DELETE FROM STUDENT WHERE SNO=109
			deleteQuery="DELETE FROM "+tableName+" WHERE SNO="+sNo;
			System.out.println(deleteQuery);
			
			count=0;
			if(st!=null)
				count=st.executeUpdate(deleteQuery);
				
			if(count==0) {
				System.out.println("No Records Effected: ");
				System.out.println();
			}//if
			else {
				System.out.println(count+" Record is Effected: ");
				System.out.println();
			}//else
//======================================================================================================================================

		}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch

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
/**
 * SQL> select * from student;

       SNO SNAME                SADD              AVG      MARKS
---------- -------------------- -------------------- ---------- ----------
       101 AKHIL                HYDERABAD    76.35         77
       102 BALU                  VIZAG                91.86         92
       103 CHANDHU        KARNOOL         83.68         84
       104 DINESH              MUMBAI           64.81         65
       105 ESHWAR             BANGLORE      53.85         54
       106 FATHIMA            PUNE                 65.61         66
       107 GANI                 CHENNAI            71.62         72
       108 HARI                 HYDERABAD      93.67         94

8 rows selected.
 */
