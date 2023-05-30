package fr.iut.uca.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/issues")
public class IssueController {

    @GET
    public Response getAll() {
        System.out.println("/issues: getAll");
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") int id) {
        System.out.println("/issues/{id}: getOne(" + id + ")");
        return Response.ok().build();
    }

    @POST
    public Response create() {
        System.out.println("/issues: create");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id) {
        System.out.println("/issues/{id}: update(" + id + ")");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        System.out.println("/issues/{id}: delete(" + id + ")");
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") int id) {
        System.out.println("/issues/{id}/status: updateStatus(" + id + ")");
        return Response.ok().build();
    }
}
