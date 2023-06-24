package fr.iut.uca.controller;

import fr.iut.uca.dto.issues.CommentDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/issues/{issue_id}/comments")
public class CommentController {
    @PathParam("issue_id")
    private String issueId;

    @POST
    public Response create(@RequestBody(required = true) CommentDTO commentDTO) {
        System.out.println("/issues/" + issueId + "/comments: create");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{comment_id}")
    public Response delete(@PathParam("comment_id") int commentId) {
        System.out.println("/issues/" + issueId + "/comments/{id}: delete(" + commentId + ")");
        return Response.ok().build();
    }
}
