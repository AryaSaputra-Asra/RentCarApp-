package controller;

import dao.MobilDAO;
import model.Mobil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MobilServlet", urlPatterns = {"/MobilServlet"})
public class MobilServlet extends HttpServlet {

    private MobilDAO mobilDAO = new MobilDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "tambah":
                request.getRequestDispatcher("formMobil.jsp").forward(request, response);
                break;

            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Mobil mobil = mobilDAO.getMobilById(idEdit);
                request.setAttribute("mobil", mobil);
                request.getRequestDispatcher("formMobil.jsp").forward(request, response);
                break;

            case "hapus":
                int idHapus = Integer.parseInt(request.getParameter("id"));
                mobilDAO.hapusMobil(idHapus);
                response.sendRedirect("MobilServlet");
                break;

            default: // list
                List<Mobil> listMobil = mobilDAO.getAllMobil();
                request.setAttribute("listMobil", listMobil);
                request.getRequestDispatcher("mobil.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idMobilStr = request.getParameter("idMobil");
        String namaMobil = request.getParameter("namaMobil");
        String merk = request.getParameter("merk");
        int tahun = Integer.parseInt(request.getParameter("tahun"));
        String platNomor = request.getParameter("platNomor");
        int hargaSewa = Integer.parseInt(request.getParameter("hargaSewa"));
        String status = request.getParameter("status");

        Mobil mobil = new Mobil();
        mobil.setNamaMobil(namaMobil);
        mobil.setMerk(merk);
        mobil.setTahun(tahun);
        mobil.setPlatNomor(platNomor);
        mobil.setHargaSewa(hargaSewa);
        mobil.setStatus(status);

        if (idMobilStr == null || idMobilStr.isEmpty()) {
            // Tambah data baru
            mobilDAO.tambahMobil(mobil);
        } else {
            // Update data
            mobil.setIdMobil(Integer.parseInt(idMobilStr));
            mobilDAO.updateMobil(mobil);
        }

        response.sendRedirect("MobilServlet");
    }
}