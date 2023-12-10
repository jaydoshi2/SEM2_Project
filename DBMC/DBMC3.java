package JDBC.src.DBMC;

import java.io.*;
import java.sql.*;

public class DBMC3 {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/practice", "postgres", "1234");
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }
        String query = "insert into charFileStorage(FieleId,FileName,FileContent) values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, 1);
        pst.setString(2, "charFile");
        File f = new File("C:\\Java language\\java programs\\src\\JDBC\\src\\DBMC\\File1.txt");
        FileReader fr = new FileReader(f);
        pst.setCharacterStream(3, fr, f.length());
        int r = pst.executeUpdate();
        if (r > 0)
            System.out.println("filecreated");
        else
            System.out.println("Storage faile");

    }
}
