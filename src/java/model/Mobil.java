package model;

public class Mobil {
    private int idMobil;
    private String namaMobil;
    private String merk;
    private int tahun;
    private String platNomor;
    private int hargaSewa;
    private String status;

    public Mobil() {}

    public Mobil(int idMobil, String namaMobil, String merk, int tahun, String platNomor, int hargaSewa, String status) {
        this.idMobil = idMobil;
        this.namaMobil = namaMobil;
        this.merk = merk;
        this.tahun = tahun;
        this.platNomor = platNomor;
        this.hargaSewa = hargaSewa;
        this.status = status;
    }

    public int getIdMobil() { return idMobil; }
    public void setIdMobil(int idMobil) { this.idMobil = idMobil; }

    public String getNamaMobil() { return namaMobil; }
    public void setNamaMobil(String namaMobil) { this.namaMobil = namaMobil; }

    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public String getPlatNomor() { return platNomor; }
    public void setPlatNomor(String platNomor) { this.platNomor = platNomor; }

    public int getHargaSewa() { return hargaSewa; }
    public void setHargaSewa(int hargaSewa) { this.hargaSewa = hargaSewa; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}