package fr.iut.uca.controller;

import fr.iut.uca.dto.issues.issue.IssueDTO;
import fr.iut.uca.dto.issues.issue.IssueDetailDTO;
import fr.iut.uca.dto.issues.issue.IssueInsertDTO;
import fr.iut.uca.dto.issues.issue.IssueUpdateDTO;
import fr.iut.uca.model.issues.Issue;
import fr.iut.uca.service.IssueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static fr.iut.uca.extension.issues.IssueExtensions.issueToDetailDTO;
import static fr.iut.uca.extension.issues.IssueExtensions.issuesToDTOs;

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
    public Response create(IssueInsertDTO issueInsertDTO) {
        System.out.println("/issues: create");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, IssueUpdateDTO issueUpdateDTO) {
        System.out.println("/issues/{id}: update(" + id + ")");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        System.out.println("/issues/{id}: delete(" + id + ")");
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") int id) {
        System.out.println("/issues/{id}/status: updateStatus(" + id + ")");
        return Response.ok().build();
    }
}
