package app.servlets;

import app.entities.Employee;
import app.scripts.EmployeeScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String employeeId = req.getParameter("EmployeeId");

        EmployeeScripts es = new EmployeeScripts();
        es.UpdateEmployee(req);

        resp.sendRedirect(req.getContextPath() + "/detail?EmployeeId="+employeeId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeScripts es = new EmployeeScripts();
        Employee employee = es.SelectOneEmployee(req);

        req.setAttribute("Employee", employee);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detail.jsp");
        requestDispatcher.forward(req, resp);
    }

}
