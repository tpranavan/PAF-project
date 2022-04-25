package com;

import model.Emp;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Emp")

public class EmpService{

	Emp itemObj = new Emp();
	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String readItems()
//	{
//	return "Hello";
//	}
	
	//GET
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return itemObj.readItems();
	}
	
	//POST
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("eName") String eName,
	 @FormParam("eRole") String eRole,
	 @FormParam("ePass") String ePass)
	
	{
	 String output = itemObj.insertItem(eName, eRole	, ePass);
	return output;
	}
	
	//PUT
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String eID = itemObject.get("eID").getAsString();
	 String eName = itemObject.get("eName").getAsString();
	 String eRole = itemObject.get("eRole").getAsString();
	 String ePass = itemObject.get("ePass").getAsString();
	 String output = itemObj.updateItem(eID, eName, eRole, ePass);
	return output;
	}
	
	//DELETE
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String eID = doc.select("eID").text();
	 String output = itemObj.deleteItem(eID);
	return output;
	}

}