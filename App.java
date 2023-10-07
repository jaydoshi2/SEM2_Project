
import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        try {

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            // String createTableSQL = "CREATE TABLE IF NOT EXISTS Teachers " +
            // "(id SERIAL PRIMARY KEY, " +
            // "name VARCHAR(255), " +
            // "age INT)";
            // con.createStatement().executeUpdate(createTableSQL);

            // Insert values into the table
            String insertSQL = "INSERT INTO Teachers (name, age) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
            // preparedStatement.setString(1, "John Doe");
            // preparedStatement.setInt(2, 30);
            // preparedStatement.executeUpdate();

            // preparedStatement.setString(1, "Jane Smith");
            // preparedStatement.setInt(2, 25);
            // preparedStatement.executeUpdate();
            // preparedStatement.setString(1, "Jay Doshi");
            // preparedStatement.setInt(2, 30);
            // preparedStatement.executeUpdate();

            // preparedStatement.setString(1, "Urval Shah");
            // preparedStatement.setInt(2, 25);
            // preparedStatement.executeUpdate();
            preparedStatement.setString(1,"Bhavna");
            preparedStatement.setInt(2,34);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select*from teachers");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
