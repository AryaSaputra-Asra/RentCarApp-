package dao;

import koneksi.Koneksi;
import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer ORDER BY id_customer DESC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setIdCustomer(rs.getInt("id_customer"));
                c.setNamaCustomer(rs.getString("nama_customer"));
                c.setNoKtp(rs.getString("no_ktp"));
                c.setAlamat(rs.getString("alamat"));
                c.setNoTelp(rs.getString("no_telp"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllCustomer: " + e.getMessage());
        }
        return list;
    }

    public boolean tambahCustomer(Customer c) {
        String sql = "INSERT INTO customer (nama_customer, no_ktp, alamat, no_telp) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNamaCustomer());
            ps.setString(2, c.getNoKtp());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getNoTelp());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error tambahCustomer: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer(Customer c) {
        String sql = "UPDATE customer SET nama_customer=?, no_ktp=?, alamat=?, no_telp=? WHERE id_customer=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNamaCustomer());
            ps.setString(2, c.getNoKtp());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getNoTelp());
            ps.setInt(5, c.getIdCustomer());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateCustomer: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusCustomer(int idCustomer) {
        String sql = "DELETE FROM customer WHERE id_customer=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCustomer);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error hapusCustomer: " + e.getMessage());
            return false;
        }
    }

    public Customer getCustomerById(int idCustomer) {
        Customer c = null;
        String sql = "SELECT * FROM customer WHERE id_customer=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCustomer);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Customer();
                c.setIdCustomer(rs.getInt("id_customer"));
                c.setNamaCustomer(rs.getString("nama_customer"));
                c.setNoKtp(rs.getString("no_ktp"));
                c.setAlamat(rs.getString("alamat"));
                c.setNoTelp(rs.getString("no_telp"));
            }
        } catch (SQLException e) {
            System.out.println("Error getCustomerById: " + e.getMessage());
        }
        return c;
    }
}