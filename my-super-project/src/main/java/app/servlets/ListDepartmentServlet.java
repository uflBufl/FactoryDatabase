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

public class ListDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String addressFilter = req.getParameter("address");

        if (nameFilter != null) {
            resp.sendRedirect(req.getContextPath() + "/listDepartment?Name=" + nameFilter + "&Address=" + addressFilter);
        } else {
            DepartmentScripts ds = new DepartmentScripts();
            ds.deleteDepartment(departmentId);

            resp.sendRedirect(req.getContextPath() + "/listDepartment");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("Name");
        if (!(nameFilterString == "" || nameFilterString == null)) {
            args.add("name");
            params.add(nameFilterString);
        }
        String addressFilterString = req.getParameter("Address");
        if (!(addressFilterString == "" || addressFilterString == null)) {
            args.add("address");
            params.add(addressFilterString);
        }

        DepartmentScripts ds = new DepartmentScripts();
        ArrayList<Department> departments = ds.selectFilterDepartments(params, args);

        req.setAttribute("departmentsData", departments);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

}
