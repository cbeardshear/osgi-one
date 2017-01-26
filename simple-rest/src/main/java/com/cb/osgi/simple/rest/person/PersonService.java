package com.cb.osgi.simple.rest.person;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_XML)
public interface PersonService {
    @GET
    @Path("/getAll")
    public Collection<Person> getAll();
     
    @GET
    @Path("/getPerson/{id}")
    public Person getPerson(@PathParam("id") String id);
     
    @PUT
    @Path("/updatePerson/{id}")
    public void updatePerson(@PathParam("id") String id, Person person);
     
    @POST
    @Path("/addPerson")
    public void addPerson(Person person);
}
