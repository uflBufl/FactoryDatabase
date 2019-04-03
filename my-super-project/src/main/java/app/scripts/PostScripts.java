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
    private static SQLConnection scon;

    public PostScripts() {
        try {
            this.scon = new SQLConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addPost(ArrayList<String> params, ArrayList<String> args) {
        try {
            con = scon.getConnection();
            String sqlRequest = PostRequests.getPostInsert(args);
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

    public void deletePost(String postId) {
        String sqlRequest = PostRequests.postDelete;

        try {

            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, postId);
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


    public ArrayList<Post> selectFilterPosts(ArrayList<String> params, ArrayList<String> args) {
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            con = scon.getConnection();
            if (!args.isEmpty()) {
                String sqlRequest = PostRequests.getPostSelectWhere(args);
                pstmt = con.prepareStatement(sqlRequest);
                ServletsScripts.setParams(params, pstmt);

                System.out.println(pstmt.toString());

            } else {
                String sqlRequest = PostRequests.postSelectAll;
                pstmt = con.prepareStatement(sqlRequest);
            }

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


    public Post selectPostById(String id) {

        String sqlRequest = PostRequests.postSelectWherePostId;
        Post post = null;
        try {
            con = scon.getConnection();
            pstmt = con.prepareStatement(sqlRequest);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int postId = rs.getInt(1);
                String name = rs.getString(2);
                int salary = rs.getInt(3);
                int time = rs.getInt(4);

                post = new Post(postId, name, salary, time);
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

        return post;
    }


    public void updatePost(ArrayList<String> params, ArrayList<String> args) {
        try {
            con = scon.getConnection();
            String sqlRequest = PostRequests.getPostUpdate(args);
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
