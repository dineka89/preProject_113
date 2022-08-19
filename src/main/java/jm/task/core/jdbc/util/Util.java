package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

public class Util {
    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        // Using Oracle
        // You may be replaced by other Database.
        return MySQLConnUtils.getMySQLConnection();
    }

    //
    // Test Connection ...
    //
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {

        System.out.println("Get connection ... ");

        // Get a Connection object
        Connection conn = Util.getMyConnection();

        System.out.println("Get connection " + conn);

        System.out.println("Done!");
    }
}
