package com.jsp.jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCFunctions {
	
	void CreateDatabase(String DatabaseName){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/incubation?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("create database " +DatabaseName+ ";");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void deleteDatabase(String DatabaseNametodelete){		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/incubation?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("drop database " +DatabaseNametodelete+ ";");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void createTable(String Databasename ,String Tablename, String column, String datatype, boolean b){		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Databasename+"?user=root&password=root");
			Statement st=con.createStatement();
			if(datatype.equals("int")){
					if(b){
						st.execute("create table " +Tablename+ "( "+column+"  int primary key);");
					}else{
						st.execute("create table " +Tablename+ "( "+column+"  int not null);");
						}
			}else{
				if(b){
					st.execute("create table " +Tablename+ "( "+column+" varchar(255) primary key) ;");
				}else{
					st.execute("create table "+Tablename+"("+column+"varchar(255) not null);");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//create table ended
//for string value
	void insertValues(String Databasename ,String Tablename, String column){//insertion values
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Databasename+"?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("insert into " +Tablename+ "values ('"+column+"');");
			
		} catch (Exception e) {
			System.out.println("column not found, please make a new table");
		}
	}
	//overload method for different int value
	void insertValues(String Databasename ,String Tablename, int column){//insertion values
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Databasename+"?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("insert into " +Tablename+ " values ("+column+");");
			
		} catch (Exception e) {
			System.out.println("column not found, please make a new table");
		}
	}
	
	//delete all values from table
	void deleteValues(String Databasename ,String Tablename){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Databasename+"?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("delete from " +Tablename+ ";");
			
		} catch (Exception e) {
			System.out.println("column not found, please make a new table");
		}
	}
	//drop table from database
	void drop(String Databasename ,String Tablename){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Databasename+"?user=root&password=root");
			Statement st=con.createStatement();
			st.execute("drop table " +Tablename+ ";");
			System.out.println("table deleted");
		} catch (Exception e) {
			System.out.println("column not found, please make a new table");
		}
	}

}






