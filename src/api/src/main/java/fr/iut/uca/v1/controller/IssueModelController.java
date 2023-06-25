package fr.iut.uca.v1.controller;

import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelDTO;
import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelDetailDTO;
import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelInsertDTO;
import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelUpdateDTO;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.v1.extension.issues.CategoryExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelFieldExtensions;
import fr.iut.uca.v1.model.issues.Category;
import fr.iut.uca.v1.model.issues.IssueModel;
import fr.iut.uca.v1.model.issues.IssueModelField;
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

import java.util.List;

@Path("/issues/models")
public class IssueModelController {

    @Inject
    IssueModelService issueModelService;

    @GET
    @Produces("application/json")
    @Operation(summary = "Get all issue models with pagination and name filter")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response getAll(@QueryParam("index") int index,
                           @QueryParam("count") int count,
                           @QueryParam("name") String name) {
        try {
            List<IssueModel> issueModels = issueModelService.getAll(index, count, name);
            List<IssueModelDTO> result = IssueModelExtensions.modelsToDTOs(issueModels);

            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Operation(summary = "Get one issue model by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "404", description = "Not Found")
    public Response getOne(@PathParam("id") String id) {
        try {
            IssueModel issueModel = issueModelService.getOne(id);
            IssueModelDetailDTO detailDTO = IssueModelExtensions.modelToDetailDTO(issueModel);
            return Response.ok(detailDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(summary = "Create a new issue model")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) IssueModelInsertDTO issueModelInsertDTO) {
        try {
            IssueModel issueModel = issueModelService.create(issueModelInsertDTO);
            IssueModelDetailDTO issueModelDTO = IssueModelExtensions.modelToDetailDTO(issueModel);

            return Response.ok(issueModelDTO).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsertException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(summary = "Update one issue model by id")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IssueModelDetailDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not Found")
    public Response update(@PathParam("id") String id, IssueModelUpdateDTO issueModelUpdateDTO) {
        try {
            IssueModel issueModel = issueModelService.update(id, issueModelUpdateDTO);
            IssueModelDetailDTO issueModelDTO = IssueModelExtensions.modelToDetailDTO(issueModel);
            return Response.ok(issueModelDTO).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete one issue model by id")
    @APIResponse(responseCode = "204", description = "No Content")
    @APIResponse(responseCode = "404", description = "Not Found")
    public Response delete(@PathParam("id") String id) {
        try {
            issueModelService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
