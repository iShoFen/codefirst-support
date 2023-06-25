package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.surveys.survey.*;
import fr.iut.uca.v1.service.SurveyService;
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


@Path("/surveys")
public class SurveyController {

    @Inject
    SurveyService surveyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all surveys with pagination and filters")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SurveyDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@BeanParam SurveyGetDTO surveyGetDTO) {
        try {
            List<SurveyDTO> result = surveyService.getAll(surveyGetDTO);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one survey by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SurveyDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getOne(@PathParam("id") String id) {
        try {
            SurveyDetailDTO result = surveyService.getOne(id);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SurveyDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) SurveyInsertDTO surveyInsertDTO) {
        try {
            SurveyDetailDTO result = surveyService.create(surveyInsertDTO);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsertException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a survey")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SurveyDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response update(@PathParam("id") String id, SurveyUpdateDTO surveyUpdateDTO) {
        try {
            SurveyDetailDTO result = surveyService.update(id, surveyUpdateDTO);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a survey")
    @APIResponse(responseCode = "204", description = "No Content")
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@PathParam("id") String id) {
        try {
            surveyService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
