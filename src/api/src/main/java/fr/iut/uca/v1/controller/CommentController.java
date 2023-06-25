package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.issues.CommentDTO;
import fr.iut.uca.v1.extension.issues.CommentExtensions;
import fr.iut.uca.v1.model.issues.Comment;
import fr.iut.uca.v1.service.CommentService;
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

@Path("/issues/{issue_id}/comments")
public class CommentController {
    @PathParam("issue_id")
    private String issueId;

    @Inject
    CommentService commentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a comment for an issue")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CommentDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) CommentDTO commentDTO) {
        try {
            CommentDTO result = commentService.create(issueId, commentDTO);
            return Response.ok(result).build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a comment for an issue")
    @APIResponse(responseCode = "204", description = "No content")
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response delete(@QueryParam("created_at") Date createdAt, @QueryParam("author") String author) {
        try {
            if (createdAt == null || author == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Missing query parameters ! createdAt and author are required.").build();
            }

            commentService.delete(issueId, author, createdAt);

            return Response.noContent().build();
        } catch (UpdateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
