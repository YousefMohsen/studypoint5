/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Yousinho
 */
@Path("quote")
public class QuoteResource {
    
    private static Map<Integer,String> quotes = new HashMap(){
            
        {
        put(1,"Friends are kisses blown to us by angels");
        put(2,"Do not take life too seriously. You will never get out of i alive");
        put(3, "Behind every great man, is a woman rolling her eyes");
        }               
    };
    
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QouteResource
     */
    public QuoteResource() {
    }

    /**
     * Retrieves representation of an instance of rest.QouteResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandom() {
        //TODO return proper representation object
      
        
        try{
          int newRandom = new Random().nextInt(quotes.size())+1;

            String r = quotes.get(newRandom);
        
        if(r.length() == 0){throw new NullPointerException();}
        return Response.status(200).entity(r).build();
        }
        catch(NullPointerException e){
        
        return Response.status(404).entity("No quotes created yet ").build();
        }
       
    }
      /**
     * Retrieves representation of an instance of rest.QouteResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("id") int id) {
        
        try{String r = quotes.get(id);
        if(r.length() == 0){throw new NullPointerException();}
        return Response.status(200).entity(r).build();
        }
        catch(NullPointerException e){
        
        return Response.status(404).entity("Quote with requested id not found ").build();
        }
        //TODO return proper representation object
  
    }

    /**
     * PUT method for updating or creating an instance of QouteResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }



    /**
     * Post method for creating an instance of QouteResource
     *
     * @param content representation for the resource
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String postJson(String content) {
   
    quotes.put(quotes.size()+1, content);
    return quotes.get(quotes.size());
    
    }
    


    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteJson(@PathParam("id") int id) {
        
                
        try{

 String deleted = quotes.get(id);
  if( quotes.remove(id)==null){
  throw new NullPointerException();}
 
        return Response.status(200).entity(deleted).build();
        }
        catch(NullPointerException e){
        
        return Response.status(404).entity("Quote with requested id not found").build();
        }
        

    
    
    }
}
