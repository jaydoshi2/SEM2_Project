package JDBC.src.QB;
import java.sql.*;
import java.util.*;

class Callable2 {
    public static void main(String[] args) throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/monishca";
        String dbuser = "root";
        String dbpass = "";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }
        String sql = "{call getEmployeeData()}";
        CallableStatement cst = con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();
        while (rs.next()) {
            System.out.println("e_id " + rs.getInt(1));
            System.out.println("name " + rs.getString(2));
        }
    }
}