package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 35);
        userService.saveUser("Stepan", "Stepanov", (byte) 53);
        userService.saveUser("Sdfe", "Gaddgads", (byte) 123);
        userService.saveUser("GDFda", "ddsf22", (byte) 0);

        List<User> userList = userService.getAllUsers();

        userList.stream().forEach(e-> System.out.println(e.toString()));

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
