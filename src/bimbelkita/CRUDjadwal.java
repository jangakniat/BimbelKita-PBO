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
import java.sql.Time;
import koneksi.koneksi;

/**
 *
 * @author asus
 */
public class CRUDjadwal {

    String nama_kelas, nama_ruang, nama_guru, hari, bidangStudi, jam;
    int tingkat, ruang, id_ruang, jumId, id_kelas, id_guru, id;

    private final Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDjadwal() {
        Connection con = new koneksi().con();
        CRUDkoneksi = con;
    }

    public void setTabId(int id) {
        this.id = id;
    }

    public int getTabId() {
        return id;
    }

    public void setTabHari(String hari) {
        this.hari = hari;
    }

    public String getTabHari() {
        return hari;
    }

    public void setTabGuru(String namaGuru) {
        this.nama_guru = namaGuru;
    }

    public String getTabGuru() {
        return nama_guru;
    }

    public void setTabBidang(String bidang) {
        this.bidangStudi = bidang;
    }

    public void setIdGuru(String nama_guru) {
        CRUDquery = "SELECT * FROM guru WHERE nama=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama_guru);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {
                int id_guru = Integer.parseInt(CRUDhasil.getString("guru_id"));
                this.id_guru = id_guru;
            }
        } catch (SQLException e) {
            System.out.println("s" + e);
        }

    }

    public int getIdGuru() {
        return id_guru;
    }

    public String getTabBidang() {
        return bidangStudi;
    }

    public void setTabKelas(String kelas) {
        this.nama_kelas = kelas;
    }

    public String getTabKelas() {
        return nama_kelas;
    }

    public void setIdKelas(String nama_kelas) {
        CRUDquery = "SELECT * FROM kelas WHERE nama=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama_kelas);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {
                int id_kelas = Integer.parseInt(CRUDhasil.getString("id"));
                this.id_kelas = id_kelas;
            }
        } catch (SQLException e) {
            System.out.println("s" + e);
        }

    }

    public int getIdKelas() {
        return id_kelas;
    }

    public void setTabRuang(String ruang) {
        this.nama_ruang = ruang;
    }

    public String getTabRuang() {
        return nama_ruang;
    }

    public void setTabJam(String jam) {
        this.jam = jam;
    }

    public String getTabJam() {
        return jam;
    }

    public void setNamaKelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNamaKelas() {
        return nama_kelas;
    }

    public void setHari(int hari) {
        if (hari == 0) {
            this.hari = "senin";
        } else if (hari == 1) {
            this.hari = "selasa";
        } else if (hari == 2) {
            this.hari = "rabu";
        } else if (hari == 3) {
            this.hari = "kamis";
        } else if (hari == 4) {
            this.hari = "jumat";
        } else if (hari == 5) {
            this.hari = "sabtu";
        } else if (hari == 6) {
            this.hari = "minggu";
        }
    }

    public String getHari() {
        return hari;
    }

    public void setJam(int jam) {
        if (jam == 0) {
            this.jam = "15:00 - 17:00";
        } else if (jam == 1) {
            this.jam = "18:00 - 20:00";
        }
    }

    public String getJam() {
        return jam;
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

    public void setNamaRuang(String nama_ruang) {
        this.nama_ruang = nama_ruang;
    }

    public String getNamaRuang() {
        return nama_ruang;
    }

    public void setNamaGuru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getNamaGuru() {
        return nama_guru;
    }

    public void setRuang(String nama_ruang) {
        CRUDquery = "SELECT * FROM ruang WHERE nama=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama_ruang);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {

                int id_ruang = Integer.parseInt(CRUDhasil.getString("id"));
                this.id_ruang = id_ruang;
            }
        } catch (SQLException e) {
            System.out.println("s" + e);
        }
    }

    public int getRuang() {
        return id_ruang;
    }

    public void setJumId(String hari, String nama_ruang) {
        CRUDquery = "SELECT COUNT(id) as jumId FROM jadwal WHERE hari=? AND ruang=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, hari);
            CRUDpsmt.setString(2, nama_ruang);
            CRUDhasil = CRUDpsmt.executeQuery();
            while (CRUDhasil.next()) {
                int jumId = CRUDhasil.getInt("jumId");
                this.jumId = jumId;
            }
        } catch (SQLException e) {
            System.out.println("s" + e);
        }
    }

    public int getJumId() {
        return jumId;
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

    public ResultSet tampilJadwal(int ruang, String hari) {
        CRUDquery = "SELECT jadwal.*,guru.nama as namaGuru,guru.bidang as bidangGuru,kelas.nama as namaKelas,ruang.nama as namaRuang "
                + "FROM jadwal JOIN guru ON jadwal.id_guru=guru.guru_id JOIN kelas ON jadwal.id_kelas=kelas.id "
                + "JOIN ruang ON jadwal.ruang=ruang.id WHERE jadwal.ruang=? AND jadwal.hari=? GROUP BY jadwal.id";
        try {

            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, ruang);
            CRUDpsmt.setString(2, hari);
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("crud jadwal" + e);
        }
        return CRUDhasil;
    }

    public ResultSet tampilGuru(String bidang, String jam, String hari, String ruang) {
        CRUDquery = "SELECT DISTINCT guru.nama,guru.* FROM guru LEFT JOIN jadwal ON jadwal.id_guru=guru.guru_id WHERE "
                + "guru.bidang IN(SELECT guru.bidang FROM guru WHERE guru.bidang=?)"
                + " AND guru_id NOT IN (SELECT jadwal.id_guru FROM jadwal"
                + " WHERE jadwal.hari=? AND jadwal.jam=? AND jadwal.ruang=? ) "
                + "AND guru_id NOT IN (SELECT jadwal.id_guru FROM jadwal "
                + "WHERE jadwal.hari=? AND jadwal.jam=? AND not jadwal.ruang=? )";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, bidang);
            CRUDpsmt.setString(2, hari);
            CRUDpsmt.setString(3, jam);
            CRUDpsmt.setString(4, ruang);
            CRUDpsmt.setString(5, hari);
            CRUDpsmt.setString(6, jam);
            CRUDpsmt.setString(7, ruang);
            CRUDhasil = CRUDpsmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("crud guru" + e);
        }
        return CRUDhasil;
    }

    public ResultSet tampilJam(String hari, String ruang) {
        CRUDquery = "SELECT DISTINCT jam FROM jadwal WHERE jam NOT IN "
                + "(SELECT jam FROM jadwal WHERE hari=? AND ruang=?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, hari);
            CRUDpsmt.setString(2, ruang);;
            CRUDhasil = CRUDpsmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("crud" + e);
        }
        return CRUDhasil;
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

    public ResultSet tampilRuang() {
//        CRUDquery = "SELECT ruang.nama,hari,COUNT(ruang) as r FROM jadwal JOIN ruang on ruang.id=jadwal.ruang group by ruang having r < 2 and hari=?";
        CRUDquery = "SELECT * FROM ruang";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (SQLException e) {
        }
        return CRUDhasil;
    }

    public void simpanData(int id_guru, int id_kelas, String ruang, String hari, String jam) {
        CRUDquery = "INSERT INTO jadwal (id_guru,id_kelas,ruang,hari,jam) VALUES (?,?,?,?,?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id_guru);
            CRUDpsmt.setInt(2, id_kelas);
            CRUDpsmt.setString(3, ruang);
            CRUDpsmt.setString(4, hari);
            CRUDpsmt.setString(5, jam);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ubahData(int id, int idGuru, int idkelas, String ruang, String hari, String jam) {
        CRUDquery = "UPDATE jadwal SET id_guru=?,id_kelas=?,ruang=?,hari=?,jam=? WHERE id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, idGuru);
            CRUDpsmt.setInt(2, idkelas);
            CRUDpsmt.setString(3, ruang);
            CRUDpsmt.setString(4, hari);
            CRUDpsmt.setString(5, jam);
            CRUDpsmt.setInt(6, id);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println("ubah data" + e.getMessage());
        }
    }

    public void hapusData(int id) {
        CRUDquery = "DELETE FROM jadwal WHERE id=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
