package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException, ClassNotFoundException, IOException;

    void dropUsersTable() throws SQLException, ClassNotFoundException, IOException;

    void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException, IOException;

    void removeUserById(long id) throws SQLException, ClassNotFoundException, IOException;

    List<User> getAllUsers() throws SQLException, ClassNotFoundException, IOException;

    void cleanUsersTable() throws SQLException, ClassNotFoundException, IOException;
}
