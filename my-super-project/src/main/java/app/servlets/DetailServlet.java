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

public class DetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String emailFilterString = req.getParameter("email");
        if (!(emailFilterString.equals("") || emailFilterString.equals("null"))) {
            args.add("email");
            params.add(emailFilterString);
        }
        String addressFilterString = req.getParameter("address");
        if (!(addressFilterString.equals("") || addressFilterString.equals("null"))) {
            args.add("address");
            params.add(addressFilterString);
        }
        String postIdFilterString = req.getParameter("postid");
        if (!(postIdFilterString.equals(""))) {
            args.add("postId");
            params.add(postIdFilterString);
        }
        String departmentIdFilterString = req.getParameter("departmentid");
        if (!(departmentIdFilterString.equals(""))) {
            args.add("departmentId");
            params.add(departmentIdFilterString);
        }
        String headFilterString = req.getParameter("head");
        if (!(headFilterString.equals("") || headFilterString.equals("0"))) {
            args.add("head");
            params.add(headFilterString);
        }

        String employeeId = req.getParameter("EmployeeId");
        params.add(employeeId);

        EmployeeScripts es = new EmployeeScripts();
        es.updateEmployee(params, args);

        resp.sendRedirect(req.getContextPath() + "/detail?EmployeeId=" + employeeId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("EmployeeId");

        EmployeeScripts es = new EmployeeScripts();
        Employee employee = es.selectEmployeeById(id);

        req.setAttribute("Employee", employee);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detail.jsp");
        requestDispatcher.forward(req, resp);
    }

}
