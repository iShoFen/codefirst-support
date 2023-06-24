package fr.iut.uca.controller;

import fr.iut.uca.dto.OperatorDTO;
import fr.iut.uca.dto.surveys.survey.SurveyDTO;
import fr.iut.uca.dto.surveys.survey.SurveyInsertDTO;
import fr.iut.uca.dto.surveys.survey.SurveyUpdateDTO;
import fr.iut.uca.model.surveys.Survey;
import fr.iut.uca.service.SurveyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.Date;
import java.util.List;

import static fr.iut.uca.extension.surveys.SurveyExtensions.toDTOs;


@Path("/surveys")
public class SurveyController {

    @Inject
    SurveyService surveyService;

    @GET
    public Response getAll(@QueryParam("index") int index,
                           @QueryParam("count") int count,
                           @QueryParam("created_at") Date createdAt,
                           @QueryParam("published_at") Date publishedAt,
                           @QueryParam("end_at") Date endAt,
                           @QueryParam("end_date") Date endDate,
                           @QueryParam("operator") OperatorDTO operator) {
        try {
            List<Survey> result = surveyService.getAll(index, count, createdAt, publishedAt, endAt, endDate, operator);
            List<SurveyDTO> response = toDTOs(result);

            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") String id) {
        System.out.println("/surveys/{id}: getOne(" + id + ")");
        return Response.ok().build();
    }

    @POST
    public Response create(@RequestBody(required = true) SurveyInsertDTO surveyInsertDTO) {
        System.out.println("/surveys: create");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, SurveyUpdateDTO surveyUpdateDTO) {
        System.out.println("/surveys/{id}: update(" + id + ")");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        System.out.println("/surveys/{id}: delete(" + id + ")");
        return Response.ok().build();
    }
}
