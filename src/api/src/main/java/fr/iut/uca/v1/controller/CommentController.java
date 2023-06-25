package fr.iut.uca.v1.controller;

import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.issues.CommentDTO;
import fr.iut.uca.v1.service.CommentService;
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

import java.util.Date;

/**
 * Comment controller
 */
@Path("/issues/{issue_id}/comments")
public class CommentController {
    /**
     * Issue id
     */
    @PathParam("issue_id")
    private String issueId;

    /**
     * Comment service
     */
    @Inject
    CommentService commentService;

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(CommentController.class);

    /**
     * Create a comment for an issue
     * @param commentDTO Comment to create
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a comment for an issue")
    @APIResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CommentDTO.class)))
    @APIResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    @APIResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Response create(@RequestBody(required = true) CommentDTO commentDTO) {
        try {
            CommentDTO result = commentService.create(issueId, commentDTO);
            LOG.info("Comment created from author %s for issue %s".formatted(commentDTO.author(), issueId));
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (UpdateException e) {
            LOG.error("Error while creating comment from author %s for issue %s".formatted(commentDTO.author(), issueId));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Error while creating comment for issue %s ! Reason : %s".formatted(issueId, e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.error("Error while creating comment, issue %s not found".formatted(issueId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Delete a comment for an issue
     * @param createdAt Comment creation date
     * @param author Comment author
     * @return Response
     */
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
                LOG.error("Missing query parameters ! createdAt and author are required.");
                return Response.status(Response.Status.BAD_REQUEST).entity("Missing query parameters ! createdAt and author are required.").build();
            }

            commentService.delete(issueId, author, createdAt);
            LOG.info("Comment deleted from author %s for issue %s".formatted(author, issueId));
            return Response.noContent().build();
        } catch (UpdateException e) {
            LOG.error("Error while deleting comment from author %s for issue %s".formatted(author, issueId));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.error("Error while deleting comment, issue %s not found".formatted(issueId));
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
