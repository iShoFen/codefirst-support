package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.issues.IssueWithStatusDTO;
import fr.iut.uca.v1.dto.issues.issue.*;
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

import java.util.List;

@Path("/issues")
public class IssueController {

    @Inject
    IssueService issueService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all issues with pagination and filters")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@BeanParam IssueGetDTO getIssueDTO) {
        try {
            List<IssueDTO> result = issueService.getAll(getIssueDTO);
            return Response.ok(result).build();
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
            IssueDetailDTO result = issueService.getOne(id);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new issue")
    @APIResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) IssueInsertDTO issueInsertDTO) {
        try {
            IssueDetailDTO result = issueService.create(issueInsertDTO);
            return Response.status(Response.Status.CREATED).entity(result).build();
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
            IssueDetailDTO result = issueService.update(id, issueUpdateDTO);
            return Response.ok(result).build();
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
            IssueDetailDTO result = issueService.updateStatus(id);
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/issues-status")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all issues status and get the number of issues for each status")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueWithStatusDTO.class)))
    public Response getIssuesStatus() {
        IssueWithStatusDTO issuesStatus = issueService.getIssueWithStatus();
        return Response.ok(issuesStatus).build();
    }
}
