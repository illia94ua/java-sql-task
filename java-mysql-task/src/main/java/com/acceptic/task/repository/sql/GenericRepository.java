package com.acceptic.task.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericRepository {

    protected void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
