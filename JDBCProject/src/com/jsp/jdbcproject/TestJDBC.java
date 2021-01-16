package com.jsp.jdbcproject;

import java.util.Scanner;

public class TestJDBC {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		JDBCFunctions anshul= new JDBCFunctions();
		System.out.println("==========This is the Normal application to Create, insert, delete DataTable=========");

		System.out.println("Enter the value that you want to use:\n'create' to create database\n'delete' to delete database\n'ctable' to create table\n'insert' to insert into table\n'del' to delete all values from table\n'drop' table from database\n");
		String value=sc.next();
		
		//create
		if(value.equals("create")){
			System.out.println("Enter name to create database ");
			String name=sc.next();
			anshul.CreateDatabase(name);
			System.out.println("Database succesfully created");

		}else if(value.equals("delete")){//delete
			System.out.println("Enter name to delete database ");
			String name=sc.next();
			anshul.deleteDatabase(name);
			System.out.println("Database succesfully deleted");
			
		}else if(value.equals("ctable")){//create table
			System.out.println("====By default all columns will be not null====");
			System.out.println("Give the name of the database in which you want to create");
			String dname=sc.next();
			System.out.println("Enter name to make table ");
			String name=sc.next();
			System.out.println("Enter column name");
			String Columnname=sc.next();
			System.out.println("Select datatype from int or varchar(255)");
			String datatype=sc.next();
			System.out.println("do you want to make this column as primary key?\n type yes else no");
			String primaryOrnot=sc.next();
			
			try{
				if(primaryOrnot.equals("yes")){
					anshul.createTable(dname,name, Columnname, datatype, true);
				}else{
					anshul.createTable(dname,name, Columnname, datatype, false);
				}
				System.out.println("Table succesfully created");
			}catch(Exception e){
				System.out.println("database name does not exist, please make one first");
			}
		}else if(value.equals("insert")){//insert into table
			System.out.println("Give the name of the database in which you have table");
			String dname=sc.next();
			System.out.println("Enter name of table ");
			String name=sc.next();
			System.out.println("are the values int or varchar\n type int for integer\n type varchar for carchar");
			String value_datatype=sc.next();
			
			if(value_datatype.equals("int")){
				System.out.println("Enter the int values to insert into table");
				int int_value=sc.nextInt();
				try{
					anshul.insertValues(dname, name, int_value);
				}catch(Exception e){
					System.out.println("cannot insert values, please check all columns of table");
				}
			}else{
				System.out.println("Enter the varchar values to insert into table");
				String varchar_value=sc.next();
				try{
					anshul.insertValues(dname, name, varchar_value);
				}catch(Exception e){
					System.out.println("cannot insert values, please check all columns of table");
				}
			}// insert into table over
			
		}else if(value.equals("del")){//delete all values from table
			System.out.println("Enter name to delete database ");
			String name=sc.next();
			System.out.println("enter the name of the table");
			String table_name=sc.next();
			anshul.deleteValues(name, table_name);
			System.out.println("all values succesfully deleted");
			
		}else if(value.equals("drop")){//delete
			System.out.println("Enter name of database ");
			String name=sc.next();
			System.out.println("enter name of the table you want to delete");
			String Table_name=sc.next();
			anshul.drop(name, Table_name);
		}else{
			System.out.println("Sorry cannot create/delete");
		}
		
		
		
		
		
		
		
		
		}

}
