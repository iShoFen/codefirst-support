package fr.iut.uca.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/surveys")
public class SurveyController {

    @GET
    public Response getAll() {
        System.out.println("/surveys: getAll");
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") int id) {
        System.out.println("/surveys/{id}: getOne(" + id + ")");
        return Response.ok().build();
    }

    @POST
    public Response create() {
        System.out.println("/surveys: create");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id) {
        System.out.println("/surveys/{id}: update(" + id + ")");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        System.out.println("/surveys/{id}: delete(" + id + ")");
        return Response.ok().build();
    }
}
