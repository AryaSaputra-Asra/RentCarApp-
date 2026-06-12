<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Data Customer</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #4CAF50; color: white; }
        tr:hover { background: #f1f1f1; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; margin-right: 5px; }
        .btn-tambah { background: #2196F3; }
        .btn-edit { background: #FF9800; }
        .btn-hapus { background: #f44336; }
        .top-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="top-bar">
        <h2>Data Customer</h2>
        <a class="btn btn-tambah" href="CustomerServlet?action=tambah">+ Tambah Customer</a>
    </div>
     <p><a href="index.jsp">⬅ Kembali ke Menu</a></p>

    <table>
        <tr>
            <th>ID</th>
            <th>Nama Customer</th>
            <th>No KTP</th>
            <th>Alamat</th>
            <th>No Telp</th>
            <th>Aksi</th>
        </tr>
        <c:forEach var="c" items="${listCustomer}">
        <tr>
            <td>${c.idCustomer}</td>
            <td>${c.namaCustomer}</td>
            <td>${c.noKtp}</td>
            <td>${c.alamat}</td>
            <td>${c.noTelp}</td>
            <td>
                <a class="btn btn-edit" href="CustomerServlet?action=edit&id=${c.idCustomer}">Edit</a>
                <a class="btn btn-hapus" href="CustomerServlet?action=hapus&id=${c.idCustomer}" onclick="return confirm('Yakin hapus data ini?')">Hapus</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>