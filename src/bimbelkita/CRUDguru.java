/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbelkita;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import koneksi.koneksi;

/**
 *
 * @author asus
 */
public class CRUDguru {

    private String id, nama, alamat, kelamin, bidangStudi, keyword, foto;
    private int nip, bidang;
    private Date tanggal_lahir;
    private final Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDguru() {
        Connection con = new koneksi().con();
        CRUDkoneksi = con;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public int getNip() {
        return nip;
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

    public void setBidang(String bidangStudi) {
        this.bidangStudi = bidangStudi;
    }

    public String getBidang() {
        return bidangStudi;
    }

    public void setBidangStudi(int bidangStudi) {
        if (bidangStudi == 0) {
            this.bidangStudi = "Bhs. Indonesia";
        } else if (bidangStudi == 1) {
            this.bidangStudi = "Bhs. Inggris";
        } else if (bidangStudi == 2) {
            this.bidangStudi = "Matematika";
        } else if (bidangStudi == 3) {
            this.bidangStudi = "IPA";
        } else if (bidangStudi == 4) {
            this.bidangStudi = "IPS";
        }
    }

    public String getbidangStudi() {
        return bidangStudi;
    }

    public void setBidangIndex(String namaBidang) {
        if (null != namaBidang) {
            switch (namaBidang) {
                case "Bhs. Indonesia":
                    this.bidang = 0;
                    break;
                case "Bhs. Inggris":
                    this.bidang = 1;
                    break;
                case "Matematika":
                    this.bidang = 2;
                    break;
                case "IPA":
                    this.bidang = 3;
                    break;
                case "IPS":
                    this.bidang = 4;
                    break;
                default:
                    break;
            }
        }
    }

    public int getBidangIndex() {
        return bidang;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public ResultSet tampilData(String bidangStudi) {
        CRUDquery = "SELECT * FROM guru WHERE bidang=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, bidangStudi);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("crudguru" + e);
        }
        return CRUDhasil;
    }

    public void simpanData(int id, int nip, String nama, String bidang, String kelamin, String alamat, String tanggal_lahir, String foto) {
        CRUDquery = "INSERT INTO guru (nip,nama,bidang,kelamin,alamat,tanggal_lahir,foto) VALUES (?,?,?,?,?,?,?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, nip);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setString(3, bidang);
            CRUDpsmt.setString(4, kelamin);
            CRUDpsmt.setString(5, alamat);
            CRUDpsmt.setString(6, tanggal_lahir);
            CRUDpsmt.setString(7, foto);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ubahData(int id, int nip, String nama, String bidang, String kelamin, String alamat, String tanggal_lahir, String foto) {
        CRUDquery = "UPDATE guru SET nip=?,nama=?,bidang=?,kelamin=?,alamat=?,tanggal_lahir=?, foto=? WHERE guru_id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, nip);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setString(3, bidang);
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

    public ResultSet fotoGuru(String id) throws SQLException {
        CRUDquery = "SELECT foto FROM guru WHERE guru_id=?";

        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, id);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
        }

        return CRUDhasil;
    }

    public void hapusData(String id) {
        CRUDquery = "DELETE FROM guru WHERE guru_id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, id);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet searchData(String keyword, String bidang) {
        CRUDquery = "SELECT * FROM guru WHERE bidang=? AND (nama LIKE ? OR guru_id LIKE ? OR bidang LIKE ? OR nip LIKE ? ) ";

        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, bidang);
            CRUDpsmt.setString(2, "%" + keyword + "%");
            CRUDpsmt.setString(3, "%" + keyword + "%");
            CRUDpsmt.setString(4, "%" + keyword + "%");
            CRUDpsmt.setString(5, "%" + keyword + "%");

            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
        }
        return CRUDhasil;
    }
}
