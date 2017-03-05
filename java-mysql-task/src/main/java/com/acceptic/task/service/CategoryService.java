package com.acceptic.task.service;

import java.util.List;

import com.acceptic.task.model.Category;
import com.acceptic.task.repository.CategoryRepository;
import com.acceptic.task.repository.sql.CategoryRepositorySql;

public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepositorySql();
    }

    public List<Category> findCategoriesByUserId(int userId) {
        return categoryRepository.findCategoriesByUserId(userId);
    }

    public List<Category> createCategory(Category category) {
        return categoryRepository.createCategory(category);
    }

    public void createCategoryUserRelation(int userId, int categoryId) {
        categoryRepository.createCategoryUserRelation(userId, categoryId);
    }

}
