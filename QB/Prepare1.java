package QB;
import java.sql.*;

public class Prepare1 {
    static Connection con = null;

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/practice", "postgres", "1234");
            // String createTableSQL = "CREATE TABLE IF NOT EXISTS Employee " +
            // "(name VARCHAR(255), " +
            // "salary INT, "+"Phone_No BIGINT)";

            // con.createStatement().executeUpdate(createTableSQL);
            // System.out.println("Table created");
            String insertSQL = "INSERT INTO employee (name, salary,Phone_No) VALUES (?, ?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            // // preparedStatement.setString(1, "Jay doshi");
            // // preparedStatement.setInt(2, 2300);
            // // preparedStatement.setLong(3, 87938632);
            // preparedStatement.setString(1, "Vraj thakkar");
            // preparedStatement.setInt(2, 23000);
            // preparedStatement.setLong(3, 879938632);
            // // preparedStatement.setString(1, "Monishca");
            // // preparedStatement.setInt(2, 23000);
            // // preparedStatement.setLong(3, 879938632);
            // System.out.println("Values inserted");
            // int x = preparedStatement.executeUpdate();
            // if (x > 0) {
            //     System.out.println("Inserted");
            // }
            // String sql = "update Employee set Phone_No=? where name=?";
            // PreparedStatement pst = con.prepareStatement(sql);
            // pst.setLong(1, 54678912);
            // pst.setString(2, "Monishca");
            // int x = pst.executeUpdate();
            // if(x>0)
            // System.out.println("updated");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select*from employee");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getLong(3));
            }

            // String sql=" select* from Monischa"
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
