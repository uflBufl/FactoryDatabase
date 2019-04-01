package app.servlets;

import app.entities.Department;
import app.entities.Employee;
import app.scripts.DepartmentScripts;
import app.scripts.EmployeeScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String departmentId = req.getParameter("DepartmentId");

        DepartmentScripts ds = new DepartmentScripts();
        ds.UpdateDepartment(req);

        resp.sendRedirect(req.getContextPath() + "/detailDepartment?DepartmentId="+departmentId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentScripts ds = new DepartmentScripts();
        Department department = ds.SelectOneDepartment(req);

        req.setAttribute("Department", department);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detailDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

}