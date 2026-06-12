<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Rent Car App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
            width: 350px;
        }
        h1 {
            color: #333;
            margin-bottom: 5px;
        }
        p {
            color: #888;
            margin-bottom: 30px;
        }
        .menu a {
            display: block;
            padding: 15px;
            margin-bottom: 12px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: 0.2s;
        }
        .menu a:hover {
            background: #388E3C;
        }
        .menu a.customer { background: #2196F3; }
        .menu a.customer:hover { background: #1769AA; }
        .menu a.sewa { background: #FF9800; }
        .menu a.sewa:hover { background: #E68900; }
        .menu a.laporan { background: #555; }
        .menu a.laporan:hover { background: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚗 Rent Car App</h1>
        <p>Sistem Penyewaan Mobil</p>
        <div class="menu">
            <a href="MobilServlet">Data Mobil</a>
            <a class="customer" href="CustomerServlet">Data Customer</a>
            <a class="sewa" href="SewaServlet">Transaksi Sewa</a>
            <a class="laporan" href="LaporanServlet">Laporan Transaksi</a>
        </div>
    </div>
</body>
</html>