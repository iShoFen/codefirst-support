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
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Feedback controller
 */
@Path("/surveys/{survey_id}/feedbacks")
public class FeedbackController {
    /**
     * Feedback service
     */
    @Inject
    FeedbackService feedbackService;

    /**
     * Survey id
     */
    @PathParam("survey_id")
    private String surveyId;

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(FeedbackController.class);

    /**
     * Get all feedbacks from a survey with pagination and filters
     * @param feedbackGetDTO filters
     * @return Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all feedbacks from a survey with pagination and filters")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDTO.class)))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@BeanParam FeedbackGetDTO feedbackGetDTO) {
        try {
            List<FeedbackDTO> result = feedbackService.getAll(surveyId, feedbackGetDTO);
            LOG.info("Get %d feedbacks from survey %s".formatted(result.size(), surveyId));
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            LOG.error("Survey %s not found".formatted(surveyId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Get a feedback from a survey
     * @param feedbackId feedback id
     * @return Response
     */
    @GET
    @Path("/{feedback_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a feedback from a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FeedbackDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response get(@PathParam("feedback_id") String feedbackId) {
        try {
            FeedbackDetailDTO result = feedbackService.getOne(surveyId, feedbackId);
            LOG.info("Get feedback %s from survey %s".formatted(feedbackId, surveyId));
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            LOG.error("Survey %s or feedback %s not found".formatted(surveyId, feedbackId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Create a feedback for a survey
     * @param feedbackInsertDTO feedback to create
     * @return Response
     */
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
            LOG.info("Create feedback %s for survey %s".formatted(result.id(), surveyId));
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            LOG.error("Survey %s not found".formatted(surveyId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Not valid arguments for feedback creation for survey %s ! Reason : %s".formatted(surveyId, e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsertException e) {
            LOG.error("Error while creating feedback %s for survey %s".formatted(feedbackInsertDTO, surveyId));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Update a feedback for a survey
     * @param feedbackId feedback id
     * @param feedbackUpdateDTO feedback to update
     * @return Response
     */
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
            LOG.info("Update feedback %s for survey %s".formatted(feedbackId, surveyId));
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            LOG.error("Survey %s or feedback %s not found".formatted(surveyId, feedbackId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Not valid arguments for feedback update for survey %s ! Reason : %s".formatted(surveyId, e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            LOG.error("Error while updating feedback %s for survey %s".formatted(feedbackId, surveyId));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Delete a feedback from a survey
     * @param feedbackId feedback id
     * @return Response
     */
    @DELETE
    @Path("/{feedback_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a feedback from a survey")
    @APIResponse(responseCode = "204", description = "No content")
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@PathParam("feedback_id") String feedbackId) {
        try {
            feedbackService.delete(surveyId, feedbackId);
            LOG.info("Delete feedback %s from survey %s".formatted(feedbackId, surveyId));
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Survey %s or feedback %s not found".formatted(surveyId, feedbackId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
