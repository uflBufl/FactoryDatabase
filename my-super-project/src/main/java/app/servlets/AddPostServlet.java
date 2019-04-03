package app.servlets;

import app.scripts.EmployeeScripts;
import app.scripts.PostScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String salaryFilterString = req.getParameter("salary");
        if (!(salaryFilterString.equals(""))) {
            args.add("salary");
            params.add(salaryFilterString);
        }
        String timeFilterString = req.getParameter("time");
        if (!(timeFilterString.equals(""))) {
            args.add("time");
            params.add(timeFilterString);
        }

        PostScripts ps = new PostScripts();
        ps.addPost(params, args);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(req, resp);
    }

}
