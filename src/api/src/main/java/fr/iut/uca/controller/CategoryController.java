package fr.iut.uca.controller;

import fr.iut.uca.model.issues.Category;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/api/categories")
public class CategoryController {

    @GET
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("bug"));
        categories.add(new Category("feature"));

        return categories;
    }
}
