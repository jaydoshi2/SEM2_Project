import java.sql.*;
import java.util.*;

class Callable3 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/practice", "postgres", "1234");
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }
        int choice = 0;
        opp: do {
            System.out.println("Enter 1 for insert");
            System.out.println("Enter 2 for update");
            System.out.println("Enter 3 for delete");
            System.out.println("Enter 4 for retirievall");
            System.out.println("Enter 5 for retrieveemployee");
            System.out.println("Enter 6 exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    String sql = "call insertData(?, ?, ?)";
                    CallableStatement cs = con.prepareCall(sql);
                    System.out.println("Enter name to insert");
                    String name = sc.next();
                    System.out.println("Enter salary to insert");
                    float salary = sc.nextFloat();
                    System.out.println("Enter mobile number to insert");
                    String mobile = sc.next();
                    cs.setString(1, name);
                    cs.setFloat(2, salary);
                    cs.setString(3, mobile);
                    cs.execute();
                    break;
                    case 2:
                        String sql2 = "call updateData(?, ?)";
                        CallableStatement cs2 = con.prepareCall(sql2);
                        System.out.println("Enter id to update");
                        int id = sc.nextInt();
                        System.out.println("Enter salary to update");
                        float salary2 = sc.nextFloat();
                        cs2.setInt(1, id);
                        cs2.setFloat(2, salary2);
                        cs2.execute();
                        break;
                        case 3:
                            String sql3 = "call deleteData(?)";
                            CallableStatement cs3 = con.prepareCall(sql3);
                            System.out.println("Enter id to delete");
                            int id2 = sc.nextInt();
                            cs3.setInt(1, id2);
                            cs3.execute();
                    break;
                case 4:
                    String sql4 = "call retrivalData";
                    ResultSet rs = ((Statement) con).executeQuery(sql4);
                    while (rs.next()) {
                        System.out.println("id="+rs.getInt(1));
                        System.out.println("name="+rs.getInt(2));
                        System.out.println("salary="+rs.getInt(3));
                        System.out.println("number="+rs.getInt(4));
                    }
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break opp;
            
                default:
                    break;
            }
        } while (choice != 6);
    }
}