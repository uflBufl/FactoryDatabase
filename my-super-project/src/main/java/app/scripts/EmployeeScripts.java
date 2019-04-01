package app.scripts;

import app.Requests.Requests;
import app.SQLConnection;
import app.entities.Employee;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeScripts {

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

//    private Employee getEmployee(ResultSet rs){
//        int employeeId = rs.getInt(1);
//        String name = rs.getString(2);
//        int postId = rs.getInt(5);
//        int departmentId = rs.getInt(6);
//
//        return new Employee(employeeId,name,postId,departmentId);
//    }

//    public void AddEmployee(HttpServletRequest req){
//        String newEmployeeName = req.getParameter("name");
//        String sqlRequest = Requests.employeeInsert;
//        try {
//
//            SQLConnection scon = new SQLConnection();
//            con = scon.getConnection();
//            pstmt = con.prepareStatement(sqlRequest);
//            pstmt.setString(1, newEmployeeName);
//            pstmt.executeUpdate();
//
//        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//        catch (Exception ex) { ex.printStackTrace(); }
//        finally {
//            //close connection ,stmt and resultset here
//            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
//    }




    public void addEmployee(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
        String emailFilterString = req.getParameter("email");
        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("address");
        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
        String postIdFilterString = req.getParameter("postid");
        if(!(postIdFilterString.equals("") )){ args.add("postId");params.add(postIdFilterString);}
        String departmentIdFilterString = req.getParameter("departmentid");
        if(!(departmentIdFilterString.equals(""))){ args.add("departmentId"); params.add(departmentIdFilterString);}
        String headFilterString = req.getParameter("head");
        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        try {
            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = Requests.getEmployeeInsert(args);

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






    public void deleteEmployee(HttpServletRequest req){
        String employeeId = req.getParameter("deleteButton");
        String sqlRequest = Requests.employeeDelete;

        try {

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
        catch (Exception ex) { ex.printStackTrace(); }
        finally {
            try { con.close(); } catch (SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch (SQLException se) { /*can't do anything */ }
        }

    }


    public ArrayList<Employee> selectFilterEmployees(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("Name");
        if(!(nameFilterString == "" || nameFilterString == null)){ args.add("name"); params.add(nameFilterString);}
        String emailFilterString = req.getParameter("Email");
        if(!(emailFilterString == "" || emailFilterString == null)){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("Address");
        if(!(addressFilterString == "" || addressFilterString == null)){ args.add("address"); params.add(addressFilterString);}
        String postIdFilterString = req.getParameter("PostId");
        if(!(postIdFilterString == "" || postIdFilterString == null)){ args.add("postId");params.add(postIdFilterString);}
        String departmentIdFilterString = req.getParameter("DepartmentId");
        if(!(departmentIdFilterString == "" || departmentIdFilterString == null)){ args.add("departmentId"); params.add(departmentIdFilterString);}
        String headFilterString = req.getParameter("Head");
//        System.out.println(headFilterString);
        if(!(headFilterString == "" || headFilterString == null)){ args.add("head");params.add(headFilterString);}

        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            if (!args.isEmpty()) {

                String sqlRequest = Requests.getEmployeeSelectWhere(args);

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
                String sqlRequest = Requests.employeeSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

            rs = pstmt.executeQuery();

//            ArrayList<Employee> employees = new ArrayList<Employee>();

            while (rs.next()) {
                int employeeId = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                int postId = rs.getInt(5);
                int departmentId = rs.getInt(6);
                int head = rs.getInt(7);

                Employee employee = new Employee(employeeId, name, email, address, postId, departmentId, head);
                employees.add(employee);
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
        return employees;
    }



    public Employee selectEmployeeById(HttpServletRequest req){

        String id = req.getParameter("EmployeeId");
        String sqlRequest = Requests.employeeSelectWhereEmployeeId;
        Employee employee = null;
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
                String email = rs.getString(3);
                String address = rs.getString(4);
                int postId = rs.getInt(5);
                int departmentId = rs.getInt(6);
                int head = rs.getInt(7);

                employee = new Employee(employeeId,name,email,address,postId,departmentId, head);
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

        return employee;
    }


//    public void UpdateEmployee(HttpServletRequest req){
//
//        String newEmployeeName = req.getParameter("name");
//        String employeeId = req.getParameter("EmployeeId");
//        String sqlRequest = Requests.employeeUpdate;
//
//        try {
//            SQLConnection scon = new SQLConnection();
//            con = scon.getConnection();
//
//            pstmt = con.prepareStatement(sqlRequest);
//
//            pstmt.setString(1, newEmployeeName);
//            pstmt.setString(2, employeeId);
//
//            pstmt.executeUpdate();
//
//        } catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            //close connection ,stmt and resultset here
//            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { pstmt.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
//
//    }



    public void updateEmployee(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
        String emailFilterString = req.getParameter("email");
        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
        String addressFilterString = req.getParameter("address");
        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
        String postIdFilterString = req.getParameter("postid");
        if(!(postIdFilterString.equals("") )){ args.add("postId");params.add(postIdFilterString);}
        String departmentIdFilterString = req.getParameter("departmentid");
        if(!(departmentIdFilterString.equals(""))){ args.add("departmentId"); params.add(departmentIdFilterString);}
        String headFilterString = req.getParameter("head");
        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        String employeeId = req.getParameter("EmployeeId");
        params.add(employeeId);

        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = Requests.getEmployeeUpdate(args);

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
