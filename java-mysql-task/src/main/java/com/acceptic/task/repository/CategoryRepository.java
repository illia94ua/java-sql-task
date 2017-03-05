package com.acceptic.task.repository;

import java.util.List;

import com.acceptic.task.model.Category;

public interface CategoryRepository {

    List<Category> findCategoriesByUserId(int userId);

    List<Category> createCategory(Category category);

    List<Category> findCategoryById(int categoryId);

    void createCategoryUserRelation(int userId, int categoryId);

}
