package com.acceptic.task.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acceptic.task.model.User;
import com.acceptic.task.repository.UserRepository;
import com.acceptic.task.util.ConnectionHolder;

public class UserRepositorySql extends GenericRepository
        implements UserRepository {

    private static final String CREATE_USER_QUERY = "INSERT INTO user(user_id, username, email, password) values (?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET username=?, email=?, password=? WHERE user_id=?";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id = ?";

    @Override
    public List<User> createUser(User user) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection.prepareStatement(CREATE_USER_QUERY);
            prepStatement.setInt(1, user.getId());
            prepStatement.setString(2, user.getUsername());
            prepStatement.setString(3, user.getEmail());
            prepStatement.setString(4, user.getPassword());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }
        return getUserById(user.getId());
    }

    @Override
    public List<User> updateUser(User user) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection.prepareStatement(UPDATE_USER_QUERY);
            prepStatement.setString(1, user.getUsername());
            prepStatement.setString(2, user.getEmail());
            prepStatement.setString(3, user.getPassword());
            prepStatement.setInt(4, user.getId());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }
        return getUserById(user.getId());
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        return users;
    }

    @Override
    public List<User> getUserById(int userId) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            prepStatement = connection
                    .prepareStatement(SELECT_USER_BY_ID_QUERY);
            prepStatement.setInt(1, userId);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        return users;
    }

    private User createUserFromResultSet(ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreateTime(resultSet.getTimestamp("create_time"));
        return user;
    }

}
