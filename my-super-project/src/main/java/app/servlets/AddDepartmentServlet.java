package app.servlets;

import app.scripts.DepartmentScripts;
import app.scripts.EmployeeScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddDepartmentServlet extends HttpServlet {
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

        DepartmentScripts ds = new DepartmentScripts();
        ds.addDepartment(params, args);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }
}
