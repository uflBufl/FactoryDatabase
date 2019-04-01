package app;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnection {
    private InitialContext ic;
    private DataSource ds;

    public SQLConnection() throws NamingException{
        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mydb"); // вместо написать java:/comp/env/jdbc/TestDB
    }

    public Connection getConnection() throws SQLException{
//        ic = new InitialContext();
////        Class.forName("com.mysql.jdbc.Driver");
//        ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mydb"); // вместо написать java:/comp/env/jdbc/TestDB
        return ds.getConnection();
    }
}
