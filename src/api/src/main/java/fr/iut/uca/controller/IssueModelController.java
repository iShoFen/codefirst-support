package fr.iut.uca.controller;

import fr.iut.uca.dto.issues.issuemodel.IssueModelDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelDetailDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelInsertDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelUpdateDTO;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.extension.issues.CategoryExtensions;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import fr.iut.uca.extension.issues.IssueModelFieldExtensions;
import fr.iut.uca.model.issues.Category;
import fr.iut.uca.model.issues.IssueModel;
import fr.iut.uca.model.issues.IssueModelField;
import fr.iut.uca.service.IssueModelService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/issues/models")
public class IssueModelController {

    @Inject
    IssueModelService issueModelService;

    @GET
    public Response getAll(@QueryParam("index") int index,
                           @QueryParam("count") int count) {
        try {
            List<IssueModel> issueModels = issueModelService.getAll(index, count);
            List<IssueModelDTO> dtos = IssueModelExtensions.issueModelsToDTOs(issueModels);
            return Response.ok(dtos).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") String id) {
        try {
            IssueModel issueModel = issueModelService.getOne(id);
            IssueModelDetailDTO detailDTO = IssueModelExtensions.issueModelToDetailDTO(issueModel);
            return Response.ok(detailDTO).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response create(@RequestBody(required = true) IssueModelInsertDTO issueModelInsertDTO) {
        try {
            Category category = CategoryExtensions.categoryDTOToModel(issueModelInsertDTO.category());
            List<IssueModelField> fields = IssueModelFieldExtensions.issueModelFieldDTOsToModels(issueModelInsertDTO.fields());
            IssueModel issueModel = issueModelService.create(issueModelInsertDTO.name(), issueModelInsertDTO.shortDescription(), issueModelInsertDTO.description(), category, fields);

            IssueModelDetailDTO issueModelDTO = IssueModelExtensions.issueModelToDetailDTO(issueModel);
            return Response.ok(issueModelDTO).build();
        } catch (IllegalArgumentException | InsertException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, IssueModelUpdateDTO issueModelUpdateDTO) {
        System.out.println("/issues/models/{id}: update");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            issueModelService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
