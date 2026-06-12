<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form Mobil</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        .form-box { background: #fff; padding: 20px; border-radius: 8px; max-width: 400px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box; }
        .btn-simpan { margin-top: 15px; padding: 10px 20px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .btn-batal { margin-top: 15px; padding: 10px 20px; background: #ccc; color: #333; text-decoration: none; border-radius: 4px; display: inline-block; }
    </style>
</head>
<body>
    <div class="form-box">
        <h2>${mobil != null ? "Edit Mobil" : "Tambah Mobil"}</h2>
        <form action="MobilServlet" method="post">
            <input type="hidden" name="idMobil" value="${mobil.idMobil}">

            <label>Nama Mobil</label>
            <input type="text" name="namaMobil" value="${mobil.namaMobil}" required>

            <label>Merk</label>
            <input type="text" name="merk" value="${mobil.merk}" required>

            <label>Tahun</label>
            <input type="number" name="tahun" value="${mobil.tahun}" required>

            <label>Plat Nomor</label>
            <input type="text" name="platNomor" value="${mobil.platNomor}" required>

            <label>Harga Sewa (per hari)</label>
            <input type="number" name="hargaSewa" value="${mobil.hargaSewa}" required>

            <label>Status</label>
            <select name="status">
                <option value="Tersedia" ${mobil.status == 'Tersedia' ? 'selected' : ''}>Tersedia</option>
                <option value="Disewa" ${mobil.status == 'Disewa' ? 'selected' : ''}>Disewa</option>
            </select>

            <br>
            <button type="submit" class="btn-simpan">Simpan</button>
            <a class="btn-batal" href="MobilServlet">Batal</a>
        </form>
    </div>
</body>
</html>