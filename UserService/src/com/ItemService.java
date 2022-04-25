package com;

import model.Item;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")

public class ItemService{

	Item itemObj = new Item();
	
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
	public String insertItem(@FormParam("nic") String nic,
	 @FormParam("tele") String tele,
	 @FormParam("address") String address,
	 @FormParam("meterNo") String meterNo)
	{
	 String output = itemObj.insertItem(nic, tele, address, meterNo);
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
	 String nID = itemObject.get("nID").getAsString();
	 String nic = itemObject.get("nic").getAsString();
	 String tele = itemObject.get("tele").getAsString();
	 String address = itemObject.get("address").getAsString();
	 String meterNo = itemObject.get("meterNo").getAsString();
	 String output = itemObj.updateItem(nID, nic, tele, address, meterNo);
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

	//Read the value from the element <nID>
	 String nID = doc.select("nID").text();
	 String output = itemObj.deleteItem(nID);
	return output;
	}

}