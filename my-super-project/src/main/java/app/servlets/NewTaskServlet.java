package app.servlets;

import app.beans.BeanPost;
import app.beans.CheckValidation;
import app.beans.PostSessionBean;
import app.beans.PostSessionBeanLocal;
import app.entities.DataBase;
import app.entities.Department;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.wiztools.xsdgen.XsdGen;

public class NewTaskServlet extends HttpServlet {
    @EJB
    private PostSessionBeanLocal psb;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PostSessionBean psb = new PostSessionBean();
        System.out.println(psb.getStr());
        ArrayList posts = psb.getBeans();

        //тест JAXB
//        psb.exportBean((Post)posts.get(0));
//        psb.exportDB((Post)posts.get(0), Post.class);
        Employee e = new Employee(1,"2","3","4",5,6,7);
        Employee e1 = new Employee(1,"2","3","4",5,6,7);
        Employee e2 = new Employee(1,"2","3","4",5,6,7);
        Department d = new Department(1,"2","3");
        Department d1 = new Department(1,"2","3");
        Department d2 = new Department(1,"2","3");
        DataBase db = new DataBase();
//        db.departments.add(d);
//        db.departments.add(d1);
//        db.departments.add(d2);
        db.employees.add(e);
        db.employees.add(e1);
        db.employees.add(e2);
        db.posts.add((Post)posts.get(0));
        db.posts.add((Post)posts.get(1));
        db.posts.add((Post)posts.get(2));

//        psb.exportDB(db, "test");
//        psb.exportDBT(db);




//        String filePath ="C:/Users/gamer/Desktop/xml_файлы_NetCracker/posts.xml";
//        File f = new File(filePath);
//        InputStream xml;

        File initialFile = new File("C:/Users/gamer/Desktop/xml_файлы_NetCracker/testT.xml");
        InputStream xml = new FileInputStream(initialFile);

        File initialFile2 = new File("C:/Users/gamer/Desktop/xml_файлы_NetCracker/db.xsd");
        InputStream xsd = new FileInputStream(initialFile2);

        try {


//            String xsdS = new XsdGen().parse(initialFile).toString();
//            System.out.println(xsdS);
//            InputStream xsd3;
//            xsd3.

            System.out.println(CheckValidation.validateAgainstXSD(xml, xsd));
        }
        catch (Exception er){}






        NewClassScripts nc = new NewClassScripts();
//        ArrayList<Post> posts = nc.selectPosts();
        ArrayList<Employee> employees = nc.selectEmployees();

        req.setAttribute("employeesData", employees);
        req.setAttribute("postsData", posts);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/newTask.jsp");
        requestDispatcher.forward(req, resp);

    }

}
