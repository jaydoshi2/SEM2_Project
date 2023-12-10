package JDBC.src.Individual_Project.D5;

import java.util.*;
import java.sql.*;
import java.io.*;

public class _2Giving_feedback {
    static int feedack1 = 0;
    static int feedack2 = 0;
    static int feedack3 = 0;
    static int feedack4 = 0;
    static int feedack5 = 0;
    static int sum_teacher1 = 0;
    static int sum_teacher2 = 0;
    static int sum_teacher3 = 0;
    static int sum_teacher4 = 0;
    static int sum_teacher5 = 0;

    static int avg_teacher1 = 0;
    static int avg_teacher2 = 0;
    static int avg_teacher3 = 0;
    static int avg_teacher4 = 0;
    static int avg_teacher5 = 0;
    static ArrayList<Integer> Negative_points1 = new ArrayList<>();
    static ArrayList<Integer> Negative_points2 = new ArrayList<>();
    static ArrayList<Integer> Negative_points3 = new ArrayList<>();
    static ArrayList<Integer> Negative_points4 = new ArrayList<>();
    static ArrayList<Integer> Negative_points5 = new ArrayList<>();

    static Connection con = null;

    static String[] Subject = { "Maths", "DBMS", "Java", "FEE", "DS" };
    static HashMap<Integer, String> negative_aspects_of_teacher = new HashMap<>();

    public static void input(HashMap<String, Faculty> facultyMap, String class_String) throws Exception {
        Scanner sc = new Scanner(System.in);
        negative_aspects_of_teacher.put(1, "IMPROPER TEACHING");
        negative_aspects_of_teacher.put(2, "BAD BEHAVIOUR");
        negative_aspects_of_teacher.put(3, "LACK OF KNOWLEDGE");
        negative_aspects_of_teacher.put(4, "TEACHING BAD APPROACHES TO STUDENTS");
        negative_aspects_of_teacher.put(5, "LACK IN DISCIPLINE CONTROL");

        Faculty faculty = facultyMap.get(class_String);
        boolean mflag = true;

        System.out.println("GIVE THE FEEDBACK OUT OF 10");
        System.out.println("GIVE FEEDBACK TO " + faculty.maths);

        boolean flag1 = true;
        while (flag1) {
            try {
                feedack1 = sc.nextInt();
                if (feedack1 > 10) {
                    throw new IllegalArgumentException("ENTER VALUE Less than 10");

                } else {
                    sum_teacher1 += feedack1;
                    System.out.println("Add negative point if you want to (Enter 0 if you don't)");
                    int choice1 = sc.nextInt();
                    boolean check_flag1 = true;
                    while (check_flag1) {
                        if (choice1 > 5) {
                            throw new IllegalArgumentException("ENTER negative point Less than 5");
                        }
                        if (choice1 != 0) {
                            System.out.println("Displaying negative points");
                            System.out.println(negative_aspects_of_teacher);
                            for (int i = 0; i < choice1; i++) {
                                System.out.println("Choose negative point from above points");
                                int choose1 = sc.nextInt();
                                Negative_points1.add(choose1);
                                flag1 = false;
                                check_flag1= false;
                            }
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                flag1 = true;
            }
        }

        System.out.println("Give feedack to " + faculty.DBMS);
        feedack2 = sc.nextInt();
        if (feedack2 > 10) {
            throw new IllegalArgumentException("Enter value less than 10 and run program again");
        } else {
            sum_teacher2 += feedack2;
            System.out.println("Add negative point if you want to (Enter 0 if you don't)");
            int choice1 = sc.nextInt();
            if (choice1 != 0) {
                System.out.println("Displaying negative points");
                System.out.println(negative_aspects_of_teacher);
                for (int i = 0; i < choice1; i++) {
                    System.out.println("Choose negative point from above points");
                    int choose1 = sc.nextInt();
                    Negative_points2.add(choose1);
                }
            }
        }
        System.out.println("Give feedack to " + faculty.Java);
        feedack3 = sc.nextInt();
        if (feedack3 > 10) {
            throw new IllegalArgumentException("Enter value less than 10 and run program again");
        } else {
            sum_teacher3 += feedack3;
            System.out.println("Add negative point if you want to (Enter 0 if you don't)");
            int choice1 = sc.nextInt();
            if (choice1 != 0) {
                System.out.println("Displaying negative points");
                System.out.println(negative_aspects_of_teacher);
                for (int i = 0; i < choice1; i++) {
                    System.out.println("Choose negative point from above points");
                    int choose1 = sc.nextInt();
                    Negative_points3.add(choose1);
                }
            }
        }
        System.out.println("Give feedack to " + faculty.FEE);
        feedack4 = sc.nextInt();
        if (feedack4 > 10) {
            throw new IllegalArgumentException("Enter value less than 10 and run program again");

        } else {
            sum_teacher4 += feedack4;
            System.out.println("Add negative point if you want to (Enter 0 if you don't)");
            int choice1 = sc.nextInt();
            if (choice1 != 0) {
                System.out.println("Displaying negative points");
                System.out.println(negative_aspects_of_teacher);
                for (int i = 0; i < choice1; i++) {
                    System.out.println("Choose negative point from above points");
                    int choose1 = sc.nextInt();
                    Negative_points4.add(choose1);
                }
            }
        }
        System.out.println("Give feedack to " + faculty.DS);
        feedack5 = sc.nextInt();
        if (feedack5 > 10) {
            throw new IllegalArgumentException("Enter value less than 10 and run program again");
        } else {
            sum_teacher5 += feedack5;
            System.out.println("Add negative point if you want to (Enter 0 if you don't)");
            int choice1 = sc.nextInt();
            if (choice1 != 0) {
                System.out.println("Displaying negative points");
                System.out.println(negative_aspects_of_teacher);
                for (int i = 0; i < choice1; i++) {
                    System.out.println("Choose negative point from above points");
                    int choose1 = sc.nextInt();
                    Negative_points5.add(choose1);
                }
            }
        }
    }

    public static void find_average(int no_of_students) {

        avg_teacher1 = (int) (sum_teacher1 / no_of_students);
        avg_teacher2 = (int) (sum_teacher2 / no_of_students);
        avg_teacher3 = (int) (sum_teacher3 / no_of_students);
        avg_teacher4 = (int) (sum_teacher4 / no_of_students);
        avg_teacher5 = (int) (sum_teacher5 / no_of_students);
    }

    public static void storing(HashMap<String, Faculty> facultyMap, String class_String) throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/practice", "postgres", "1234");
        if (con != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }

        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + class_String +
                "(Name VARCHAR(255), " + "Subject VARCHAR(255), " +
                "Feedback INT, Negative_points TEXT, PRIMARY KEY (Name, Subject))";

        con.createStatement().executeUpdate(createTableSQL);
        String insertValues = "insert into " + class_String
                + "(Name, Subject,Feedback, Negative_points) Values (?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertValues);

        Faculty faculty = facultyMap.get(class_String);

        preparedStatement.setString(1, faculty.maths);
        preparedStatement.setString(2, Subject[0]);
        preparedStatement.setInt(3, avg_teacher1);

        File f1 = new File(_1Class_selection.class_name);
        FileWriter fw1 = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.maths.replaceAll("\\s+", "") + ".txt", true);
        BufferedWriter bw1 = new BufferedWriter(fw1);

        for (Integer number : Negative_points1) {
            bw1.write(negative_aspects_of_teacher.get(number) + "\n");
        }
        bw1.close();

        FileReader fr1 = new FileReader("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.maths.replaceAll("\\s+", "") + ".txt");
        preparedStatement.setCharacterStream(4, fr1, (int) f1.length());
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, faculty.DBMS);
        preparedStatement.setString(2, Subject[1]);
        preparedStatement.setInt(3, avg_teacher2);

        File f2 = new File(_1Class_selection.class_name, faculty.DBMS.trim());
        FileWriter fw2 = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.DBMS.replaceAll("\\s+", "") + ".txt", true);
        BufferedWriter bw2 = new BufferedWriter(fw2);

        for (Integer number : Negative_points2) {
            bw2.write(negative_aspects_of_teacher.get(number) + "\n");
        }
        bw2.close();

        FileReader fr2 = new FileReader("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.DBMS.replaceAll("\\s+", "") + ".txt");
        preparedStatement.setCharacterStream(4, fr2, (int) f2.length());
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, faculty.Java);
        preparedStatement.setString(2, Subject[2]);
        preparedStatement.setInt(3, avg_teacher3);
        File f3 = new File(faculty.Java.trim());
        FileWriter fw3 = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.Java.replaceAll("\\s+", "") + ".txt", true);
        BufferedWriter bw3 = new BufferedWriter(fw3);

        for (Integer number : Negative_points3) {
            bw3.write(negative_aspects_of_teacher.get(number) + "\n");
        }
        bw3.close();

        FileReader fr3 = new FileReader("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.Java.replaceAll("\\s+", "") + ".txt");
        preparedStatement.setCharacterStream(4, fr3, (int) f3.length());
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, faculty.FEE);
        preparedStatement.setString(2, Subject[3]);
        preparedStatement.setInt(3, avg_teacher4);
        File f4 = new File(faculty.FEE.trim());
        FileWriter fw4 = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.FEE.replaceAll("\\s+", "") + ".txt", true);
        BufferedWriter bw4 = new BufferedWriter(fw4);

        for (Integer number : Negative_points1) {
            bw4.write(negative_aspects_of_teacher.get(number) + "\n");
        }
        bw4.close();

        FileReader fr4 = new FileReader("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.FEE.replaceAll("\\s+", "") + ".txt");
        preparedStatement.setCharacterStream(4, fr4, (int) f4.length());
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, faculty.DS);
        preparedStatement.setString(2, Subject[4]);
        preparedStatement.setInt(3, avg_teacher5);
        File f5 = new File(faculty.DS.trim());
        FileWriter fw5 = new FileWriter("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.DS.replaceAll("\\s+", "") + ".txt", true);
        BufferedWriter bw5 = new BufferedWriter(fw5);

        for (Integer number : Negative_points1) {
            bw5.write(negative_aspects_of_teacher.get(number) + "\n");
        }
        bw5.close();

        FileReader fr5 = new FileReader("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\" + f1
                + "\\" + faculty.DS.replaceAll("\\s+", "") + ".txt");
        preparedStatement.setCharacterStream(4, fr5, (int) f5.length());
        preparedStatement.executeUpdate();

    }
}
