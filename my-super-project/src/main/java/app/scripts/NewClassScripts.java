package app.scripts;

import app.Requests.PostRequests;
import app.Requests.Requests;
import app.SQLConnection;
import app.entities.Employee;
import app.entities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewClassScripts {

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static SQLConnection scon;

    public NewClassScripts() {
        try {
            this.scon = new SQLConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Post> selectPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            con = scon.getConnection();
            String sqlRequest = PostRequests.postSelectAll;
            pstmt = con.prepareStatement(sqlRequest);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int postId = rs.getInt(1);
                String name = rs.getString(2);
                int salary = rs.getInt(3);
                int time = rs.getInt(4);

                Post post = new Post(postId, name, salary, time);
                posts.add(post);
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
        return posts;
    }





    public ArrayList<Employee> selectEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            con = scon.getConnection();
                String sqlRequest = Requests.employeeSelectAll;
                pstmt = con.prepareStatement(sqlRequest);

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

}
