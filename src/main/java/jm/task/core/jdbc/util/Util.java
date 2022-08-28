package jm.task.core.jdbc.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException, IOException {
        return MySQLConnUtils.getMySQLConnection();
    }

    public static Properties getProps() throws IOException {
        FileReader reader = new FileReader("src/database.properties");

        Properties p = new Properties();
        p.load(reader);
        return p;
    }

    //
    // Test Connection ...
    //
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException, IOException {

        System.out.println("Get connection ... ");

        // Get a Connection object
        Connection conn = Util.getMyConnection();

        System.out.println("Get connection " + conn);

        System.out.println("Done!");
    }
}
