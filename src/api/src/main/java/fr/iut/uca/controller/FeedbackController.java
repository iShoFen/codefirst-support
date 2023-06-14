package fr.iut.uca.controller;

import fr.iut.uca.dto.surveys.feedback.FeedbackGetDTO;
import fr.iut.uca.dto.surveys.feedback.FeedbackInsertDTO;
import fr.iut.uca.dto.surveys.feedback.FeedbackUpdateDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/surveys/{survey_id}/feedbacks")
public class FeedbackController {
    @PathParam("survey_id")
    private String surveyId;

    @GET
    public Response getAll(FeedbackGetDTO feedbackGetDTO) {
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
    public Response create(FeedbackInsertDTO feedbackInsertDTO) {
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
