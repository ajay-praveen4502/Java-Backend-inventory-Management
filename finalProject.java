package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class finalProject {

	public static void main(String[] args) {
		int total=0;
		// Step 1: Load the driver 
				try { Class.forName("com.mysql.cj.jdbc.Driver"); 
				} 
				catch (ClassNotFoundException e) { 
					e.printStackTrace(); } 
				// Step 2: Establish Connection 
		     Connection con = null; 	//connection is an interface
			
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_Project","root","root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scanner sc = new Scanner(System.in);
				int input = 0,x;
				String empty;
				boolean result = false,a =true;
				while(a){
				System.out.println("________________________________________________________");
				System.out.println("1. Admin");
				System.out.println("2. Agent");
				System.out.println("3. Exit");
				System.out.println("________________________________________________________");
				input = sc.nextInt();
				System.out.println();
				if (input == 1) {
					System.out.print("Enter username: ");
					empty = sc.nextLine();
					String name = sc.nextLine();
					System.out.println();
					System.out.print("Enter password: ");
					String Password = sc.nextLine();
					System.out.println();
					String viewSql = "select * from Admin_details";
					PreparedStatement view;
					try {
						view = con.prepareStatement(viewSql);
						ResultSet rs = view.executeQuery();
						while(rs.next()) 
						{
							if (name.equals(rs.getString("username")) && Password.equals(rs.getString("password"))) {
								result = true;
								System.out.println("Login Successful");
								break;
							}
							else {
								result = false;
							}
						}
						if(result == true) {
							do {
								System.out.println("________________________________________________________");
								System.out.println("1. Display products");
								System.out.println("2. Add Products");
								System.out.println("________________________________________________________");
								x = sc.nextInt();
								if (x == 1) {
									try {
										viewSql = "select * from product";
										view = con.prepareStatement(viewSql);
										rs = view.executeQuery();
										while(rs.next()) 
										{
									//		System.out.println("________________________________________________________");
											System.out.print("| ");
											System.out.print(rs.getInt("Product_ID"));
											System.out.print(" | ");
											System.out.print(rs.getString("Product_Name"));
											System.out.print(" | ");
											System.out.print(rs.getInt("min_selling_quantity"));
											System.out.print(" | ");
											System.out.print(rs.getInt("price"));
											System.out.print(" | ");
											System.out.print(rs.getInt("quantity"));
											System.out.println(" |");
										}
									}
									catch (SQLException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else if(x == 2) {
									System.out.println("________________________________________________________");
									System.out.print("Enter ID: ");
									int ID = sc.nextInt();
									System.out.print("Enter Product name: ");
									empty = sc.nextLine();
									String new_name = sc.nextLine();
									System.out.print("Enter minimum seeling quantity: ");
									int msq = sc.nextInt();
									System.out.print("Enter price: ");
									int new_price = sc.nextInt();
									System.out.print("Enter Quantity: ");
									int qty = sc.nextInt();
									String sql = "insert into Product value(?,?,?,?,?)";
									try {
										PreparedStatement ps = con.prepareStatement(sql);
										ps.setInt(1,ID);
										ps.setString(2,new_name);
										ps.setInt(3,msq);
										ps.setInt(4,new_price);
										ps.setInt(5,qty);
										ps.executeUpdate();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else {
									result = false;
								}
							}
							while(x <= 2 && x>0);
						}
						else {
							System.out.println("Invalid username or password");
						}
					}
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
					}
				if (input == 2) {
					System.out.print("Enter username: ");
					empty = sc.nextLine();
					String name1 = sc.nextLine();
					System.out.println();
					System.out.print("Enter password: ");
					String Password1 = sc.nextLine();
					System.out.println();
					String viewSql1 = "select * from Agent_details";
					PreparedStatement view1;
					try {
						view1 = con.prepareStatement(viewSql1);
						ResultSet rs1 = view1.executeQuery();
						while(rs1.next()) 
						{
							if (name1.equals(rs1.getString("username")) && Password1.equals(rs1.getString("password"))) {
								result = true;
								System.out.println("Login Successful");
							}
						}
						if(result == true) {
							do {
								System.out.println("________________________________________________________");
								System.out.println("1. Buy");
								System.out.println("2. Sell");
								System.out.println("3. logout");
								System.out.println("________________________________________________________");
								x = sc.nextInt();
								
								if (x == 1) {
									System.out.println("________________________________________________________");
									System.out.print("Enter ID: ");
									int ID = sc.nextInt();
									System.out.println();
									System.out.print("Enter Product name: ");
empty = sc.nextLine();
									String new_name = sc.nextLine();
									System.out.println();
									System.out.print("Enter minimum selling quantity: ");
									int msq = sc.nextInt();
									System.out.println();
									System.out.print("Enter price: ");
									int new_price = sc.nextInt();
									System.out.println();
									System.out.print("Enter Quantity: ");
									int qty = sc.nextInt();
									System.out.println();
									String sql = "insert into product value(?,?,?,?,?)";
								
									try {
										PreparedStatement ps = con.prepareStatement(sql);
										ps.setInt(1,ID);
										ps.setString(2,new_name);
										ps.setInt(3,msq);
										ps.setInt(4,new_price);
										ps.setInt(5,qty);
										ps.executeUpdate();
									}catch (SQLException e){
										e.printStackTrace();
									}
								}
								else if (x == 2) {
									System.out.println("________________________________________________________");
									System.out.print("Enter ID you want to sell: ");
									int ID = sc.nextInt();
									System.out.print("Enter quantity required: ");
									empty = sc.nextLine();
									int new_qty = sc.nextInt();
									String sql = "select price from Product where product_ID = ?";
									try {
										PreparedStatement ps1 = con.prepareStatement(sql);
										ps1.setInt(1,ID);
										ResultSet new_price = ps1.executeQuery();
										while(new_price.next()) {
											int val = (new_price.getInt("price"));
											total = (new_qty * val);
											
										}
										System.out.println("Total : "+ total);
										
									}
									catch (SQLException e){
										e.printStackTrace();
									}
								}
								}while(x >= 1 && x < 3);
							}
						else {
							System.out.println("Invalid username or password");
						}
						}
					catch (SQLException e){
						e.printStackTrace();
					}
				}
				else {
					if (input >= 3) {
						break;
					}
					else {
						continue;
					}
				}
			}
	}
}
