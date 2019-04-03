package app.servlets;

import app.entities.Employee;
import app.scripts.EmployeeScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;

public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String emailFilter = req.getParameter("email");
        String addressFilter = req.getParameter("address");
        String postFilter = req.getParameter("postid");
        String departmentFilter = req.getParameter("departmentid");
        String headFilter = req.getParameter("head");

        if (postFilter != null) {
            resp.sendRedirect(req.getContextPath() + "/list?PostId=" + postFilter + "&Name=" + nameFilter + "&Email=" + emailFilter + "&Address=" + addressFilter + "&DepartmentId=" + departmentFilter + "&Head=" + headFilter);
        } else {
            EmployeeScripts es = new EmployeeScripts();
            es.deleteEmployee(employeeId);

            resp.sendRedirect(req.getContextPath() + "/list");
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
        String emailFilterString = req.getParameter("Email");
        if (!(emailFilterString == "" || emailFilterString == null)) {
            args.add("email");
            params.add(emailFilterString);
        }
        String addressFilterString = req.getParameter("Address");
        if (!(addressFilterString == "" || addressFilterString == null)) {
            args.add("address");
            params.add(addressFilterString);
        }
        String postIdFilterString = req.getParameter("PostId");
        if (!(postIdFilterString == "" || postIdFilterString == null)) {
            args.add("postId");
            params.add(postIdFilterString);
        }
        String departmentIdFilterString = req.getParameter("DepartmentId");
        if (!(departmentIdFilterString == "" || departmentIdFilterString == null)) {
            args.add("departmentId");
            params.add(departmentIdFilterString);
        }
        String headFilterString = req.getParameter("Head");
        if (!(headFilterString == "" || headFilterString == null)) {
            args.add("head");
            params.add(headFilterString);
        }


        EmployeeScripts es = new EmployeeScripts();
        ArrayList<Employee> employees = es.selectFilterEmployees(params, args);

        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
