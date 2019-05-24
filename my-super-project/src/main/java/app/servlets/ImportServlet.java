package app.servlets;

import app.beans.CheckValidation;
import app.beans.PostSessionBeanLocal;
import app.entities.Department;
import app.entities.Employee;
import app.entities.Post;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class ImportServlet extends HttpServlet {

    @EJB
    private PostSessionBeanLocal psb;

    private static final long serialVersionUID = 1L;
    public static final String UPLOAD_DIR = "uploadedFiles";
    public static final String SAVE_DIRECTORY = "uploadDir";
    public static final String XSD_DIRECTORY = "xsdDir";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/import.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);

//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/import.jsp");
//        dispatcher.forward(request, response);

    }


    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("START");

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

        // Creates the save directory if it does not exists
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        System.out.println("START PROCESS");

        // Part list (multi files).
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                String filePath = fullSavePath + File.separator + fileName;
                System.out.println("Write attachment to file: " + filePath);
                // Write to file
                part.write(filePath);


                String type = "nothing";


                File initialFile = new File(filePath);
                InputStream xml = new FileInputStream(initialFile);

                String path = new String(appPath+"/"+XSD_DIRECTORY);
                File initialFile2 = new File(path+File.separator+"employee.xsd");
                File initialFile3 = new File(path+File.separator+"post.xsd");
                File initialFile4 = new File(path+File.separator+"department.xsd");

                InputStream xsd = new FileInputStream(initialFile2);

//                System.out.println("файлы");

                try {


                    //            String xsdS = new XsdGen().parse(initialFile).toString();
                    //            System.out.println(xsdS);
                    //            InputStream xsd3;
                    //            xsd3.



//                    System.out.println(CheckValidation.validateAgainstXSD(xml, xsd));

//                    InputStream xml = FileInputStream(initialFile);
//                    InputStream xsd = FileInputStream(initialFile2);
//
//                    System.out.println(CheckValidation.validateAgainstXSD(xml1, xsd1));
//
//                    xml.close();
//                    xsd.close();
//                    InputStream xml1 = new FileInputStream(initialFile);
//                    InputStream xsd1 = new FileInputStream(initialFile2);

//                    if(CheckValidation.validateAgainstXSD(xml,xsd) == true){
//                        System.out.println("123");
//                    }

                    if(CheckValidation.validateAgainstXSD(xml,xsd)){
                        type = "employee";
                    }
                    xml.close();
                    xsd.close();
                    InputStream xmlp = new FileInputStream(initialFile);
                    InputStream xsdp = new FileInputStream(initialFile3);
                    if(CheckValidation.validateAgainstXSD(xmlp,xsdp)){
                        type = "post";
                    }
                    xml.close();
                    xsd.close();
                    InputStream xmld = new FileInputStream(initialFile);
                    InputStream xsdd = new FileInputStream(initialFile4);
                    if(CheckValidation.validateAgainstXSD(xmld,xsdd)){
                        type = "department";
                    }


                    if(type.equals("post")){

                        System.out.println("true");

                        System.out.println("Something else");
                        Post importPost = psb.importFromXml(filePath);
                        int importPostId = importPost.getPostId();
                        ArrayList<Post> posts = psb.getBeans();
                        int exist = 0;
//                        while(exist > -1){
//
//                        }

                        for (Post post:posts) {
                            System.out.println("1");
                            if(post.getPostId() == importPostId){
                                exist = 1;
                            }
                        }

                        if(exist == 1){
                            System.out.println("Всё плохо");

//                            psb.editPost(importPost, "edit");

                            response.sendRedirect(request.getContextPath() + "/error?fileName=" + fileName + "&type=" + type);

//                            request.setAttribute("filePath", filePath);
//                            RequestDispatcher dispatcher = request.getRequestDispatcher("views/error.jsp");
//                            request.setAttribute("filePath", filePath);
//                            dispatcher.forward(request, response);


                        }
                        else{
                            System.out.println("Всё хорошо");
                            psb.editPost(importPost, "add");
                            response.sendRedirect(request.getContextPath() + "/");
                        }
                    }
                    else{
                        if(type.equals("employee")){
                            System.out.println("true");
                            Employee importEmployee = psb.importFromXml(filePath);
                            int importEmployeeId = importEmployee.getEmployeeId();
                            ArrayList<Employee> employees = psb.getEmployees();
                            int exist = 0;
                            for (Employee employee:employees) {
                                System.out.println("1");
                                if(employee.getEmployeeId() == importEmployeeId){
                                    exist = 1;
                                }
                            }
                            if(exist == 1){
                                System.out.println("Всё плохо");
                                response.sendRedirect(request.getContextPath() + "/error?fileName=" + fileName + "&type=" + type);
                            }
                            else{
                                System.out.println("Всё хорошо");
                                psb.editEmployee(importEmployee, "add");
                                response.sendRedirect(request.getContextPath() + "/");
                            }
                        }
                        else {
                            if(type.equals("department")){
                                System.out.println("true");
                                Department importDepartment = psb.importFromXml(filePath);
                                int importDepartmentId = importDepartment.getDepartmentId();
                                ArrayList<Department> departments = psb.getDepartments();
                                int exist = 0;
                                for (Department department:departments) {
                                    System.out.println("1");
                                    if(department.getDepartmentId() == importDepartmentId){
                                        exist = 1;
                                    }
                                }
                                if(exist == 1){
                                    System.out.println("Всё плохо");
                                    response.sendRedirect(request.getContextPath() + "/error?fileName=" + fileName + "&type=" + type);
                                }
                                else{
                                    System.out.println("Всё хорошо");
                                    psb.editDepartment(importDepartment, "add");
                                    response.sendRedirect(request.getContextPath() + "/");
                                }
                            }
                            else {
                                System.out.println("false");
                                response.sendRedirect(request.getContextPath() + "/import");
                            }
                        }
                    }
                }
                catch (Exception er){}
                finally {
                    if (xml != null) {
                        xml.close();
                    }
                    if (xsd != null) {
                        xsd.close();
                    }
                }




            }
        }

        System.out.println("END");
    }



    /***** Helper Method #1 - This Method Is Used To Read The File Names *****/
    private String extractFileName(Part part) {
        String fileName = "",
                contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }

}
