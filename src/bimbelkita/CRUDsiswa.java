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
import java.sql.Date;

import koneksi.koneksi;

/**
 *
 * @author asus
 */
public class CRUDsiswa {

    private String id, nama, alamat, kelamin, keyword, foto;
    private int nis, kelas, kelas_id;
    private Date tanggal_lahir;
    private final Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDsiswa() {
        Connection con = new koneksi().con();
        CRUDkoneksi = con;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public int getNis() {
        return nis;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setTanggal_Lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public Date getTanggal_Lahir() {
        return tanggal_lahir;
    }

    public void setKelas(int kelas) {
        this.kelas = kelas;
    }

    public int getKelas() {
        return kelas;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setkelasId(int tingkat) {
        CRUDquery = "SELECT SUM(jumlah_siswa) as jumlah, id FROM kelas WHERE tingkat=? && jumlah_siswa < 15 GROUP BY nama";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, tingkat);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {
                int kelas_id = CRUDhasil.getInt("id");
                this.kelas_id = kelas_id;
                int jumlahBaru = CRUDhasil.getInt("jumlah");
                System.out.println(jumlahBaru);
                CRUDquery = "UPDATE kelas SET jumlah_siswa=? WHERE id=?";
                CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
                CRUDpsmt.setInt(1, jumlahBaru += 1);
                CRUDpsmt.setInt(2, kelas_id);
                CRUDpsmt.executeUpdate();
                CRUDpsmt.close();
                break;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int getKelasId() {
        return kelas_id;
    }

    public ResultSet tampilData() {
        CRUDquery = "SELECT * FROM siswa";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (SQLException e) {
        }
        return CRUDhasil;
    }

    public void simpanData(int id, int nis, String nama, int kelas, String kelamin, String alamat, String tanggal_lahir, String foto, int kelasId) {
        CRUDquery = "INSERT INTO siswa (nis,nama,kelas,kelamin,alamat,tanggal_lahir,foto,kelas_id) VALUES (?,?,?,?,?,?,?,?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, nis);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setInt(3, kelas);
            CRUDpsmt.setString(4, kelamin);
            CRUDpsmt.setString(5, alamat);
            CRUDpsmt.setString(6, tanggal_lahir);
            CRUDpsmt.setString(7, foto);
            CRUDpsmt.setInt(8, kelasId);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ubahData(int id, int nis, String nama, int kelas, String kelamin, String alamat, String tanggal_lahir, String foto) {
        CRUDquery = "UPDATE siswa SET nis=?,nama=?,kelas=?,kelamin=?,alamat=?,tanggal_lahir=?, foto=? WHERE siswa_id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, nis);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setInt(3, kelas);
            CRUDpsmt.setString(4, kelamin);
            CRUDpsmt.setString(5, alamat);
            CRUDpsmt.setString(6, tanggal_lahir);
            CRUDpsmt.setString(7, foto);
            CRUDpsmt.setInt(8, id);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet fotoSiswa(String id) throws SQLException {
        CRUDquery = "SELECT foto FROM siswa WHERE siswa_id=?";

        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, id);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
        }

        return CRUDhasil;
    }

    public void hapusData(String id) {
        CRUDquery = "DELETE FROM siswa WHERE siswa_id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, id);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet searchData(String keyword) {
        CRUDquery = "SELECT * FROM siswa WHERE nama LIKE ? OR siswa_id LIKE ? OR kelas LIKE ? OR nis LIKE ?";

        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, "%" + keyword + "%");
            CRUDpsmt.setString(2, "%" + keyword + "%");
            CRUDpsmt.setString(3, "%" + keyword + "%");
            CRUDpsmt.setString(4, "%" + keyword + "%");
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
        }
        return CRUDhasil;
    }
}
