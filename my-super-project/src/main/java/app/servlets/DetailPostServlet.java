package app.servlets;

import app.entities.Employee;
import app.entities.Post;
import app.scripts.EmployeeScripts;
import app.scripts.PostScripts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DetailPostServlet extends HttpServlet {

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

        String postId = req.getParameter("PostId");
        params.add(postId);

        PostScripts ps = new PostScripts();
        ps.updatePost(params, args);

        resp.sendRedirect(req.getContextPath() + "/detailPost?PostId=" + postId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("PostId");

        PostScripts ps = new PostScripts();
        Post post = ps.selectPostById(id);

        req.setAttribute("Post", post);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detailPost.jsp");
        requestDispatcher.forward(req, resp);
    }


}
