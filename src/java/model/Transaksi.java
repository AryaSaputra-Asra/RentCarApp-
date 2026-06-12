package model;

import java.sql.Date;

public class Transaksi {
    private int idTransaksi;
    private int idMobil;
    private int idCustomer;
    private Date tglSewa;
    private Date tglKembali;
    private int totalBayar;
    private String status;

    // Field tambahan untuk tampilan (join)
    private String namaMobil;
    private String namaCustomer;

    public Transaksi() {}

    public Transaksi(int idTransaksi, int idMobil, int idCustomer, Date tglSewa, Date tglKembali, int totalBayar, String status) {
        this.idTransaksi = idTransaksi;
        this.idMobil = idMobil;
        this.idCustomer = idCustomer;
        this.tglSewa = tglSewa;
        this.tglKembali = tglKembali;
        this.totalBayar = totalBayar;
        this.status = status;
    }

    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public int getIdMobil() { return idMobil; }
    public void setIdMobil(int idMobil) { this.idMobil = idMobil; }

    public int getIdCustomer() { return idCustomer; }
    public void setIdCustomer(int idCustomer) { this.idCustomer = idCustomer; }

    public Date getTglSewa() { return tglSewa; }
    public void setTglSewa(Date tglSewa) { this.tglSewa = tglSewa; }

    public Date getTglKembali() { return tglKembali; }
    public void setTglKembali(Date tglKembali) { this.tglKembali = tglKembali; }

    public int getTotalBayar() { return totalBayar; }
    public void setTotalBayar(int totalBayar) { this.totalBayar = totalBayar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNamaMobil() { return namaMobil; }
    public void setNamaMobil(String namaMobil) { this.namaMobil = namaMobil; }

    public String getNamaCustomer() { return namaCustomer; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }
}