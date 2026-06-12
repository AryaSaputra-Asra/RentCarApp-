package controller;

import dao.TransaksiDAO;
import dao.MobilDAO;
import dao.CustomerDAO;
import model.Transaksi;
import model.Mobil;
import model.Customer;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SewaServlet", urlPatterns = {"/SewaServlet"})
public class SewaServlet extends HttpServlet {

    private TransaksiDAO transaksiDAO = new TransaksiDAO();
    private MobilDAO mobilDAO = new MobilDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "tambah":
                List<Mobil> listMobil = mobilDAO.getAllMobil();
                List<Customer> listCustomer = customerDAO.getAllCustomer();
                request.setAttribute("listMobil", listMobil);
                request.setAttribute("listCustomer", listCustomer);
                request.getRequestDispatcher("formTransaksi.jsp").forward(request, response);
                break;

            case "kembalikan":
                int idTransaksi = Integer.parseInt(request.getParameter("id"));
                int idMobil = Integer.parseInt(request.getParameter("idMobil"));
                Date tglHariIni = new Date(System.currentTimeMillis());
                transaksiDAO.prosesPengembalian(idTransaksi, idMobil, tglHariIni);
                response.sendRedirect("SewaServlet");
                break;

            case "hapus":
                int idHapus = Integer.parseInt(request.getParameter("id"));
                transaksiDAO.hapusTransaksi(idHapus);
                response.sendRedirect("SewaServlet");
                break;

            default:
                List<Transaksi> listTransaksi = transaksiDAO.getAllTransaksi();
                request.setAttribute("listTransaksi", listTransaksi);
                request.getRequestDispatcher("transaksi.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idMobil = Integer.parseInt(request.getParameter("idMobil"));
        int idCustomer = Integer.parseInt(request.getParameter("idCustomer"));
        String tglSewaStr = request.getParameter("tglSewa");
        int lamaSewa = Integer.parseInt(request.getParameter("lamaSewa"));

        Mobil mobil = mobilDAO.getMobilById(idMobil);
        int totalBayar = mobil.getHargaSewa() * lamaSewa;

        Transaksi t = new Transaksi();
        t.setIdMobil(idMobil);
        t.setIdCustomer(idCustomer);
        t.setTglSewa(Date.valueOf(tglSewaStr));
        t.setTotalBayar(totalBayar);

        transaksiDAO.tambahTransaksi(t);

        response.sendRedirect("SewaServlet");
    }
}