package app.servlets;

import app.beans.BeanPost;
import app.beans.PostSessionBean;
import app.beans.PostSessionBeanLocal;
import app.entities.Employee;
import app.entities.Post;
import app.scripts.EmployeeScripts;
import app.scripts.NewClassScripts;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NewTaskServlet extends HttpServlet {
    @EJB
    private PostSessionBeanLocal psb;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PostSessionBean psb = new PostSessionBean();
        System.out.println(psb.getStr());
        ArrayList posts = psb.getBeans();

        NewClassScripts nc = new NewClassScripts();
//        ArrayList<Post> posts = nc.selectPosts();
        ArrayList<Employee> employees = nc.selectEmployees();

        req.setAttribute("employeesData", employees);
        req.setAttribute("postsData", posts);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/newTask.jsp");
        requestDispatcher.forward(req, resp);

    }

}
