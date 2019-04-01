package app.servlets;

import app.scripts.DepartmentScripts;
import app.scripts.EmployeeScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentScripts ds = new DepartmentScripts();
        ds.AddDepartment(req);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from AddServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }
}
