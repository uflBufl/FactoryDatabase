package app.scripts;

import app.Requests.PostRequests;
import app.Requests.Requests;
import app.SQLConnection;
import app.entities.Employee;
import app.entities.Post;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostScripts {

    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;


    public void AddPost(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("email");
//        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
//        String addressFilterString = req.getParameter("address");
//        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
        String salaryFilterString = req.getParameter("salary");
        if(!(salaryFilterString.equals("") )){ args.add("salary");params.add(salaryFilterString);}
        String timeFilterString = req.getParameter("time");
        if(!(timeFilterString.equals(""))){ args.add("time"); params.add(timeFilterString);}
//        String headFilterString = req.getParameter("head");
//        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        try {
            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = PostRequests.getPostInsert(args);

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
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void DeletePost(HttpServletRequest req){
        String postId = req.getParameter("deleteButton");
        String sqlRequest = PostRequests.postDelete;

        try {

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, postId);
            pstmt.executeUpdate();

        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
        catch (Exception ex) { ex.printStackTrace(); }
        finally {
            try { con.close(); } catch (SQLException se) { /*can't do anything */ }
            try { pstmt.close(); } catch (SQLException se) { /*can't do anything */ }
        }

    }



    public ArrayList<Post> SelectManyPosts(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("Name");
        if(!(nameFilterString == "" || nameFilterString == null)){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("Email");
//        if(!(emailFilterString == "" || emailFilterString == null)){ args.add("email"); params.add(emailFilterString);}
//        String addressFilterString = req.getParameter("Address");
//        if(!(addressFilterString == "" || addressFilterString == null)){ args.add("address"); params.add(addressFilterString);}
        String salaryFilterString = req.getParameter("Salary");
        if(!(salaryFilterString == "" || salaryFilterString == null)){ args.add("salary");params.add(salaryFilterString);}
        String timeFilterString = req.getParameter("Time");
        if(!(timeFilterString == "" || timeFilterString == null)){ args.add("time"); params.add(timeFilterString);}
//        String headFilterString = req.getParameter("Head");
//        System.out.println(headFilterString);
//        if(!(headFilterString == "" || headFilterString == null)){ args.add("head");params.add(headFilterString);}

        ArrayList<Post> posts = new ArrayList<Post>();
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            if (!args.isEmpty()) {

                String sqlRequest = PostRequests.getPostSelectWhere(args);

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
                String sqlRequest = PostRequests.postSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

            rs = pstmt.executeQuery();

//            ArrayList<Employee> employees = new ArrayList<Employee>();

            while (rs.next()) {
                int postId = rs.getInt(1);
                String name = rs.getString(2);
//                String email = rs.getString(3);
//                String address = rs.getString(4);
                int salary = rs.getInt(3);
                int time = rs.getInt(4);
//                int head = rs.getInt(7);

                Post post = new Post(postId, name, salary, time);
                posts.add(post);
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
        return posts;
    }


    public Post SelectOnePost(HttpServletRequest req){

        String id = req.getParameter("PostId");
        String sqlRequest = PostRequests.postSelectWherePostId;
        Post post = null;
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int postId = rs.getInt(1);
                String name = rs.getString(2);
//                String email = rs.getString(3);
//                String address = rs.getString(4);
                int salary = rs.getInt(3);
                int time = rs.getInt(4);
//                int head = rs.getInt(7);

                post = new Post(postId,name,salary,time);
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

        return post;
    }


    public void UpdatePost(HttpServletRequest req){

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = req.getParameter("name");
        if(!(nameFilterString.equals(""))){ args.add("name"); params.add(nameFilterString);}
//        String emailFilterString = req.getParameter("email");
//        if(!(emailFilterString.equals("") || emailFilterString.equals("null"))){ args.add("email"); params.add(emailFilterString);}
//        String addressFilterString = req.getParameter("address");
//        if(!(addressFilterString.equals("") || addressFilterString.equals("null"))){ args.add("address"); params.add(addressFilterString);}
        String salaryFilterString = req.getParameter("salary");
        if(!(salaryFilterString.equals("") )){ args.add("salary");params.add(salaryFilterString);}
        String timeFilterString = req.getParameter("time");
        if(!(timeFilterString.equals(""))){ args.add("time"); params.add(timeFilterString);}
//        String headFilterString = req.getParameter("head");
//        if(!(headFilterString.equals("")  || headFilterString.equals("0"))){ args.add("head");params.add(headFilterString);}

        String postId = req.getParameter("PostId");
        params.add(postId);

        try {

//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            SQLConnection scon = new SQLConnection();
            con = scon.getConnection();

            String sqlRequest = PostRequests.getPostUpdate(args);

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
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
