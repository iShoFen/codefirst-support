package fr.iut.uca.v1.controller;

import fr.iut.uca.dto.issues.issue.*;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.v1.dto.issues.issue.*;
import fr.iut.uca.v1.model.issues.Issue;
import fr.iut.uca.v1.service.IssueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.iut.uca.v1.extension.issues.IssueExtensions.issueToDetailDTO;
import static fr.iut.uca.v1.extension.issues.IssueExtensions.issuesToDTOs;

@Path("/issues")
public class IssueController {

    @Inject
    IssueService issueService;

    @GET
    public Response getAll(@QueryParam("index") int index,
                           @QueryParam("count") int count) {
        try {
            List<Issue> issues = issueService.getAll(index, count);
            List<IssueDTO> response = issuesToDTOs(issues);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") String id) {
        try {
            Issue issue = issueService.getOne(id);
            IssueDetailDTO issueDTO = issueToDetailDTO(issue);
            return Response.ok(issueDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response create(@RequestBody(required = true) IssueInsertDTO issueInsertDTO) {
        try {
            // map the fields received to map
            Map<String, String> fields = new HashMap<>();
            for (IssueFieldInsertDTO field : issueInsertDTO.fields()) {
                fields.put(field.title(), field.value());
            }

            Issue issue = issueService.create(issueInsertDTO.title(), issueInsertDTO.author(), issueInsertDTO.createdAt(), issueInsertDTO.modelId(), fields);
            IssueDetailDTO issueDetailDTO = issueToDetailDTO(issue);
            return Response.ok(issueDetailDTO).build();
        } catch (IllegalArgumentException | InsertException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, IssueUpdateDTO issueUpdateDTO) {
        System.out.println("/issues/{id}: update(" + id + ")");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            issueService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") String id) {
        System.out.println("/issues/{id}/status: updateStatus(" + id + ")");
        return Response.ok().build();
    }
}
