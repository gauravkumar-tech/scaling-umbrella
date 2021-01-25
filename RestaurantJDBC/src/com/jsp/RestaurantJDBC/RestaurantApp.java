package com.jsp.RestaurantJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.Statement;

public class RestaurantApp {

	public static void main(String[] args) {
		System.out.println("welocome to Domino's");
		System.out.println("Press 1 to add into main menu\nPress 2 to order as Customer");
		Scanner sc=new Scanner(System.in);
		StringBuilder sb=new StringBuilder();
		int button=sc.nextInt();
		
		if(button==1){
			System.out.println("You selected to add into main menu");
			System.out.println("Do you want to edit main_menu, if yes press 1\nPress 2 to edit sub_menu");
			int edit=sc.nextInt();
			if(edit==1){
				System.out.println("Please enter the S.No of the Main_menu");
				int S_no=sc.nextInt();
				System.out.println("Please enter the Menu for the Main_menu");
				sc.nextLine();
				String main_menu=sc.nextLine();
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
					Statement st=(Statement) con.createStatement();
					st.execute("insert into main_menu values("+S_no+",'"+main_menu+"');");
					System.out.println("menu added");
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}else if(edit==2){
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
					Statement st=(Statement) con.createStatement();
					String query="select * from main_menu;";
					ResultSet rs=st.executeQuery(query);
					while(rs.next()){
						System.out.println(rs.getString("main_name"));
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}	
				
				int stop;
				do{
				System.out.println("press 0 to stop, press 1 to keep on adding");
				stop=sc.nextInt();	
					if(stop==0){
						System.out.println("you wanted to stop, we have stopped");
						break;
					}else if(stop!=0){
						
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
							Statement st=(Statement) con.createStatement();
							
							String query="select * from sub_menu;";
							ResultSet rs=st.executeQuery(query);
							while(rs.next()){
								System.out.println("menus are S.No: "+rs.getString("subid")+", Sub menu: "+rs.getString("sub_name")+", Price is: "+rs.getString("price")+", Category is: "+rs.getString("category"));
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						
						System.out.println("Please enter the S.No of the sub_menu");
						int S_no=sc.nextInt();
						System.out.println("Please enter the Menu for the Sub_menu");
						sc.nextLine();
						String sub_menu=sc.nextLine();
						System.out.println("Enter the price of the item");
						int price=sc.nextInt();
						System.out.println("Enter the Category");
						sc.nextLine();
						String category=sc.nextLine();
						
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
							Statement st1=(Statement) con1.createStatement();
							st1.execute("insert into sub_menu values("+S_no+",'"+sub_menu+"',"+price+",'"+category+"');");
							System.out.println("sub_menu added");
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
				}while(stop!=0);	

			}
				
		}else if(button==2){//for ordering
			String cat;
			int sum=0;
			int stop;
		do{
			System.out.println("do you want to stop, Press 0 to generate bill and press 1 to resumne and wsee main menu");
			stop=sc.nextInt();
			if(stop!=0){
				
			try{//printing main menu
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
				Statement st=(Statement) con.createStatement();
				String query="select * from main_menu;";
				ResultSet rs=st.executeQuery(query);
				
				while(rs.next()){
					System.out.println(rs.getString("main_name"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}	// printing main menu done
			
			//taking value of category and printing the category menu
			
			try{//printing sub menu
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
				Statement st=(Statement) con.createStatement();

				System.out.println("Enter the Category:");
				sc.nextLine();
				cat=sc.nextLine();
				String query="select * from sub_menu where category='"+cat+"';";
				ResultSet rs=st.executeQuery(query);
				
				while(rs.next()){
					System.out.println("menus are S.No: "+rs.getString("subid")+", Sub menu: "+rs.getString("sub_name")+", Price is: "+rs.getString("price")+", Category is: "+rs.getString("category"));
					
				
				}
				int press_me;
				do{
					System.out.println("press 1 to oder and 0 to return to main menu");
					press_me=sc.nextInt();
					if(press_me==0){
						break;
					}else{
					System.out.println("you have pressed 1 to order\nPlease enter the S.no of the menu to select.");
					int ss_no=sc.nextInt();
					System.out.println("Enter the Quantity");
					int quantity=sc.nextInt();
						try{
							Class.forName("com.mysql.jdbc.Driver");
							Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?user=root&password=root");
							Statement st3=(Statement) con.createStatement();
							String query3="select * from sub_menu where subid="+ss_no+";";//update this
							ResultSet rs3=st.executeQuery(query3);
							
							while(rs3.next()){
								System.out.println("menus are S.No: "+rs3.getString("subid")+", Sub menu: "+rs3.getString("sub_name")+", Price is: "+rs3.getString("price")+", Category is: "+rs3.getString("category"));
								sb.append("menus are S.No: "+rs3.getString("subid")+", Sub menu: "+rs3.getString("sub_name")+", Price is: "+rs3.getString("price")+", Category is: "+rs3.getString("category")+"\n"+" Quantity is Selected: "+quantity+"\n");
								System.out.println("order:\n\n"+sb);
								int sub_menu_with_quantity=rs3.getInt("price")*quantity;
								sum+=sub_menu_with_quantity;
								
								System.out.println("total sum: "+sum);
							}
							
						
						
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}while(press_me!=0);
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}//printing sub menu done
			}else if(stop==0){
				System.out.println("======You bill generating======");
				System.out.println("=========Your Orders=======\n"+sb);
				System.out.println("=========Your Total sum========\nRs."+sum);
				
			}
		}while(stop!=0);	
			
		}
		
		
	}

}
