/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class koneksi {

    private Connection koneksi;

    public Connection con() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("berhasil gan");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("gagal" + cnfe);
        }
        String url = "jdbc:mysql://localhost:3306/BimbelKita";
        try {
            koneksi = DriverManager.getConnection(url, "root", "");
            System.out.println("berhasil tersambung");
        } catch (SQLException se) {
            System.out.println("tidak ada database" + se);
        }
        return koneksi;
    }

    public static void main(String[] args) {
        koneksi con = new koneksi();
        con.con();
    }
}
