package JDBC.src.Individual_Project.D5;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class _1Class_selection {
    public static ArrayList<String> TEACHER = new ArrayList<>();
    public static String class_name;
    public static HashMap<String, Faculty> facultyMap = new HashMap<>();

    public static void addingTeacher() {
        facultyMap.put("D5", new Faculty("krunal sir", "Ankur sir ", "divyang sir", "Nimish das", "Jinal Zala"));
        facultyMap.put("D6", new Faculty("krunal sir", "Ankur sir ", "divyang sir", "Nimish das", "Jinal Zala"));
        facultyMap.put("D9", new Faculty("krunal sir", "Ankur sir ", "divyang sir", "Nimish das", "Jinal Zala"));
        facultyMap.put("D1",
                new Faculty("Meghna parikh", "Chintan morasia", "Milan Trivedi", "Kamaldeep mam", "Komal mam"));
        facultyMap.put("D2",
                new Faculty("Meghna parikh", "Chintan morasia", "Milan Trivedi", "Kamaldeep mam", "Komal mam"));
        facultyMap.put("D10",
                new Faculty("Meghna parikh", "Chintan morasia", "Milan Trivedi", "Kamaldeep mam", "Komal mam"));
    }

    public static void main(String[] args) {
        _1Class_selection.addingTeacher();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the class number");
            int class_number = sc.nextInt();
             class_name = "D" + Integer.toString(class_number);
            sc.nextLine();
            System.out.println("THIS IS D-" + class_number);
            System.out.println("Your teachers are ");

            Faculty faculty = facultyMap.get(class_name);

            File f = new File("C:\\Java language\\java programs\\src\\JDBC\\src\\Individual_Project\\"+class_name);
            if(f.isDirectory()==false){f.mkdir();}
            System.out.println("maths Teacher " + faculty.maths);
            System.out.println("DBMS Teacher " + faculty.DBMS);
            System.out.println("Java Teacher " + faculty.Java);
            System.out.println("FEE Teacher " + faculty.FEE);
            System.out.println("DS Teacher " + faculty.DS);
            System.out.println("Enter the number of students who want to give feedback");
            int no_of_students = sc.nextInt();
            for (int i = 0; i < no_of_students; i++) {
                System.out.println("This is student " + (i + 1));
                _2Giving_feedback.input(facultyMap, class_name);
            }
            _2Giving_feedback.find_average(no_of_students);
            _2Giving_feedback.storing(facultyMap, class_name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e2) {
            // System.out.println("Enter a valid input");
            e2.printStackTrace();
        }
    }
}

class Faculty {
    String maths;
    String Java;
    String DBMS;
    String FEE;
    String DS;

    public Faculty(String maths, String java, String dBMS, String fEE, String dS) {
        this.maths = maths;
        Java = java;
        DBMS = dBMS;
        FEE = fEE;
        DS = dS;
    }
}