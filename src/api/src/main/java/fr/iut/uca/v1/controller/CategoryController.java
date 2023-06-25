package fr.iut.uca.v1.controller;

import fr.iut.uca.v1.dto.issues.CategoryDTO;
import fr.iut.uca.v1.dto.issues.issue.IssueDTO;
import fr.iut.uca.v1.model.issues.Category;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.v1.extension.issues.CategoryExtensions.categoriesToDTOs;

@Path("/categories")
public class CategoryController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all categories")
    @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoryDTO.class)))
    public Response getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("bug"));
        categories.add(new Category("feature"));

        List<CategoryDTO> categoryDTOS = categoriesToDTOs(categories);

        return Response.ok(categoryDTOS).build();
    }
}
