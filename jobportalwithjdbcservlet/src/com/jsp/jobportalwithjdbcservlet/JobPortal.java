package com.jsp.jobportalwithjdbcservlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class JobPortal {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("press 1 to register as student\npress 2 to register as company\npress 3 to get the eligible candidates details for interview\npress 4 to see all the companies and thier requirements");
		int input=sc.nextInt();
		
		if(input==1){
			System.out.println("you wanted to register as student\n");
			System.out.print("Enter the name:");
			sc.nextLine();
			String s_name=sc.nextLine();
			System.out.println("Enter the email of the student");
			String s_email=sc.nextLine();
			System.out.println("Enter the percentage without decimal");
			int s_per=sc.nextInt();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportaljdbc?user=root&password=root");
				Statement st1=(Statement) con.createStatement();
				String query="select * from companyregister";
				ResultSet rs=st1.executeQuery(query);
				while(rs.next()){
					System.out.println("company id: "+rs.getInt("cid")+"  company name is: "+rs.getString("cname"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("\nEnter the id of the student:\n");
			int s_id=sc.nextInt();
			
			HashSet<StudentRegister> hs=new HashSet<>();
			boolean b=hs.add(new StudentRegister(s_name, s_id, s_email,s_per));
			if(b){
				System.out.println("Succesfully added into Collection");
				System.out.println("Adding you into Main Database");
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportaljdbc?user=root&password=root");
					Statement st=(Statement) con.createStatement();
					st.execute("insert into studentregister values("+s_id+",'"+s_name+"','"+s_email+"',"+s_per+")");
					System.out.println("Entered into main Database");
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				System.out.println("Duplicate id found, cannot Signup again");
			}
					
		}else if(input==2){
			System.out.println("you wanted to register as company\n");
			System.out.print("Enter the name:");
			sc.nextLine();
			String c_name=sc.nextLine();
			System.out.println("\nEnter the id of the company:\n");
			int c_id=sc.nextInt();
			System.out.println("Enter the requiremnet percentage of the company");
			sc.nextLine();
			int c_per=sc.nextInt();
			
			HashSet<CompanyRegister> hc=new HashSet<>();
			boolean b=hc.add(new CompanyRegister(c_id, c_name, c_per));
			if(b){
				System.out.println("Succesfully added into Collection");
				System.out.println("Adding you into Main Database");
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportaljdbc?user=root&password=root");
					Statement st=(Statement) con.createStatement();
					st.execute("insert into companyregister values("+c_id+",'"+c_name+"','"+c_per+"')");
					System.out.println("Entered into main Database");
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				System.out.println("Duplicate id found, cannot Signup again");
			}
		}else if(input==3){
			System.out.println("you want to get details for the company who are eligible candidates");
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportaljdbc?user=root&password=root");
				Statement st=(Statement) con.createStatement();
				String query="SELECT cid,cname,cper,sid,sname,sgmail,percentage from (companyregister c JOIN studentregister s ON cid=sid);";
				ResultSet rs=st.executeQuery(query);
				
				System.out.println();
				System.out.println("Selected student for this company is this: congratulations!!!\n");
				while(rs.next()){
					
					int check_it=rs.getInt("percentage");
					int c_per=rs.getInt("cper");
					if(check_it>=c_per){
						System.out.print("company id is: "+rs.getString("cid")+", ");
						System.out.print("company name is: "+rs.getString("cname")+", ");
						System.out.print("Student name is: "+rs.getString("sname")+", ");
						System.out.print("sgmail is: "+rs.getString("sgmail")+" ");
						System.out.println();
					}
				}
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(input==4){
			System.out.println("you wanted to check all the comapnies and their requirements");
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportaljdbc?user=root&password=root");
				Statement st=(Statement) con.createStatement();
				String query="SELECT * from companyregister;";
				ResultSet rs=st.executeQuery(query);
				
				System.out.println();
				System.out.println("All the registered companies and their requirements are:\n");
				while(rs.next()){
					
					System.out.print("Company id is: "+rs.getInt("cid")+", ");
					System.out.print("Company name is: "+rs.getString("cname")+", ");
					System.out.print("Comapny requirement % is: "+rs.getInt("cper"));
					System.out.println();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
