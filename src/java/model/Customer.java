package model;

public class Customer {
    private int idCustomer;
    private String namaCustomer;
    private String noKtp;
    private String alamat;
    private String noTelp;

    public Customer() {}

    public Customer(int idCustomer, String namaCustomer, String noKtp, String alamat, String noTelp) {
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.noKtp = noKtp;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public int getIdCustomer() { return idCustomer; }
    public void setIdCustomer(int idCustomer) { this.idCustomer = idCustomer; }

    public String getNamaCustomer() { return namaCustomer; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }

    public String getNoKtp() { return noKtp; }
    public void setNoKtp(String noKtp) { this.noKtp = noKtp; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
}