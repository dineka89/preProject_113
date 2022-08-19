package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 35);
        userDaoJDBC.saveUser("Stepan", "Stepanov", (byte) 53);
        userDaoJDBC.saveUser("Sdfe", "Gaddgads", (byte) 123);
        userDaoJDBC.saveUser("GDFda", "ddsf22", (byte) 0);

        List<User> userList = userDaoJDBC.getAllUsers();

        userList.stream().forEach(e-> System.out.println(e.toString()));

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();
    }
}
