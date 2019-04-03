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
    private static SQLConnection scon;

    public EmployeeScripts() {
        try {
            this.scon = new SQLConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addEmployee(ArrayList<String> params, ArrayList<String> args) {

        try {
            con = scon.getConnection();
            String sqlRequest = Requests.getEmployeeInsert(args);
            pstmt = con.prepareStatement(sqlRequest);
            ServletsScripts.setParams(params, pstmt);

            System.out.println(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                pstmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public void deleteEmployee(String employeeId) {
        String sqlRequest = Requests.employeeDelete;

        try {
//            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                pstmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

    public ArrayList<Employee> selectFilterEmployees(ArrayList<String> params, ArrayList<String> args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            con = scon.getConnection();
            if (!args.isEmpty()) {

                String sqlRequest = Requests.getEmployeeSelectWhere(args);
                pstmt = con.prepareStatement(sqlRequest);
                ServletsScripts.setParams(params, pstmt);

                System.out.println(pstmt.toString());

            } else {
                String sqlRequest = Requests.employeeSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

            rs = pstmt.executeQuery();

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
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                pstmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return employees;
    }


    public Employee selectEmployeeById(String id) {
        String sqlRequest = Requests.employeeSelectWhereEmployeeId;
        Employee employee = null;
        try {
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                int postId = rs.getInt(5);
                int departmentId = rs.getInt(6);
                int head = rs.getInt(7);

                employee = new Employee(employeeId, name, email, address, postId, departmentId, head);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                pstmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

        return employee;
    }

    public void updateEmployee(ArrayList<String> params, ArrayList<String> args) {
        try {
            con = scon.getConnection();

            String sqlRequest = Requests.getEmployeeUpdate(args);
            pstmt = con.prepareStatement(sqlRequest);
            ServletsScripts.setParams(params, pstmt);

            System.out.println(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                pstmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
