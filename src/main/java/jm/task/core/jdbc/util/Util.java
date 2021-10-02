package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util  {

    private static final String URL = "jdbc:mysql://localhost:3306/mybdtest?serverTimezone=Europe/Moscow";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "F1133363il@";


    public static Connection getMySQLConnection () throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);

    }
}
