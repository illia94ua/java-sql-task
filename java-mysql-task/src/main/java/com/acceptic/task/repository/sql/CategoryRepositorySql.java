package com.acceptic.task.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acceptic.task.model.Category;
import com.acceptic.task.repository.CategoryRepository;
import com.acceptic.task.util.ConnectionHolder;

public class CategoryRepositorySql extends GenericRepository
        implements CategoryRepository {

    private static final String SELECT_USER_CATEGORIES_QUERY = "SELECT * FROM category WHERE category_id IN (SELECT category_id FROM user_to_category WHERE user_id = ?)";
    private static final String CREATE_CATEGORY_QUERY = "INSERT INTO category(category_id, name) values (?, ?)";
    private static final String CREATE_USER_CATEGORY_RELATION_QUERY = "INSERT INTO user_to_category(user_id, category_id) values (?, ?)";
    private static final String SELECT_CATEGORIES_BY_ID_QUERY = "SELECT * FROM category WHERE category_id = ?";

    @Override
    public List<Category> findCategoriesByUserId(int userId) {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection
                    .prepareStatement(SELECT_USER_CATEGORIES_QUERY);
            prepStatement.setInt(1, userId);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Category category = createCategoryFromResultSet(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        return categories;
    }

    @Override
    public List<Category> createCategory(Category category) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection.prepareStatement(CREATE_CATEGORY_QUERY);
            prepStatement.setInt(1, category.getId());
            prepStatement.setString(2, category.getName());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }
        return findCategoryById(category.getId());
    }

    public void createCategoryUserRelation(int userId, int categoryId) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection
                    .prepareStatement(CREATE_USER_CATEGORY_RELATION_QUERY);
            prepStatement.setInt(1, userId);
            prepStatement.setInt(2, categoryId);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }

    }

    @Override
    public List<Category> findCategoryById(int categoryId) {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection
                    .prepareStatement(SELECT_CATEGORIES_BY_ID_QUERY);
            prepStatement.setInt(1, categoryId);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Category category = createCategoryFromResultSet(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        return categories;
    }

    private Category createCategoryFromResultSet(ResultSet resultSet)
            throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("category_id"));
        category.setName(resultSet.getString("name"));
        return category;
    }

}
