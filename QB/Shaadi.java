package QB;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Shaadi {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Shaadi", "postgres", "1234");
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }

        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Enter 1 to insert your data");
            System.out.println("Enter 2 to get your data");
            System.out.println("choice 7 to exit");
            System.out.println("Enter the choice");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // con.setAutoCommit(false);
                    System.out.println("Enter the id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter your name");
                    String name = sc.nextLine();
                    System.out.println("Select Gender");
                    String Gender = sc.nextLine();
                    System.out.println("Enter the age");
                    int age = sc.nextInt();
                    System.out.println("Enter the salary");
                    double salary = sc.nextDouble();
                    String insertSql = "insert into patner (id,name,age,salary,Gender,detailed_description,photo)values (?,?,?,?,?,?,?)";
                    PreparedStatement p1 = con.prepareStatement(insertSql);
                    p1.setInt(1, id);
                    p1.setString(2, name);
                    p1.setInt(3, age);
                    p1.setDouble(4, salary);
                    p1.setString(5, Gender);
                    // p1.setBlob(id, null, age);
                    // String photo = "WhatsApp Image 2023-09-03 at 01.35.35";
                    File f1 = new File("C:\\Java language\\java programs\\src\\JDBC\\src\\description1.txt");
                    FileReader fr = new FileReader(f1);

                    String filename = "description1.txt";
                    p1.setCharacterStream(6, fr, filename.length());
                    File f2 = new File(
                            "C:\\Java language\\java programs\\src\\JDBC\\src\\WhatsApp Image 2023-09-03 at 01.35.35.jpg");
                    FileInputStream f1s = new FileInputStream(f2);
                    p1.setBinaryStream(7, f1s);
                    boolean r = p1.execute();
                    // if(r){
                    //     System.out.println("Insert sucessfull");
                    // }
                    // else{
                    //     System.out.println("Insert failed");
                    // }
                    break;

                    case 2:
                    sc.nextLine();
                    System.out.println("Getting patner details");
                    String procedure = "{call GetPatner_details1(?)}";
                    System.out.println("Enter the Gender");
                    String gender = sc.nextLine();
                    CallableStatement cs = con.prepareCall(procedure);
                    cs.setString(1, gender);
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        System.out.println("id is "+rs.getInt(1)+"name is "+rs.getString(2)+"salary is "+rs.getDouble(3)+rs.getInt(4));
                        // Blob b = rs.getBlob(7);
                        // byte[] b1 = b.getBytes(1, (int) b.length());
                        // FileOutputStream fs = new FileOutputStream("src/photo.jpg");
                        // fs.write(b1);
                        // fs.close();

                        // Clob c = rs.getClob(6);
                        // Reader rss = c.getCharacterStream();
                        // FileWriter fws = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\description1.txt");
                        // int i;
                        // while ((i=rss.read())!=-1) {
                        //     fws.write((char)i);
                        // }
                        // fws.close();
                    }
                case 7:
                    
                    break;

                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        } while (choice != 7);
    }
}
