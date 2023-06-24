package fr.iut.uca.v1.controller;

import fr.iut.uca.v1.dto.surveys.feedback.FeedbackInsertDTO;
import fr.iut.uca.v1.dto.surveys.feedback.FeedbackUpdateDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/surveys/{survey_id}/feedbacks")
public class FeedbackController {
    @PathParam("survey_id")
    private String surveyId;

    @GET
    public Response getAll() {
        System.out.println("/surveys/" + surveyId + "/feedbacks: getAll");
        return Response.ok().build();
    }

    @GET
    @Path("/{feedback_id}")
    public Response get(@PathParam("feedback_id") int feedbackId) {
        System.out.println("/surveys/" + surveyId + "/feedbacks/{id}: get("+feedbackId+")");
        return Response.ok().build();
    }

    @POST
    public Response create(@RequestBody(required = true) FeedbackInsertDTO feedbackInsertDTO) {
        System.out.println("/surveys/" + surveyId + "/feedbacks: create");
        return Response.ok().build();
    }

    @PUT
    @Path("/{feedback_id}")
    public Response update(@PathParam("feedback_id") int feedbackId, FeedbackUpdateDTO feedbackUpdateDTO) {
        System.out.println("/surveys/" + surveyId + "/feedbacks: update");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{feedback_id}")
    public Response delete(@PathParam("feedback_id") int feedbackId) {
        System.out.println("/surveys/" + surveyId + "/feedbacks/{id}: delete("+feedbackId+")");
        return Response.ok().build();
    }
}
