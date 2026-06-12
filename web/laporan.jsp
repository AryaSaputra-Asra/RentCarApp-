<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Laporan Transaksi</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        h2 { color: #333; }
        .summary { display: flex; gap: 20px; margin-bottom: 20px; }
        .card { background: #fff; padding: 20px; border-radius: 8px; flex: 1; text-align: center; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        .card h3 { margin: 0; color: #777; font-size: 14px; }
        .card p { margin: 10px 0 0; font-size: 24px; font-weight: bold; color: #333; }
        .card.pendapatan p { color: #4CAF50; }
        .card.disewa p { color: #FF9800; }
        .card.selesai p { color: #2196F3; }
        table { width: 100%; border-collapse: collapse; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #4CAF50; color: white; }
        tr:hover { background: #f1f1f1; }
        .status-disewa { color: #FF9800; font-weight: bold; }
        .status-selesai { color: #4CAF50; font-weight: bold; }
        .btn-print { padding: 8px 16px; background: #555; color: white; border: none; border-radius: 4px; cursor: pointer; margin-bottom: 15px; }
        @media print {
            .btn-print { display: none; }
        }
    </style>
</head>
<body>
    <h2>Laporan Transaksi Penyewaan Mobil</h2>

    <div class="summary">
        <div class="card pendapatan">
            <h3>Total Pendapatan (Selesai)</h3>
            <p>Rp ${totalPendapatan}</p>
        </div>
        <div class="card disewa">
            <h3>Sedang Disewa</h3>
            <p>${jumlahDisewa}</p>
        </div>
        <div class="card selesai">
            <h3>Transaksi Selesai</h3>
            <p>${jumlahSelesai}</p>
        </div>
    </div>

    <button class="btn-print" onclick="window.print()">Print Laporan</button>
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
        </tr>
        </c:forEach>
    </table>
</body>
</html>