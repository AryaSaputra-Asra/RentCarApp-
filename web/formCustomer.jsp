<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form Customer</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        .form-box { background: #fff; padding: 20px; border-radius: 8px; max-width: 400px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box; }
        .btn-simpan { margin-top: 15px; padding: 10px 20px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .btn-batal { margin-top: 15px; padding: 10px 20px; background: #ccc; color: #333; text-decoration: none; border-radius: 4px; display: inline-block; }
    </style>
</head>
<body>
    <div class="form-box">
        <h2>${customer != null ? "Edit Customer" : "Tambah Customer"}</h2>
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="idCustomer" value="${customer.idCustomer}">

            <label>Nama Customer</label>
            <input type="text" name="namaCustomer" value="${customer.namaCustomer}" required>

            <label>No KTP</label>
            <input type="text" name="noKtp" value="${customer.noKtp}" required>

            <label>Alamat</label>
            <input type="text" name="alamat" value="${customer.alamat}" required>

            <label>No Telp</label>
            <input type="text" name="noTelp" value="${customer.noTelp}" required>

            <br>
            <button type="submit" class="btn-simpan">Simpan</button>
            <a class="btn-batal" href="CustomerServlet">Batal</a>
        </form>
    </div>
</body>
</html>