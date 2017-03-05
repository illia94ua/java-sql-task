package com.acceptic.task.facade;

import java.util.List;

import com.acceptic.task.model.Category;
import com.acceptic.task.model.User;
import com.acceptic.task.service.CategoryService;
import com.acceptic.task.service.UserService;
import com.acceptic.task.util.DbUtils;

public class UserFacade {

    private UserService userService;
    private CategoryService categoryService;

    public UserFacade() {
        userService = new UserService();
        categoryService = new CategoryService();
    }

    public List<User> createUser(User user) {
        int userId = DbUtils.generateId();
        user.setId(userId);
        userService.createUser(user);
        createCategoriesForUser(user, userId);
        return userService.getUserById(userId);
    }

    public List<User> updateUser(User user) {
        userService.updateUser(user);
        createCategoriesForUser(user, user.getId());
        return userService.getUserById(user.getId());
    }

    public List<User> getAllUsers() {
        List<User> users = userService.findAllUsers();
        fillCategoriesOfUsers(users);
        return users;
    }

    public List<User> getUserById(int userId) {
        List<User> users = userService.getUserById(userId);
        fillCategoriesOfUsers(users);
        return users;
    }

    private void createCategoriesForUser(User user, int userId) {
        for (Category category : user.getCategories()) {
            int categoryId = DbUtils.generateId();
            category.setId(categoryId);
            categoryService.createCategory(category);
            categoryService.createCategoryUserRelation(userId, categoryId);
        }
    }

    private void fillCategoriesOfUsers(List<User> users) {
        for (User user : users) {
            List<Category> categories = categoryService
                    .findCategoriesByUserId(user.getId());
            user.setCategories(
                    categories.toArray(new Category[categories.size()]));
        }
    }

}
