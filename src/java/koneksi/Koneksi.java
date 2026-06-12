package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/rentcar_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi database berhasil!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }

    // Untuk testing koneksi langsung
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Berhasil terhubung ke database rentcar_db");
        } else {
            System.out.println("Gagal terhubung ke database");
        }
    }
}