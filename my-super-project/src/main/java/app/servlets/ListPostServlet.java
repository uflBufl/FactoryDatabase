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
//        String employeeId = req.getParameter("employeeId");
        String postId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
//        String emailFilter = req.getParameter("email");
//        String addressFilter = req.getParameter("address");
        String salaryFilter = req.getParameter("salary");
        String timeFilter = req.getParameter("time");
//        String headFilter = req.getParameter("head");
//        String password = req.getParameter("pass");

        if(salaryFilter != null){
            resp.sendRedirect(req.getContextPath() + "/listPost?Salary="+salaryFilter+"&Name="+nameFilter+"&Time="+timeFilter);
        }
        else {
            PostScripts ps = new PostScripts();
            ps.deletePost(req);

            resp.sendRedirect(req.getContextPath() + "/listPost");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        PostScripts ps = new PostScripts();
        ArrayList<Post> posts = ps.selectFilterPosts(req);

        req.setAttribute("postsData", posts);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listPost.jsp");
        requestDispatcher.forward(req, resp);
    }

}
