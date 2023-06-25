package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.dto.issues.IssueStatusDTO;
import fr.iut.uca.v1.dto.issues.issue.*;
import fr.iut.uca.v1.model.issues.Issue;
import fr.iut.uca.v1.service.IssueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.Date;
import java.util.List;

import static fr.iut.uca.v1.extension.issues.IssueExtensions.modelToDetailDTO;
import static fr.iut.uca.v1.extension.issues.IssueExtensions.modelsToDTOs;

@Path("/issues")
public class IssueController {

    @Inject
    IssueService issueService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all issues with pagination and filters")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@QueryParam("index") int index,
                           @QueryParam("count") int count,
                           @QueryParam("status") IssueStatusDTO status,
                           @QueryParam("created_at") Date createdAt,
                           @QueryParam("end_date") Date endDate,
                           @QueryParam("operator")OperatorDTO operator) {
        try {
            List<Issue> issues = issueService.getAll(index, count, status, createdAt, endDate, operator);
            List<IssueDTO> response = modelsToDTOs(issues);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one issue by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getOne(@PathParam("id") String id) {
        try {
            Issue issue = issueService.getOne(id);
            IssueDetailDTO issueDTO = modelToDetailDTO(issue);
            return Response.ok(issueDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new issue")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) IssueInsertDTO issueInsertDTO) {
        try {
            Issue issue = issueService.create(issueInsertDTO);
            IssueDetailDTO issueDetailDTO = modelToDetailDTO(issue);
            return Response.ok(issueDetailDTO).build();
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
    @Operation(summary = "Update an issue")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response update(@PathParam("id") String id, @RequestBody(required = true) IssueUpdateDTO issueUpdateDTO) {
        try {
            Issue issue = issueService.update(id, issueUpdateDTO);
            IssueDetailDTO issueDetailDTO = modelToDetailDTO(issue);
            return Response.ok(issueDetailDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an issue")
    @APIResponse(responseCode = "204", description = "No Content")
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@PathParam("id") String id) {
        try {
            issueService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @PUT
    @Path("/{id}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Switch status of an issue. Opened it if it's closed, closed it if it's opened")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response updateStatus(@PathParam("id") String id) {
        try {
            Issue issue = issueService.updateStatus(id);
            IssueDetailDTO issueDetailDTO = modelToDetailDTO(issue);
            return Response.ok(issueDetailDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
