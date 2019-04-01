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

public class DetailPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("PostId");

        PostScripts ps = new PostScripts();
        ps.UpdatePost(req);

        resp.sendRedirect(req.getContextPath() + "/detailPost?PostId="+postId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostScripts ps = new PostScripts();
        Post post = ps.SelectOnePost(req);

        req.setAttribute("Post", post);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/detailPost.jsp");
        requestDispatcher.forward(req, resp);
    }


}
