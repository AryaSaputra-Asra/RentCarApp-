package controller;

import dao.CustomerDAO;
import model.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "tambah":
                request.getRequestDispatcher("formCustomer.jsp").forward(request, response);
                break;

            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Customer customer = customerDAO.getCustomerById(idEdit);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("formCustomer.jsp").forward(request, response);
                break;

            case "hapus":
                int idHapus = Integer.parseInt(request.getParameter("id"));
                customerDAO.hapusCustomer(idHapus);
                response.sendRedirect("CustomerServlet");
                break;

            default:
                List<Customer> listCustomer = customerDAO.getAllCustomer();
                request.setAttribute("listCustomer", listCustomer);
                request.getRequestDispatcher("customer.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCustomerStr = request.getParameter("idCustomer");
        String namaCustomer = request.getParameter("namaCustomer");
        String noKtp = request.getParameter("noKtp");
        String alamat = request.getParameter("alamat");
        String noTelp = request.getParameter("noTelp");

        Customer customer = new Customer();
        customer.setNamaCustomer(namaCustomer);
        customer.setNoKtp(noKtp);
        customer.setAlamat(alamat);
        customer.setNoTelp(noTelp);

        if (idCustomerStr == null || idCustomerStr.isEmpty()) {
            customerDAO.tambahCustomer(customer);
        } else {
            customer.setIdCustomer(Integer.parseInt(idCustomerStr));
            customerDAO.updateCustomer(customer);
        }

        response.sendRedirect("CustomerServlet");
    }
}