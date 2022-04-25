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
  
  public String insertItem(String role, String name)
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
      String query = " insert into units(`unitID`,dist,unitp)"+ " values (?, ?, ?)";
          PreparedStatement preparedStmt = con.prepareStatement(query);
         
        // binding values
      preparedStmt.setInt(1, 0);
      preparedStmt.setString(2, role);
      preparedStmt.setString(3, name);
     
      
  
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
        output = "<table border='1'><tr><th>units</th><th>district</th>" ;
        String query = "select * from units  ";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    
       // iterate through the rows in the result set
      
      while (rs.next())
      {
        String unitID = Integer.toString(rs.getInt("unitID"));
        String dist = rs.getString("dist");
        String unitp = rs.getString("unitp");
    
       
        // Add into the html table
        output += "<tr><td>" + dist + "</td>";
        output += "<td>" + unitp + "</td>";

        // buttons
       
      }
        con.close();
        // Complete the html table
        output += "</table>";
      }
      catch (Exception e)
      {
        output = "Error while reading the units.";
        System.err.println(e.getMessage());
      }
    return output;
  }
  
  public String updateItem(String ID, String role, String name)
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
          String query = "UPDATE units SET dist=?,unitp=? WHERE unitID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
        
        // binding values
preparedStmt.setString(1, role);
        preparedStmt.setString(2, name);
        preparedStmt.setInt(3, Integer.parseInt(ID));
       
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
      
  public String deleteItem(String unitID)
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
          String query = "delete from units where unitID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
      // binding values
          preparedStmt.setInt(1, Integer.parseInt(unitID));
      
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