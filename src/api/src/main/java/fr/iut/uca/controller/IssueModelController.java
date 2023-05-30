package fr.iut.uca.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/issues/models")
public class IssueModelController {
    @GET
    public Response getAll() {
        System.out.println("/issues/models: getAll");
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") int id) {
        System.out.println("/issues/models/{id}: getOne(" + id + ")");
        return Response.ok().build();
    }

    @POST
    public Response create() {
        System.out.println("/issues/models: getOne");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id) {
        System.out.println("/issues/models/{id}: update");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        System.out.println("/issues/models/{id}: delete");
        return Response.ok().build();
    }
}
