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
public class ItemService
{
Item itemObj = new Item();


//GET
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return itemObj.readItems();
}

//post
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("dist") String dist,
@FormParam("unitp") String unitp)
{
	String output = itemObj.insertItem(dist, unitp);
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
 String unitID = itemObject.get("unitID").getAsString();
 String dist = itemObject.get("dist").getAsString();
 String unitp = itemObject.get("unitp").getAsString();

 String output = itemObj.updateItem(unitID, dist, unitp);
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

//Read the value from the element <unitID>
 String unitID = doc.select("unitID").text();
 String output = itemObj.deleteItem(unitID);
return output;
}

}