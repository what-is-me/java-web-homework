package per.whatisme.servlets;

import per.whatisme.beans.Customer;
import per.whatisme.mapper.CustomerMapper;
import per.whatisme.tools.Transfer;
import per.whatisme.tools.UTF8Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customer")
public class CustomerServlet extends UTF8Servlet {
    CustomerMapper customerMapper = new CustomerMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String id = req.getParameter("id");
        if (id == null)
            out.println(Transfer.toJson(customerMapper.select()));
        else out.println(Transfer.toJson(customerMapper.select(id)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        Customer customer = new Customer();
        customer.setId(req.getParameter("id"));
        customer.setMoney(Double.valueOf(req.getParameter("money")));
        customer.setName(req.getParameter("name"));
        customer.setEmail(req.getParameter("email"));
        try {
            customerMapper.add(customer);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        try {
            String id = req.getParameter("id");
            customerMapper.delete(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
