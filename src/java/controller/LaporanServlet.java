package controller;

import dao.TransaksiDAO;
import model.Transaksi;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LaporanServlet", urlPatterns = {"/LaporanServlet"})
public class LaporanServlet extends HttpServlet {

    private TransaksiDAO transaksiDAO = new TransaksiDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Transaksi> listTransaksi = transaksiDAO.getAllTransaksi();
        int totalPendapatan = transaksiDAO.getTotalPendapatan();
        int jumlahSelesai = transaksiDAO.getJumlahTransaksi("Selesai");
        int jumlahDisewa = transaksiDAO.getJumlahTransaksi("Disewa");

        request.setAttribute("listTransaksi", listTransaksi);
        request.setAttribute("totalPendapatan", totalPendapatan);
        request.setAttribute("jumlahSelesai", jumlahSelesai);
        request.setAttribute("jumlahDisewa", jumlahDisewa);

        request.getRequestDispatcher("laporan.jsp").forward(request, response);
    }
}