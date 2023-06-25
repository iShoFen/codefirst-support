package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.surveys.feedback.*;
import fr.iut.uca.v1.service.FeedbackService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/surveys/{survey_id}/feedbacks")
public class FeedbackController {
    @Inject
    FeedbackService feedbackService;

    @PathParam("survey_id")
    private String surveyId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all feedbacks from a survey with pagination and filters")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDTO.class)))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@BeanParam FeedbackGetDTO feedbackGetDTO) {
        try {
            List<FeedbackDTO> result = feedbackService.getAll(surveyId, feedbackGetDTO);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{feedback_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a feedback from a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response get(@PathParam("feedback_id") String feedbackId) {
        try {
            FeedbackDetailDTO result = feedbackService.getOne(surveyId, feedbackId);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a feedback for a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) FeedbackInsertDTO feedbackInsertDTO) {
        try {
            FeedbackDetailDTO result = feedbackService.create(surveyId, feedbackInsertDTO);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsertException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{feedback_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a feedback for a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response update(@PathParam("feedback_id") String feedbackId, FeedbackUpdateDTO feedbackUpdateDTO) {
        try {
            FeedbackDetailDTO result = feedbackService.update(surveyId, feedbackId, feedbackUpdateDTO);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{feedback_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a feedback from a survey")
    @APIResponse(responseCode = "204", description = "No content")
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@PathParam("feedback_id") String feedbackId) {
        try {
            feedbackService.delete(surveyId, feedbackId);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
