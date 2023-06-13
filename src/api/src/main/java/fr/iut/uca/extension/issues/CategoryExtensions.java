package fr.iut.uca.extension.issues;

import fr.iut.uca.entity.issues.CategoryEntity;
import fr.iut.uca.model.issues.Category;

import java.util.List;

public abstract class CategoryExtensions {

    public static final String NAME = "name";

    private CategoryExtensions() { }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.getName());

        return categoryEntity;
    }

    public static List<CategoryEntity> toEntities(List<Category> categories) {
        return categories.stream().map(CategoryExtensions::toEntity).toList();
    }

    public static Category toModel(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getName());
    }

    public static List<Category> toModels(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(CategoryExtensions::toModel).toList();
    }
}
