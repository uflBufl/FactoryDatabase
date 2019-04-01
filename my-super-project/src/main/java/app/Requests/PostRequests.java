package app.Requests;

import java.util.ArrayList;

public class PostRequests {

    public static final String postSelectAll = "select * from Post ";

    public static final String postSelectWherePostId = "select * from Post " +
            "WHERE postId= ?";

    public static final String postDelete = "DELETE FROM Post " +
            "WHERE postId = ? ;";

    public static String getPostInsert(ArrayList<String> args) {
        String s = "INSERT INTO Post ";
        int size = args.size();
        if (!args.isEmpty()) {
            s = s + "(";
            int num = 0;
            for (String arg : args
            ) {

                s = s + arg;
                num++;

//                System.out.println(num);
//                System.out.println(args);
                if (num != size) {
                    s = s + ", ";
                }
            }
            s = s + ") VALUES(";
            num = 0;
            for (String arg : args
            ) {

                s = s + "?";
                num++;

//                System.out.println(num);
//                System.out.println(args);
                if (num != size) {
                    s = s + ", ";
                }
            }
            s = s + ");";
        }

//        s = s + "WHERE employeeId = ?;";

        return s;
    }


    public static String getPostUpdate(ArrayList<String> args) {
        String s = "UPDATE Post ";
        int size = args.size();
        if (!args.isEmpty()) {
            s = s + "SET ";
            int num = 0;
            for (String arg : args
            ) {

                s = s + arg + " = ? ";
                num++;

//                System.out.println(num);
//                System.out.println(args);
                if (num != size) {
                    s = s + ", ";
                }
            }
        }

        s = s + "WHERE postId = ?;";

        return s;
    }


    public static String getPostSelectWhere(ArrayList<String> args) {
        String s = "select * from Post ";
        int size = args.size();
        if (!args.isEmpty()) {
            s = s + "WHERE ";
            int num = 0;
            for (String arg : args
            ) {

                s = s + arg + "= ? ";
                num++;

//                System.out.println(num);
//                System.out.println(args);
                if (num != size) {
                    s = s + "AND ";
                }
            }
        }
        return s;
    }

}
