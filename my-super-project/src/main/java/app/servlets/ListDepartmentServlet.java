package app.servlets;

import app.beans.PostSessionBeanLocal;
import app.entities.Department;
import app.entities.Employee;
import app.scripts.DepartmentScripts;
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

public class ListDepartmentServlet extends HttpServlet {

    @EJB
    private PostSessionBeanLocal psb;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentId = req.getParameter("deleteButton");
        String nameFilter = req.getParameter("name");
        String addressFilter = req.getParameter("address");

        if (nameFilter != null) {
            resp.sendRedirect(req.getContextPath() + "/listDepartment?Name=" + nameFilter + "&Address=" + addressFilter);
        } else {
            DepartmentScripts ds = new DepartmentScripts();
            ds.deleteDepartment(departmentId);

            resp.sendRedirect(req.getContextPath() + "/listDepartment");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("departmentId");

        if(id != null) {
            ArrayList departments = psb.getDepartments();

            Department finalDepartment = new Department();
            for(int i = 0;i<departments.size();i++){
                Department p = (Department)departments.get(i);
                if(p.getDepartmentId() == Integer.parseInt(id)){
                    finalDepartment = p;
                }
            }

            File f = psb.convertToXml(finalDepartment);


            resp.setHeader("Content-Disposition", "attachment;filename=" + "departments.xml");
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
        String addressFilterString = req.getParameter("Address");
        if (!(addressFilterString == "" || addressFilterString == null)) {
            args.add("address");
            params.add(addressFilterString);
        }

        DepartmentScripts ds = new DepartmentScripts();
        ArrayList<Department> departments = ds.selectFilterDepartments(params, args);

        req.setAttribute("departmentsData", departments);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listDepartment.jsp");
        requestDispatcher.forward(req, resp);
    }

}
