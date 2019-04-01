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
//        String employeeId = req.getParameter("employeeId");
        String departmentId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
//        String emailFilter = req.getParameter("email");
        String addressFilter = req.getParameter("address");
//        String postFilter = req.getParameter("postid");
//        String departmentFilter = req.getParameter("departmentid");
//        String headFilter = req.getParameter("head");
//        String password = req.getParameter("pass");

        if(nameFilter != null){
            resp.sendRedirect(req.getContextPath() + "/listDepartment?Name="+nameFilter+"&Address="+addressFilter);
        }
        else {
            DepartmentScripts ds = new DepartmentScripts();
            ds.DeleteDepartment(req);

            resp.sendRedirect(req.getContextPath() + "/listDepartment");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentScripts ds = new DepartmentScripts();
        ArrayList<Department> departments = ds.SelectManyDepartments(req);

        req.setAttribute("departmentsData", departments);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

}
