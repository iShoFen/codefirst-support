package fr.iut.uca.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/surveys/{survey_id}/feedbacks")
public class FeedbackController {
    @PathParam("survey_id")
    private String surveyId;

    @GET
    public Response getAll() {
        System.out.println("/surveys/" + surveyId + "/feedbacks: getAll");
        return Response.ok().build();
    }

    @POST
    public Response create() {
        System.out.println("/surveys/" + surveyId + "/feedbacks: create");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{feedback_id}")
    public Response delete(@PathParam("feedback_id") int feedbackId) {
        System.out.println("/surveys/" + surveyId + "/feedbacks/{id}: delete("+feedbackId+")");
        return Response.ok().build();
    }
}
