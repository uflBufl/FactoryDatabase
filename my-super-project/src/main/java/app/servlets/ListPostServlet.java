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

public class ListPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String salaryFilter = req.getParameter("salary");
        String timeFilter = req.getParameter("time");

        if (salaryFilter != null) {
            resp.sendRedirect(req.getContextPath() + "/listPost?Salary=" + salaryFilter + "&Name=" + nameFilter + "&Time=" + timeFilter);
        } else {
            PostScripts ps = new PostScripts();
            ps.deletePost(postId);

            resp.sendRedirect(req.getContextPath() + "/listPost");
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
        String salaryFilterString = req.getParameter("Salary");
        if (!(salaryFilterString == "" || salaryFilterString == null)) {
            args.add("salary");
            params.add(salaryFilterString);
        }
        String timeFilterString = req.getParameter("Time");
        if (!(timeFilterString == "" || timeFilterString == null)) {
            args.add("time");
            params.add(timeFilterString);
        }

        PostScripts ps = new PostScripts();
        ArrayList<Post> posts = ps.selectFilterPosts(params, args);

        req.setAttribute("postsData", posts);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listPost.jsp");
        requestDispatcher.forward(req, resp);
    }

}
