<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form Sewa Mobil</title>
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
        <h2>Sewa Mobil</h2>
        <form action="SewaServlet" method="post">

            <label>Pilih Mobil</label>
            <select name="idMobil" required>
                <option value="">-- Pilih Mobil --</option>
                <c:forEach var="m" items="${listMobil}">
                    <c:if test="${m.status == 'Tersedia'}">
                        <option value="${m.idMobil}">${m.namaMobil} (${m.merk}) - Rp ${m.hargaSewa}/hari</option>
                    </c:if>
                </c:forEach>
            </select>

            <label>Pilih Customer</label>
            <select name="idCustomer" required>
                <option value="">-- Pilih Customer --</option>
                <c:forEach var="c" items="${listCustomer}">
                    <option value="${c.idCustomer}">${c.namaCustomer}</option>
                </c:forEach>
            </select>

            <label>Tanggal Sewa</label>
            <input type="date" name="tglSewa" required>

            <label>Lama Sewa (hari)</label>
            <input type="number" name="lamaSewa" min="1" value="1" required>

            <br>
            <button type="submit" class="btn-simpan">Simpan</button>
            <a class="btn-batal" href="SewaServlet">Batal</a>
        </form>
    </div>
</body>
</html>