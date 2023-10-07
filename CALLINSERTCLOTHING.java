import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;

 class CallInsertClothingProcedure {
    public static void main(String[] args) {
        CallableStatement callableStatement = null;

        Connection con = null;
        try {
            // Register the PostgreSQL JDBC driver
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");

            // Define the stored procedure call with a parameter
            String storedProcedure = " CALL insert_clothing(?) ";

            // Prepare the callable statement
            callableStatement = con.prepareCall(storedProcedure);

            // Set the input parameter value (replace 123 with your desired parameter value)
            int parameterValue = 123;
            callableStatement.setInt(1, parameterValue);

            // Execute the stored procedure
            callableStatement.execute();

            System.out.println("Stored procedure executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
