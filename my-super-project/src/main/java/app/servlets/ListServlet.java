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
//        String employeeId = req.getParameter("employeeId");
        String employeeId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String emailFilter = req.getParameter("email");
        String addressFilter = req.getParameter("address");
        String postFilter = req.getParameter("postid");
        String departmentFilter = req.getParameter("departmentid");
        String headFilter = req.getParameter("head");
//        String password = req.getParameter("pass");

        if(postFilter != null){
            resp.sendRedirect(req.getContextPath() + "/list?PostId="+postFilter+"&Name="+nameFilter+"&Email="+emailFilter+"&Address="+addressFilter+"&DepartmentId="+departmentFilter+"&Head="+headFilter);
        }
        else {
            EmployeeScripts es = new EmployeeScripts();
            es.DeleteEmployee(req);

            resp.sendRedirect(req.getContextPath() + "/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeScripts es = new EmployeeScripts();
        ArrayList<Employee> employees = es.SelectManyEmployees(req);

        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
