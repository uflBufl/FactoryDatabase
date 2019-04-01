package app.servlets;

import app.scripts.EmployeeScripts;
import app.scripts.PostScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostScripts ps = new PostScripts();
        ps.addPost(req);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from AddServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(req, resp);
    }

}
