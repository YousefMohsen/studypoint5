/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Person;
import facades.DBFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
@Path("person")
public class PersonResource {
    
    Gson gson = new Gson();
    DBFacade  dbf = new DBFacade(); 

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") int id) {
        //TODO return proper representation object
       Person p =  dbf.getPersonWithID(id);
       
        return gson.toJson(p);

    }
    
    
    //get all
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
       List  p =  dbf.getAllPersons();
       
        return gson.toJson(p);

    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(String content) {
    Person newPerson = gson.fromJson(content, Person.class);
    dbf.createPerson(newPerson);
    
    return content;
   
    }
    
  
    
        
    @POST
   // @Path("/add")
   // @Consumes(MediaType.APPLICATION_JSON)
    public void postTest(@FormParam("fname") String fname,@FormParam("lname") String lname,@FormParam("phone") String phone) {
  
        System.out.println("post form is called. name: "+fname+ " "+lname+" "+phone);
    
    dbf.createPerson(new Person(fname,lname,phone));
    
   // return Response.status(200)
     //       .entity("post form is called. name: "+name).build();
   
    }
   

    

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
    Person newPerson = gson.fromJson(content, Person.class);
    dbf.updatePerson(newPerson);
        System.out.println("update"+newPerson.toString());
        return gson.toJson(newPerson);
    }
    
    
    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteJson(@PathParam("id") int id) {
    dbf.deletePerson(id);
    
        
    }    
    
    
    
    
}
