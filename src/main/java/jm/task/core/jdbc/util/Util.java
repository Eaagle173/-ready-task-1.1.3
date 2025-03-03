package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "rootroot";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к БД");
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка закрытия соединения: " + e.getMessage());
            }
        }
    }
}
