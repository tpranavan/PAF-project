package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Emp {
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
	
	public String insertItem(String code, String name, String price)
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
			String query = " insert into emp(`eID`,`eName`,`eRole`,`ePass`)"+ " values (?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, price);
	
	
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
			  return "Error while connecting to the database for ePass."; 
	      }
			  
		  	// Prepare the html table to be displayed
			  output = "<table border='1'><tr><th>eName</th><th>eRole</th>" +
					  "<th>ePassword</th>" 
					  
					 ;
			  String query = "select * from emp where ePass = 143 ";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
				String eID = Integer.toString(rs.getInt("eID"));
				String eName = rs.getString("eName");
				String eRole = rs.getString("eRole");
				String ePass = rs.getString("ePass");
			
				// Add into the html table
				output += "<tr><td>" + eName + "</td>";
				output += "<td>" + eRole + "</td>";
				output += "<td>" + ePass + "</td>";
		
			
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while ePass the emp.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateItem(String ID, String code, String name, String price)
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
				  String query = "UPDATE emp SET eName=?,eRole=?,ePass=? WHERE eID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, price);
				preparedStmt.setInt(4, Integer.parseInt(ID));
			 
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
			
	public String deleteItem(String eID)
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
			  	String query = "delete from emp where eID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(eID));
			
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
