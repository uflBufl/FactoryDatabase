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
import java.util.ArrayList;

public class DetailDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String addressFilterString = req.getParameter("address");
        if (!(addressFilterString.equals("") || addressFilterString.equals("null"))) {
            args.add("address");
            params.add(addressFilterString);
        }

        String departmentId = req.getParameter("DepartmentId");
        params.add(departmentId);

        DepartmentScripts ds = new DepartmentScripts();
        ds.updateDepartment(params, args);

        resp.sendRedirect(req.getContextPath() + "/detailDepartment?DepartmentId=" + departmentId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("DepartmentId");

        DepartmentScripts ds = new DepartmentScripts();
        Department department = ds.selectDepartmentById(id);

        req.setAttribute("Department", department);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detailDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

}
