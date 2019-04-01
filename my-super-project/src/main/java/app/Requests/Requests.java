package app.Requests;

import java.util.ArrayList;

public class Requests {

    public static final String employeeSelectAll = "select * from Employee ";

    public static final String employeeSelectWhereEmployeeId = "select * from Employee " +
            "WHERE EmployeeId= ?";

    public static final String employeeSelectWherePostId = "select * from Employee " +
            "WHERE postId= ? ";

    public static final String employeeDelete = "DELETE FROM Employee " +
            "WHERE employeeId = ? ;";

    public static final String employeeInsert = "INSERT INTO Employee " +
            "(name,postId,departmentId)" +
            "VALUES" +
            "(?,1,1);";

    public static String getEmployeeInsert(ArrayList<String> args) {
        String s = "INSERT INTO Employee ";
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

    public static final String employeeUpdate = "UPDATE Employee " +
            "SET name = ? " +
            "WHERE employeeId = ?;";

    public static String getEmployeeUpdate(ArrayList<String> args) {
        String s = "UPDATE Employee ";
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

        s = s + "WHERE employeeId = ?;";

        return s;
    }

    public static String getEmployeeSelectWhere(ArrayList<String> args) {
        String s = "select * from Employee ";
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
