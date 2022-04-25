package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Item {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab", "root", "");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	public String insertItem(String code, String name, String price, String desc)
	{
	   String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
		{
			
			return "Error while connecting to the database for inserting."; 
		}
	
		  // create a prepared statement
			String query = " insert into user(`nID`,`nic`,`tele`,`address`,`meterNo`)"+ " values (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4,price);
			preparedStmt.setString(5, desc);
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readItems()
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {	
			  return "Error while connecting to the database for reading."; 
	      }
			  
		  	// Prepare the html table to be displayed
			  output = "<table border='1'><tr><th>nic</th><th>tele</th>" +
					  "<th>address</th>" +
					  "<th>meternumber</th>" ;
			  String query = "select * from user";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
				String nID = Integer.toString(rs.getInt("nID"));
				String nic = rs.getString("nic");
				String tele = rs.getString("tele");
				String address = rs.getString("address");
				String meterNo = rs.getString("meterNo");
				// Add into the html table
				output += "<tr><td>" + nic + "</td>";
				output += "<td>" + tele + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + meterNo + "</td>";
			
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while reading the user.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateItem(String ID, String code, String name, String price, String desc)
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {
			  return "Error while connecting to the database for updating."; 
		  }
			  
			  // create a prepared statement
				  String query = "UPDATE user SET nic=?,tele=?,address=?,meterNo=? WHERE nID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, price);
				preparedStmt.setString(4, desc);
				preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
		  }
		  catch (Exception e)
		  {
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteItem(String nID)
	{
		String output = "";
		  try
		  {
			  	Connection con = connect();
			  	if (con == null)
		  {
				return "Error while connecting to the database for deleting."; 
		  }
			  	
			// create a prepared statement
			  	String query = "delete from user where nID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(nID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			  	output = "Deleted successfully";
		  }
		  catch (Exception e)
		  {
			  	output = "Error while deleting the item.";
			  	System.err.println(e.getMessage());
		  }
		  return output;
			}
	}
