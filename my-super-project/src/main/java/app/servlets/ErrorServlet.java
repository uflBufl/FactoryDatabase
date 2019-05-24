package app.servlets;

import app.beans.PostSessionBeanLocal;
import app.entities.DataBase;
import app.entities.Department;
import app.entities.Employee;
import app.entities.Post;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ErrorServlet extends HttpServlet {

    @EJB
    private PostSessionBeanLocal psb;
    private String globalQwe = "";
    private String globalType = "";
    public static final String SAVE_DIRECTORY = "uploadDir";


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        globalQwe = fileName;

        String type = req.getParameter("type");
        globalType = type;



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);

        response.sendRedirect(request.getContextPath() + "/import");
    }


    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = globalQwe;
        String type = globalType;
        System.out.println(fileName);
        System.out.println(type);

        // Gets absolute path to root directory of web app.
        String appPath = request.getServletContext().getRealPath("");
        appPath = appPath.replace('\\', '/');

        // The directory to save uploaded file
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }


        String filePath = fullSavePath + File.separator + fileName;

        if(type.equals("post")) {
            System.out.println("Edit Post");
            Post importPost = psb.importFromXml(filePath);
//        System.out.println(importPost.getName());

            psb.editPost(importPost, "edit");
        }
        else{
            if(type.equals("employee")){
                System.out.println("Edit Employee");
                Employee importEmployee = psb.importFromXml(filePath);
                psb.editEmployee(importEmployee, "edit");
            }
            else{
                if(type.equals("department")){
                    System.out.println("Edit Department");
                    Department importDepartment = psb.importFromXml(filePath);
                    psb.editDepartment(importDepartment, "edit");
                }
            }
        }
    }


}
