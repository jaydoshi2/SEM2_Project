package DBMC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class DBMC2 {
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
        String query = "select *from employee";
        PreparedStatement st = con.prepareStatement(query);
        ResultSetMetaData rsmd  = st.getMetaData();
        System.out.println(rsmd.getTableName(1));
        System.out.println(rsmd.getColumnName(1));
        System.out.println(rsmd.getColumnCount());
        System.out.println(rsmd.getColumnTypeName(1));
        System.out.println(rsmd.getCatalogName(0));
        
    }
}
