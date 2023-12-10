package JDBC.src.DBMC;
import java.sql.*;
public class DBMC1 {
    public static void main(String[] args) throws Exception{
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/practice", "postgres", "1234");
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }
        DatabaseMetaData op =con.getMetaData();
        System.out.println(op.getDatabaseProductName());
        System.out.println(op.getDatabaseProductVersion());
        System.out.println(op.getDatabaseMajorVersion());
        System.out.println(op.getDatabaseMinorVersion());
        System.out.println(op.getURL());
        System.out.println(op.getMaxColumnsInTable());
        System.out.println(op.getDriverName());
        System.out.println(op.getUserName());
    }
}
