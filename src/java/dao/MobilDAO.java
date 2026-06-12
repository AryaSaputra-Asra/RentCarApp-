package dao;

import koneksi.Koneksi;
import model.Mobil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilDAO {

    // Ambil semua data mobil
    public List<Mobil> getAllMobil() {
        List<Mobil> list = new ArrayList<>();
        String sql = "SELECT * FROM mobil ORDER BY id_mobil DESC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mobil m = new Mobil();
                m.setIdMobil(rs.getInt("id_mobil"));
                m.setNamaMobil(rs.getString("nama_mobil"));
                m.setMerk(rs.getString("merk"));
                m.setTahun(rs.getInt("tahun"));
                m.setPlatNomor(rs.getString("plat_nomor"));
                m.setHargaSewa(rs.getInt("harga_sewa"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllMobil: " + e.getMessage());
        }
        return list;
    }

    // Tambah data mobil
    public boolean tambahMobil(Mobil m) {
        String sql = "INSERT INTO mobil (nama_mobil, merk, tahun, plat_nomor, harga_sewa, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNamaMobil());
            ps.setString(2, m.getMerk());
            ps.setInt(3, m.getTahun());
            ps.setString(4, m.getPlatNomor());
            ps.setInt(5, m.getHargaSewa());
            ps.setString(6, m.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error tambahMobil: " + e.getMessage());
            return false;
        }
    }

    // Update data mobil
    public boolean updateMobil(Mobil m) {
        String sql = "UPDATE mobil SET nama_mobil=?, merk=?, tahun=?, plat_nomor=?, harga_sewa=?, status=? WHERE id_mobil=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNamaMobil());
            ps.setString(2, m.getMerk());
            ps.setInt(3, m.getTahun());
            ps.setString(4, m.getPlatNomor());
            ps.setInt(5, m.getHargaSewa());
            ps.setString(6, m.getStatus());
            ps.setInt(7, m.getIdMobil());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateMobil: " + e.getMessage());
            return false;
        }
    }

    // Hapus data mobil
    public boolean hapusMobil(int idMobil) {
        String sql = "DELETE FROM mobil WHERE id_mobil=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMobil);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error hapusMobil: " + e.getMessage());
            return false;
        }
    }

    // Ambil 1 mobil by id (untuk form edit)
    public Mobil getMobilById(int idMobil) {
        Mobil m = null;
        String sql = "SELECT * FROM mobil WHERE id_mobil=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMobil);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Mobil();
                m.setIdMobil(rs.getInt("id_mobil"));
                m.setNamaMobil(rs.getString("nama_mobil"));
                m.setMerk(rs.getString("merk"));
                m.setTahun(rs.getInt("tahun"));
                m.setPlatNomor(rs.getString("plat_nomor"));
                m.setHargaSewa(rs.getInt("harga_sewa"));
                m.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Error getMobilById: " + e.getMessage());
        }
        return m;
    }
}