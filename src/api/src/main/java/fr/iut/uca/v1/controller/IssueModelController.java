package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.issues.issuemodel.*;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.v1.service.IssueModelService;
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
 * Issue model controller
 */
@Path("/issues/models")
public class IssueModelController {

    /**
     * Issue model service
     */
    @Inject
    IssueModelService issueModelService;

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(IssueModelController.class);

    /**
     * Get all issue models with pagination and name filter
     * @param getIssueModelDTO filters
     * @return Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all issue models with pagination and name filter")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@BeanParam IssueModelGetDTO getIssueModelDTO) {
        try {
            List<IssueModelDTO> result = issueModelService.getAll(getIssueModelDTO);
            LOG.info("Returning %d issue models".formatted(result.size()));
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid parameters for get all issue models ! Reason : %s".formatted(e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Get one issue model by id
     * @param id issue model id
     * @return Response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one issue model by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getOne(@PathParam("id") String id) {
        try {
            IssueModelDetailDTO result = issueModelService.getOne(id);
            LOG.info("Returning issue model with id %s".formatted(id));
            return Response.ok(result).build();
        } catch (NotFoundException e) {
            LOG.error("Issue model with id %s not found !".formatted(id));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Create a new issue model
     * @param issueModelInsertDTO issue model to create
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new issue model")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) IssueModelInsertDTO issueModelInsertDTO) {
        try {
            IssueModelDetailDTO result = issueModelService.create(issueModelInsertDTO);
            LOG.info("Issue model with id %s created".formatted(result.id()));
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid parameters for create issue model ! Reason : %s".formatted(e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsertException e) {
            LOG.error("An error occurred while creating issue model !");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Delete one issue model by id
     * @param id issue model id
     * @param issueModelUpdateDTO issue model to delete
     * @return Response
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update one issue model by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response update(@PathParam("id") String id, IssueModelUpdateDTO issueModelUpdateDTO) {
        try {
            IssueModelDetailDTO result = issueModelService.update(id, issueModelUpdateDTO);
            LOG.info("Issue model with id %s updated".formatted(id));
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid parameters for update issue model ! Reason : %s".formatted(e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.error("Issue model with id %s not found !".formatted(id));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UpdateException e) {
            LOG.error("An error occurred while updating issue model !");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Delete one issue model by id
     * @param id issue model id
     * @return Response
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete one issue model by id")
    @APIResponse(responseCode = "204", description = "No Content")
    @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@PathParam("id") String id) {
        try {
            issueModelService.delete(id);
            LOG.info("Issue model with id %s deleted".formatted(id));
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.error("Issue model with id %s not found !".formatted(id));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
