package fr.iut.uca.controller;

import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.model.issues.Category;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.extension.issues.CategoryExtensions.categoriesToDTOs;

@Path("/categories")
public class CategoryController {

    @GET
    public Response getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("bug"));
        categories.add(new Category("feature"));
        List<CategoryDTO> categoryDTOS = categoriesToDTOs(categories);
        return Response.ok(categoryDTOS).build();
    }
}
