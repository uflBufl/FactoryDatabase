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
    private static SQLConnection scon;

    public DepartmentScripts() {
        try {
            this.scon = new SQLConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addDepartment(ArrayList<String> params, ArrayList<String> args) {
        try {
            con = scon.getConnection();
            String sqlRequest = DepartmentRequests.getDepartmentInsert(args);
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


    public void deleteDepartment(String departmentId) {
        String sqlRequest = DepartmentRequests.departmentDelete;

        try {
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, departmentId);
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


    public ArrayList<Department> selectFilterDepartments(ArrayList<String> params, ArrayList<String> args) {
        ArrayList<Department> departments = new ArrayList<Department>();
        try {
            con = scon.getConnection();
            if (!args.isEmpty()) {

                String sqlRequest = DepartmentRequests.getDepartmentSelectWhere(args);

                pstmt = con.prepareStatement(sqlRequest);
                ServletsScripts.setParams(params, pstmt);

                System.out.println(pstmt.toString());

            } else {
                String sqlRequest = DepartmentRequests.departmentSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);

                Department department = new Department(departmentId, name, address);
                departments.add(department);
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
        return departments;
    }


    public Department selectDepartmentById(String id) {

        String sqlRequest = DepartmentRequests.departmentSelectWhereDepartmentId;
        Department department = null;
        try {
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);

                department = new Department(employeeId, name, address);
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

        return department;
    }


    public void updateDepartment(ArrayList<String> params, ArrayList<String> args) {
        try {
            con = scon.getConnection();

            String sqlRequest = DepartmentRequests.getDepartmentUpdate(args);
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
