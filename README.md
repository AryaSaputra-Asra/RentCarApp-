# RentCarApp 🚗

Aplikasi web sederhana untuk penyewaan mobil (Rent Car), dibuat sebagai tugas mata kuliah **Pemrograman Web** - Universitas Pamulang (Pertemuan 17).

## Deskripsi

Aplikasi ini memungkinkan pengelolaan data mobil, customer, serta transaksi penyewaan dan pengembalian mobil, lengkap dengan laporan transaksi.

## Fitur

- **Data Mobil** - tambah, edit, hapus data mobil (nama, merk, tahun, plat nomor, harga sewa, status)
- **Data Customer** - tambah, edit, hapus data customer (nama, no KTP, alamat, no telp)
- **Transaksi Sewa** - proses sewa mobil, status mobil otomatis berubah menjadi "Disewa"
- **Pengembalian** - proses pengembalian mobil, status kembali menjadi "Tersedia"
- **Laporan Transaksi** - ringkasan total pendapatan, jumlah transaksi disewa/selesai, dan rincian semua transaksi (bisa di-print)

## Teknologi

- Java Servlet & JSP
- Apache Tomcat 9
- MySQL (XAMPP)
- HTML, CSS, JSTL
- Arsitektur MVC (Model - View - Controller)

## Struktur Project

```
RentCarApp/
├── src/java/
│   ├── controller/      # Servlet (MobilServlet, CustomerServlet, SewaServlet, LaporanServlet)
│   ├── dao/              # Akses database (MobilDAO, CustomerDAO, TransaksiDAO)
│   ├── model/            # Model data (Mobil, Customer, Transaksi)
│   └── koneksi/          # Koneksi database (Koneksi.java)
└── web/
    ├── index.jsp         # Menu utama
    ├── mobil.jsp / formMobil.jsp
    ├── customer.jsp / formCustomer.jsp
    ├── transaksi.jsp / formTransaksi.jsp
    └── laporan.jsp
```

## Setup Database

1. Jalankan Apache & MySQL di XAMPP
2. Buat database `rentcar_db` di phpMyAdmin
3. Jalankan query SQL berikut untuk membuat tabel:

```sql
CREATE TABLE mobil (
    id_mobil INT AUTO_INCREMENT PRIMARY KEY,
    nama_mobil VARCHAR(50) NOT NULL,
    merk VARCHAR(50) NOT NULL,
    tahun INT NOT NULL,
    plat_nomor VARCHAR(15) NOT NULL,
    harga_sewa INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Tersedia'
);

CREATE TABLE customer (
    id_customer INT AUTO_INCREMENT PRIMARY KEY,
    nama_customer VARCHAR(50) NOT NULL,
    no_ktp VARCHAR(20) NOT NULL,
    alamat VARCHAR(100) NOT NULL,
    no_telp VARCHAR(15) NOT NULL
);

CREATE TABLE transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    id_mobil INT NOT NULL,
    id_customer INT NOT NULL,
    tgl_sewa DATE NOT NULL,
    tgl_kembali DATE,
    total_bayar INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Disewa',
    FOREIGN KEY (id_mobil) REFERENCES mobil(id_mobil),
    FOREIGN KEY (id_customer) REFERENCES customer(id_customer)
);
```

## Cara Menjalankan

1. Clone repository ini
2. Buka project di NetBeans
3. Sesuaikan konfigurasi koneksi database di `src/java/koneksi/Koneksi.java` (default: user `root`, password kosong)
4. Jalankan dengan Apache Tomcat 9
5. Akses `http://localhost:8080/RentCarApp/`

## Penulis

Arya Saputra - NIM 231011403512 - 06TPLM006
Teknik Informatika, Universitas Pamulang
