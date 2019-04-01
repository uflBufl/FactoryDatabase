package app.scripts;

import app.Requests.DepartmentRequests;
import app.Requests.Requests;
import app.SQLConnection;
import app.entities.Department;
import app.entities.Employee;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentScripts {
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

    public void addDepartment(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("email");
//        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("address");
        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
//        String postIdFilterString = req.getParameter("postid");
//        if(!(postIdFilterString.equals("") )){ args.add("postId");params.add(postIdFilterString);}
//        String departmentIdFilterString = req.getParameter("departmentid");
//        if(!(departmentIdFilterString.equals(""))){ args.add("departmentId"); params.add(departmentIdFilterString);}
//        String headFilterString = req.getParameter("head");
//        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        try {
            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = DepartmentRequests.getDepartmentInsert(args);

            pstmt = con.prepareStatement(sqlRequest);

            int i = 1;

            for (String param : params
            ) {
                pstmt.setString(i, param);
                i++;
            }

            System.out.println(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }


    public void deleteDepartment(HttpServletRequest req){
        String departmentId = req.getParameter("deleteButton");
        String sqlRequest = DepartmentRequests.departmentDelete;

        try {

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, departmentId);
            pstmt.executeUpdate();

        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
        catch (Exception ex) { ex.printStackTrace(); }
        finally {
            try { con.close(); } catch (SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch (SQLException se) { /*can't do anything */ }
        }

    }


    public ArrayList<Department> selectFilterDepartments(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("Name");
        if(!(nameFilterString == "" || nameFilterString == null)){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("Email");
//        if(!(emailFilterString == "" || emailFilterString == null)){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("Address");
        if(!(addressFilterString == "" || addressFilterString == null)){ args.add("address"); params.add(addressFilterString);}
//        String postIdFilterString = req.getParameter("PostId");
//        if(!(postIdFilterString == "" || postIdFilterString == null)){ args.add("postId");params.add(postIdFilterString);}
//        String departmentIdFilterString = req.getParameter("DepartmentId");
//        if(!(departmentIdFilterString == "" || departmentIdFilterString == null)){ args.add("departmentId"); params.add(departmentIdFilterString);}
//        String headFilterString = req.getParameter("Head");
//        System.out.println(headFilterString);
//        if(!(headFilterString == "" || headFilterString == null)){ args.add("head");params.add(headFilterString);}

        ArrayList<Department> departments = new ArrayList<Department>();
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            if (!args.isEmpty()) {

                String sqlRequest = DepartmentRequests.getDepartmentSelectWhere(args);

//                System.out.println(sqlRequest);

                pstmt = con.prepareStatement(sqlRequest);

                int i = 1;

                for (String param : params
                ) {
                    pstmt.setString(i, param);
                    i++;
                }

                System.out.println(pstmt.toString());

            } else {
                String sqlRequest = DepartmentRequests.departmentSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

            rs = pstmt.executeQuery();

//            ArrayList<Employee> employees = new ArrayList<Employee>();

            while (rs.next()) {
                int departmentId = rs.getInt(1);
                String name = rs.getString(2);
//                String email = rs.getString(3);
                String address = rs.getString(3);
//                int postId = rs.getInt(5);
//                int departmentId = rs.getInt(6);
//                int head = rs.getInt(7);

                Department department = new Department(departmentId, name, address);
                departments.add(department);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return departments;
    }


    public Department selectDepartmentById(HttpServletRequest req){

        String id = req.getParameter("DepartmentId");
        String sqlRequest = DepartmentRequests.departmentSelectWhereDepartmentId;
        Department department = null;
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt(1);
                String name = rs.getString(2);
//                String email = rs.getString(3);
                String address = rs.getString(3);
//                int postId = rs.getInt(5);
//                int departmentId = rs.getInt(6);
//                int head = rs.getInt(7);

                department = new Department(employeeId,name,address);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return department;
    }



    public void updateDepartment(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("email");
//        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("address");
        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
//        String postIdFilterString = req.getParameter("postid");
//        if(!(postIdFilterString.equals("") )){ args.add("postId");params.add(postIdFilterString);}
//        String departmentIdFilterString = req.getParameter("departmentid");
//        if(!(departmentIdFilterString.equals(""))){ args.add("departmentId"); params.add(departmentIdFilterString);}
//        String headFilterString = req.getParameter("head");
//        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        String departmentId = req.getParameter("DepartmentId");
        params.add(departmentId);

        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = DepartmentRequests.getDepartmentUpdate(args);

//            System.out.println(sqlRequest);
//            System.out.println(params);

            pstmt = con.prepareStatement(sqlRequest);

            int i = 1;

            for (String param : params
            ) {
                pstmt.setString(i, param);
                i++;
            }

            System.out.println(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
