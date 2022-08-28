package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User (id INT primary key NOT NULL AUTO_INCREMENT, user_name VARCHAR(50), last_name VARCHAR(50), age INT)");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS User");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStmt = null;
        Connection connection = null;
        try {
            connection = Util.getMyConnection();
            String sqlInsert = "INSERT INTO User (user_name, last_name, age) VALUES (?,?,?);";
            preparedStmt = connection.prepareStatement(sqlInsert);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setInt(3, age);
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            Connection connection = Util.getMyConnection();
            String sqlRemove = "delete from user where id =?";
            PreparedStatement preparedStmt = connection.prepareStatement(sqlRemove);
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try {
            Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select user_name, last_name, age from User");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User newUser = new User();
                newUser.setName(rs.getString("user_name"));
                newUser.setLastName(rs.getString("last_name"));
                newUser.setAge((byte) rs.getInt("age"));
                resultList.add(newUser);
            }
            connection.close();
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from User");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
