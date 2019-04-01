package app.Requests;

import java.util.ArrayList;

public class DepartmentRequests {

    public static final String departmentSelectAll = "select * from Department ";

    public static final String departmentSelectWhereDepartmentId = "select * from Department " +
            "WHERE DepartmentId= ?";

    public static final String departmentDelete = "DELETE FROM Department " +
            "WHERE DepartmentId = ? ;";

    public static String getDepartmentInsert(ArrayList<String> args) {
        String s = "INSERT INTO Department ";
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


    public static String getDepartmentUpdate(ArrayList<String> args) {
        String s = "UPDATE Department ";
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

        s = s + "WHERE departmentId = ?;";

        return s;
    }

    public static String getDepartmentSelectWhere(ArrayList<String> args) {
        String s = "select * from Department ";
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
