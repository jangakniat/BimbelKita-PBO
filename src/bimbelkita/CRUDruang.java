/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbelkita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi;

/**
 *
 * @author asus
 */
public class CRUDruang {

    private String nama, Id;
    private final Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDruang() {
        Connection con = new koneksi().con();
        CRUDkoneksi = con;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public ResultSet tampilRuang() {
        CRUDquery = "SELECT * FROM ruang";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (SQLException e) {
        }
        return CRUDhasil;
    }

    public void simpanData(String nama) {
        CRUDquery = "INSERT INTO ruang (nama)VALUES (?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "kelas " + nama + " sudah ada, mohon masukan nama kelas yang lain");
            }
        }
    }
}
