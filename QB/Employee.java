package QB;

import java.io.*;
import java.util.*;
import java.sql.*;

class EmployeeManagementSystem {
    public static Connection con = null;
    static PreparedStatement st;
    static Scanner sc = new Scanner(System.in);

    public EmployeeManagementSystem() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaydoshi ", "root", "");
    }

    public static void main(String[] args) throws Exception {

        boolean flag = true;
        try {
            while (flag) {

                System.out.println("Enter the choice ");
                System.out.println("1) Add Employee\r\n" +
                        "2) Retrieve Employee\r\n" +
                        "3) Delete Employee\r\n" +
                        "4) Database Metadata\r\n" +
                        "5) ResultSet Metadata\r\n" +
                        "6) Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println();
                        addEmployee();
                        break;
                    case 2:
                        System.out.println();
                        RetrieveEmployee();
                        break;
                    case 3:
                        System.out.println();
                        deleteEmployee();
                        break;
                    case 4:
                        System.out.println();
                        databasemetadata1();
                        break;
                    case 5:
                        System.out.println();
                        resultsetmetadata1();
                        break;
                    case 6:
                        flag = false;
                        break;
                    default:
                        break;
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid input");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addEmployee() throws Exception {
        new EmployeeManagementSystem();
        System.out.println("Enter the number of employees ");
        int num_of_emp = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num_of_emp; i++) {
            System.out.println("Enter the name of emp");
            String name = sc.nextLine();
            System.out.println("Enter the department of the emp");
            String dept = sc.nextLine();
            String insertDAta = "Insert Into employeedata(Emp_name,Emp_dept,Emp_profile,Emp_file) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertDAta);
            ps.setString(1, name);
            ps.setString(2, dept);

            // inputting photo in database
            File f = new File("C:\\Java language\\java programs\\src\\JDBC\\src\\QB\\" + name + ".jpg");
            FileInputStream fs = new FileInputStream(f);
            ps.setBinaryStream(3, fs);
            // inputting file in database
            File f1 = new File("C:\\Java language\\java programs\\src\\JDBC\\src\\QB\\" + name + ".txt");
            FileReader fr = new FileReader(f1);
            ps.setCharacterStream(4, fr, f1.length());
            if (ps.executeUpdate() >= 0) {
                System.out.println("The data of Employee is added");
            } else {
                System.out.println("The employee data is not added ");
            }
        }
    }

    static void RetrieveEmployee() throws Exception {
        new EmployeeManagementSystem();
        System.out.println("hello");
        String displaying_data = "SELECT * FROM employeedata";
        PreparedStatement ps = con.prepareStatement(displaying_data);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("id: " + rs.getInt(1) + " name: " + rs.getString(2) + " departemnt: " + rs.getString(3));
            System.out.println();

            // creating new txt file by Clob
            Clob c = rs.getClob("Emp_file");
            String fileName = "C:\\Java language\\java programs\\src\\JDBC\\src\\QB\\new"
                    + rs.getString(2) + ".txt";
            String data = c.getSubString(1, (int)c.length());
            System.out.println("Text: \n");
            System.out.println(data);
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            fw.write(data);
            fw.close();

            // Creating a new Jpg photo by Blob
            Blob b = rs.getBlob("Emp_profile");
            byte arr[] = b.getBytes(1, (int) b.length());
            String fileName2 = "C:\\Java language\\java programs\\src\\JDBC\\src\\QB\\new"
                    + rs.getString(2) + ".jpg";
            FileOutputStream fos = new FileOutputStream(fileName2);
            fos.write(arr);
            fos.close();
        }
    }

    static void deleteEmployee() throws Exception {
        new EmployeeManagementSystem();
        String updateQuery = "Delete from employeedata where id =?";
        PreparedStatement ps = con.prepareStatement(updateQuery);
        System.out.println("Enter id to delete ");
        int id = sc.nextInt();
        ps.setInt(1, id);
        if (ps.executeUpdate() >= 0) {
            System.out.println("employee is deleted ");
        } else {
            System.out.println("Failed to delete employee");
        }

    }

    static void resultsetmetadata1() throws Exception {
        new EmployeeManagementSystem();
        // Retrieve and display metadata about the result set, including table name,
        // column count, and column names.
        String query = "select *from employeedata";
        PreparedStatement st = con.prepareStatement(query);
        ResultSetMetaData rsmd = st.getMetaData();
        System.out.println("TableName: " + rsmd.getTableName(1));
        System.out.println("Column Name: " + rsmd.getColumnName(1));
        System.out.println("Column Name: " + rsmd.getColumnName(2));
        System.out.println("Column Name: " + rsmd.getColumnName(3));
        System.out.println("Column Name: " + rsmd.getColumnName(4));
        System.out.println("Column Count : " + rsmd.getColumnCount());
        System.out.println("Column Type name: " + rsmd.getColumnTypeName(1));
        System.out.println("Catalog name: " + rsmd.getCatalogName(1));
    }

    static void databasemetadata1() throws Exception {
        new EmployeeManagementSystem();
        DatabaseMetaData dbmd = con.getMetaData();
        System.out.println("Driver name " + dbmd.getDriverVersion());
        System.out.println("Driver version " + dbmd.getDriverVersion());
        System.out.println("User name : " + dbmd.getUserName());
        System.out.println("Database product Name: " + dbmd.getDatabaseProductName());
    }
}
