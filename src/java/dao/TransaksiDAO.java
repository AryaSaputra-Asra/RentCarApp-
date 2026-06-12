package dao;

import koneksi.Koneksi;
import model.Transaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    // Ambil semua transaksi + join nama mobil & customer
    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.*, m.nama_mobil, c.nama_customer " +
                      "FROM transaksi t " +
                      "JOIN mobil m ON t.id_mobil = m.id_mobil " +
                      "JOIN customer c ON t.id_customer = c.id_customer " +
                      "ORDER BY t.id_transaksi DESC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaksi t = new Transaksi();
                t.setIdTransaksi(rs.getInt("id_transaksi"));
                t.setIdMobil(rs.getInt("id_mobil"));
                t.setIdCustomer(rs.getInt("id_customer"));
                t.setTglSewa(rs.getDate("tgl_sewa"));
                t.setTglKembali(rs.getDate("tgl_kembali"));
                t.setTotalBayar(rs.getInt("total_bayar"));
                t.setStatus(rs.getString("status"));
                t.setNamaMobil(rs.getString("nama_mobil"));
                t.setNamaCustomer(rs.getString("nama_customer"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllTransaksi: " + e.getMessage());
        }
        return list;
    }

    // Tambah transaksi sewa baru
    public boolean tambahTransaksi(Transaksi t) {
        String sql = "INSERT INTO transaksi (id_mobil, id_customer, tgl_sewa, total_bayar, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getIdMobil());
            ps.setInt(2, t.getIdCustomer());
            ps.setDate(3, t.getTglSewa());
            ps.setInt(4, t.getTotalBayar());
            ps.setString(5, "Disewa");

            boolean sukses = ps.executeUpdate() > 0;

            if (sukses) {
                updateStatusMobil(conn, t.getIdMobil(), "Disewa");
            }

            return sukses;
        } catch (SQLException e) {
            System.out.println("Error tambahTransaksi: " + e.getMessage());
            return false;
        }
    }

    // Proses pengembalian mobil
    public boolean prosesPengembalian(int idTransaksi, int idMobil, Date tglKembali) {
        String sql = "UPDATE transaksi SET tgl_kembali=?, status=? WHERE id_transaksi=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, tglKembali);
            ps.setString(2, "Selesai");
            ps.setInt(3, idTransaksi);

            boolean sukses = ps.executeUpdate() > 0;

            if (sukses) {
                updateStatusMobil(conn, idMobil, "Tersedia");
            }

            return sukses;
        } catch (SQLException e) {
            System.out.println("Error prosesPengembalian: " + e.getMessage());
            return false;
        }
    }

    // Helper update status mobil
    private void updateStatusMobil(Connection conn, int idMobil, String status) throws SQLException {
        String sql = "UPDATE mobil SET status=? WHERE id_mobil=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, idMobil);
            ps.executeUpdate();
        }
    }

    // Hapus transaksi
    public boolean hapusTransaksi(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTransaksi);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error hapusTransaksi: " + e.getMessage());
            return false;
        }
    }

    // Ambil transaksi by id
    public Transaksi getTransaksiById(int idTransaksi) {
        Transaksi t = null;
        String sql = "SELECT t.*, m.nama_mobil, c.nama_customer " +
                      "FROM transaksi t " +
                      "JOIN mobil m ON t.id_mobil = m.id_mobil " +
                      "JOIN customer c ON t.id_customer = c.id_customer " +
                      "WHERE t.id_transaksi=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTransaksi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new Transaksi();
                t.setIdTransaksi(rs.getInt("id_transaksi"));
                t.setIdMobil(rs.getInt("id_mobil"));
                t.setIdCustomer(rs.getInt("id_customer"));
                t.setTglSewa(rs.getDate("tgl_sewa"));
                t.setTglKembali(rs.getDate("tgl_kembali"));
                t.setTotalBayar(rs.getInt("total_bayar"));
                t.setStatus(rs.getString("status"));
                t.setNamaMobil(rs.getString("nama_mobil"));
                t.setNamaCustomer(rs.getString("nama_customer"));
            }
        } catch (SQLException e) {
            System.out.println("Error getTransaksiById: " + e.getMessage());
        }
        return t;
    }

    // Untuk laporan: total pendapatan dari transaksi selesai
    public int getTotalPendapatan() {
        int total = 0;
        String sql = "SELECT SUM(total_bayar) AS total FROM transaksi WHERE status='Selesai'";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error getTotalPendapatan: " + e.getMessage());
        }
        return total;
    }

    // Hitung jumlah transaksi per status
    public int getJumlahTransaksi(String status) {
        int jumlah = 0;
        String sql = "SELECT COUNT(*) AS jumlah FROM transaksi WHERE status=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                jumlah = rs.getInt("jumlah");
            }
        } catch (SQLException e) {
            System.out.println("Error getJumlahTransaksi: " + e.getMessage());
        }
        return jumlah;
    }
}