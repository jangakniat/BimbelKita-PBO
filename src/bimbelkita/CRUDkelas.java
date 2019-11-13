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
public class CRUDkelas {

    private String nama, nama_kelas, kelas, id;
    private int id_kelas, nis, kelas_list = 7, tingkat;
    private final Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDkelas() {
        Connection con = new koneksi().con();
        CRUDkoneksi = con;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public int getNis() {
        return nis;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelasList(int kelas_list) {
        this.kelas_list = kelas_list;
    }

    public int getKelasList() {
        return kelas_list;
    }

    public void setTingkat(int tingkat) {
        if (tingkat == 0) {
            this.tingkat = 7;
        } else if (tingkat == 1) {
            this.tingkat = 8;
        } else if (tingkat == 2) {
            this.tingkat = 9;
        } else {
            this.tingkat = 7;
        }

    }

    public int getTingkat() {
        return tingkat;
    }

    public void setNamaKelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNamaKelas() {
        return nama_kelas;
    }

    public void setID_Kelas(String nama_kelas) {
        CRUDquery = "SELECT * FROM kelas WHERE nama=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama_kelas);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {
                System.out.println(nama_kelas);
                int id_kelas = Integer.parseInt(CRUDhasil.getString("id"));
                this.id_kelas = id_kelas;
            }
        } catch (SQLException e) {
            System.out.println("s" + e);
        }

    }

    public int getID_kelas() {
        return id_kelas;
    }

    public ResultSet tampilKelas(int tingkat) {
        CRUDquery = "SELECT * FROM kelas WHERE tingkat=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, tingkat);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("crudkelas" + e);
        }
        return CRUDhasil;
    }

    public ResultSet tampilListSiswa(int kelas_list) {
        CRUDquery = "SELECT nama,siswa_id FROM siswa WHERE kelas=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, kelas_list);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return CRUDhasil;
    }

    public void simpanData(String nama_kelas, int tingkat) {
        CRUDquery = "INSERT INTO kelas (nama,tingkat)VALUES (?,?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama_kelas);
            CRUDpsmt.setInt(2, tingkat);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "kelas " + nama_kelas + " sudah ada, mohon masukan nama kelas yang lain");
            }
        }
    }

    public ResultSet tampilSiswa(int id_kelas) {
        CRUDquery = "SELECT * FROM siswa WHERE kelas_id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id_kelas);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return CRUDhasil;
    }
}
