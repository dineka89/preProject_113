package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS User (id INT primary key NOT NULL AUTO_INCREMENT, user_name VARCHAR(50), last_name VARCHAR(50), age INT)";
        statement.executeUpdate(sqlCreateTable);
        connection.close();
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement();
        String sqlCreateTable = "DROP TABLE IF EXISTS User";
        statement.executeUpdate(sqlCreateTable);
        connection.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMyConnection();
        String sqlInsert = "INSERT INTO User (user_name, last_name, age) VALUES (?,?,?);";
        PreparedStatement preparedStmt = connection.prepareStatement(sqlInsert);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, lastName);
        preparedStmt.setInt(3, age);
        preparedStmt.execute();
        connection.close();
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMyConnection();
        String sqlRemove = "delete from user where id =?";
        PreparedStatement preparedStmt = connection.prepareStatement(sqlRemove);
        preparedStmt.setLong(1, id);
        preparedStmt.execute();
        connection.close();
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> resultList = new ArrayList<>();
        Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement();
        String sql = "select user_name, last_name, age from User";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            User newUser = new User();
            newUser.setName(rs.getString("user_name"));
            newUser.setLastName(rs.getString("last_name"));
            newUser.setAge((byte) rs.getInt("age"));
            resultList.add(newUser);
        }
        connection.close();
        return resultList;
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from User";
        statement.executeUpdate(sql);
        connection.close();
    }


}
