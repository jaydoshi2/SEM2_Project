package QB;
import java.sql.*;
import java.util.*;

public class Callable1 {
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
        String sql = "{call Manipulative(?,?,?,?,?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data to insert");
        System.out.println("Enter name");
        String name = sc.next();
        System.out.println("Enter salary");
        Double sal1 = sc.nextDouble();
        System.out.println("Enter mobile_number");
        long mob1 = sc.nextLong();
        System.out.println("Now enter data to update");
        System.out.println("Enter name");
        sc.nextLine();
        String name1 = sc.nextLine();
        System.out.println("Enter Salary");
        Double sal2 = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter data to delete");
        System.out.println("Enter name");
        String name2 = sc.nextLine();
        cst.setString(1, name);
        cst.setDouble(2, sal1);
        cst.setLong(3, mob1);
        cst.setString(4, name1);
        cst.setDouble(5, sal2);
        cst.setString(6, name2);
        cst.execute();
    }
}