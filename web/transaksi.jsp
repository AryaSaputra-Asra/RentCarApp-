<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Data Transaksi</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #4CAF50; color: white; }
        tr:hover { background: #f1f1f1; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; margin-right: 5px; border: none; cursor: pointer; }
        .btn-tambah { background: #2196F3; }
        .btn-kembali { background: #009688; }
        .btn-hapus { background: #f44336; }
        .status-disewa { color: #FF9800; font-weight: bold; }
        .status-selesai { color: #4CAF50; font-weight: bold; }
        .top-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="top-bar">
        <h2>Data Transaksi</h2>
        <a class="btn btn-tambah" href="SewaServlet?action=tambah">+ Sewa Mobil</a>
    </div>
     <p><a href="index.jsp">⬅ Kembali ke Menu</a></p>

    <table>
        <tr>
            <th>ID</th>
            <th>Mobil</th>
            <th>Customer</th>
            <th>Tgl Sewa</th>
            <th>Tgl Kembali</th>
            <th>Total Bayar</th>
            <th>Status</th>
            <th>Aksi</th>
        </tr>
        <c:forEach var="t" items="${listTransaksi}">
        <tr>
            <td>${t.idTransaksi}</td>
            <td>${t.namaMobil}</td>
            <td>${t.namaCustomer}</td>
            <td>${t.tglSewa}</td>
            <td>${t.tglKembali != null ? t.tglKembali : '-'}</td>
            <td>Rp ${t.totalBayar}</td>
            <td>
                <c:choose>
                    <c:when test="${t.status == 'Disewa'}">
                        <span class="status-disewa">Disewa</span>
                    </c:when>
                    <c:otherwise>
                        <span class="status-selesai">Selesai</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${t.status == 'Disewa'}">
                    <a class="btn btn-kembali" href="SewaServlet?action=kembalikan&id=${t.idTransaksi}&idMobil=${t.idMobil}" onclick="return confirm('Proses pengembalian mobil ini?')">Kembalikan</a>
                </c:if>
                <a class="btn btn-hapus" href="SewaServlet?action=hapus&id=${t.idTransaksi}" onclick="return confirm('Yakin hapus transaksi ini?')">Hapus</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>