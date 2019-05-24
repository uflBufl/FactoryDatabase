package app.servlets;

import app.beans.PostSessionBeanLocal;
import app.entities.Employee;
import app.scripts.EmployeeScripts;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.util.ArrayList;

public class ListServlet extends HttpServlet {

    @EJB
    private PostSessionBeanLocal psb;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String emailFilter = req.getParameter("email");
        String addressFilter = req.getParameter("address");
        String postFilter = req.getParameter("postid");
        String departmentFilter = req.getParameter("departmentid");
        String headFilter = req.getParameter("head");

        if (postFilter != null) {
            resp.sendRedirect(req.getContextPath() + "/list?PostId=" + postFilter + "&Name=" + nameFilter + "&Email=" + emailFilter + "&Address=" + addressFilter + "&DepartmentId=" + departmentFilter + "&Head=" + headFilter);
        } else {
            EmployeeScripts es = new EmployeeScripts();
            es.deleteEmployee(employeeId);

            resp.sendRedirect(req.getContextPath() + "/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("employeeId");

        if(id != null) {
            ArrayList employees = psb.getEmployees();

            Employee finalEmployee = new Employee();
            for(int i = 0;i<employees.size();i++){
                Employee p = (Employee)employees.get(i);
                if(p.getEmployeeId() == Integer.parseInt(id)){
                    finalEmployee = p;
                }
            }

            File f = psb.convertToXml(finalEmployee);


            resp.setHeader("Content-Disposition", "attachment;filename=" + "employees.xml");
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            try {

                /**** Get The Output Stream Of The Response ****/
                outStream = resp.getOutputStream();
                inputStream = new FileInputStream(f);

                byte[] bytes = new byte[1024 * 100];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(bytes)) != -1) {
                    // Write image data to Response.
                    outStream.write(bytes, 0, bytesRead);
                }
            } catch (IOException ioExObj) {
                System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                outStream.flush();
                if (outStream != null) {
                    outStream.close();
                }
            }
        }

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("Name");
        if (!(nameFilterString == "" || nameFilterString == null)) {
            args.add("name");
            params.add(nameFilterString);
        }
        String emailFilterString = req.getParameter("Email");
        if (!(emailFilterString == "" || emailFilterString == null)) {
            args.add("email");
            params.add(emailFilterString);
        }
        String addressFilterString = req.getParameter("Address");
        if (!(addressFilterString == "" || addressFilterString == null)) {
            args.add("address");
            params.add(addressFilterString);
        }
        String postIdFilterString = req.getParameter("PostId");
        if (!(postIdFilterString == "" || postIdFilterString == null)) {
            args.add("postId");
            params.add(postIdFilterString);
        }
        String departmentIdFilterString = req.getParameter("DepartmentId");
        if (!(departmentIdFilterString == "" || departmentIdFilterString == null)) {
            args.add("departmentId");
            params.add(departmentIdFilterString);
        }
        String headFilterString = req.getParameter("Head");
        if (!(headFilterString == "" || headFilterString == null)) {
            args.add("head");
            params.add(headFilterString);
        }


        EmployeeScripts es = new EmployeeScripts();
        ArrayList<Employee> employees = es.selectFilterEmployees(params, args);

        req.setAttribute("employeesData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
