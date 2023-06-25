package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.CategoryDTO;
import fr.iut.uca.v1.entity.issues.CategoryEntity;
import fr.iut.uca.v1.model.issues.Category;

import java.util.ArrayList;
import java.util.List;

public abstract class CategoryExtensions {

    public static final String NAME = "name";

    private CategoryExtensions() {
    }

    public static CategoryEntity categoryToEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.getName());

        return categoryEntity;
    }

    public static List<CategoryEntity> toEntities(List<Category> categories) {
        return categories.stream().map(CategoryExtensions::categoryToEntity).toList();
    }

    public static Category toModel(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getName());
    }

    public static List<Category> toModels(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(CategoryExtensions::toModel).toList();
    }

    public static CategoryDTO categoryToDTO(Category category) {
        return new CategoryDTO(
                category.getName()
        );
    }

    public static List<CategoryDTO> categoriesToDTOs(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.forEach(category -> categoryDTOS.add(categoryToDTO(category)));
        return categoryDTOS;
    }

    public static Category categoryDTOToModel(CategoryDTO category) {
        return new Category(
                category.name()
        );
    }
}
